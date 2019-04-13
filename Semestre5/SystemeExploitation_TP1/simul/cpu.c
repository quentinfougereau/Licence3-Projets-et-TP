
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <assert.h>

#include "cpu.h"


/**********************************************************
** définition de la mémoire simulée
***********************************************************/

#define MAX_MEM        (128)

#define IS_PHYSICAL_ADR(a)    (((a) >= 0) && ((a) < MAX_MEM))

#define IS_LOGICAL_ADR(a,cpu) (((a) >= 0) && ((a) < ((cpu).SS)))

static WORD mem[MAX_MEM];     /* mémoire                       */


/**********************************************************
** Lire ou écrire une case de la mémoire physique
***********************************************************/

WORD read_mem(int physical_address) {
    if (! IS_PHYSICAL_ADR(physical_address)) {
        fprintf(stderr, "ERROR: read_mem: bad address %d\n", physical_address);
        exit(EXIT_FAILURE);
    }
    return mem[physical_address];
}


void write_mem(int physical_address, WORD value) {
    if (! IS_PHYSICAL_ADR(physical_address)) {
        fprintf(stderr, "ERROR: write_mem: bad address %d\n", physical_address);
        exit(EXIT_FAILURE);
    }
    mem[physical_address] = value;
}


/**********************************************************
** Transformer une adresse logique en adresse physique
***********************************************************/

static int logical_to_physical(int logical_address, PSW cpu) {
    if (! IS_LOGICAL_ADR(logical_address, cpu)) {
        return (-1);
    }
    int physical_address = (cpu.SB + logical_address);
    return (physical_address);
}


/**********************************************************
** Lire une case de la mémoire logique
***********************************************************/

static WORD read_logical_mem(int logical_address, PSW* cpu) {
    int physical_address = logical_to_physical(logical_address, *cpu);
    if (physical_address < 0) {
        cpu->IN = INT_SEGV;
        return (0);
    }
    return read_mem(physical_address);
}


/**********************************************************
** écrire une case de la mémoire logique
***********************************************************/

static void write_logical_mem(int logical_address, PSW* cpu, WORD value) {
    int physical_address = logical_to_physical(logical_address, *cpu);
    if (physical_address < 0) {
        cpu->IN = INT_SEGV;
        return;
    }
    write_mem(physical_address, value);
}


/**********************************************************
** Coder une instruction
***********************************************************/

WORD encode_instruction(unsigned code, unsigned i, unsigned j, short arg) {
    union { WORD word; INST fields; } inst;
    inst.fields.OP  = code;
    inst.fields.i   = i;
    inst.fields.j   = j;
    inst.fields.ARG = arg;
    return (inst.word);
}


/**********************************************************
** Décoder une instruction
***********************************************************/

INST decode_instruction(WORD value) {
    union { WORD integer; INST instruction; } inst;
    inst.integer = value;
    return inst.instruction;
}


/**********************************************************
** instruction d'addition
**
** ADD Ri, Rj, arg
**   AC := (Ri + Rj + arg)
**   Ri := AC
**   PC := (PC + 1)
***********************************************************/

PSW cpu_ADD(PSW m) {
    m.AC = m.DR[m.RI.i] += (m.DR[m.RI.j] + m.RI.ARG);
    m.PC += 1;
    return m;
}


/**********************************************************
** instruction de soustraction
**
** SUB Ri, Rj, arg
**   AC := (Ri - Rj - arg)
**   Ri := AC
**   PC := (PC + 1)
***********************************************************/

PSW cpu_SUB(PSW m) {
    m.AC = m.DR[m.RI.i] -= (m.DR[m.RI.j] + m.RI.ARG);
    m.PC += 1;
    return m;
}


/**********************************************************
** instruction de comparaison
**
** CMP Ri, Rj, arg
**   AC := (Ri - Rj - arg)
**   PC := (PC + 1)
***********************************************************/

PSW cpu_CMP(PSW m) {
    m.AC = (m.DR[m.RI.i] - (m.DR[m.RI.j] + m.RI.ARG));
    m.PC += 1;
    return m;
}


/**********************************************************
** affectation d'un registre
**
** SET Ri, k
**   | Ri := k
**   | PC := (PC + 1)
***********************************************************/

PSW cpu_SET(PSW m) {
    m.AC = m.DR[m.RI.i] = m.RI.ARG;
    m.PC += 1;
    return m;
}


/**********************************************************
** ne rien faire
**
** NOP
**   | PC := (PC + 1)
***********************************************************/

PSW cpu_NOP(PSW m) {
    m.PC += 1;
    return m;
}


/**********************************************************
** lire la mémoire dans un registre
**
** LOAD Ri, Rj, offset
**   AC := mem[ SB + Rj + offset ]
**   Ri := AC
**   PC := (PC + 1)
***********************************************************/

PSW cpu_LOAD(PSW m) {
    WORD logical_address = (m.DR[m.RI.j] + m.RI.ARG);
    WORD value = read_logical_mem(logical_address, &m);

    // erreur de lecture en mémoire logique
    if (m.IN) return (m);

    // lecture réussie
    m.DR[m.RI.i] = m.AC = value;
    m.PC += 1;
    return m;
}


/**********************************************************
** vider un registre en mémoire.
**
** STORE Ri, Rj, offset
**   mem[ SB + Rj + offset ] := Ri
**   PC := (PC + 1)
***********************************************************/

PSW cpu_STORE(PSW m) {
    WORD logical_address = (m.DR[m.RI.j] + m.RI.ARG);
    write_logical_mem(logical_address, &m, m.DR[m.RI.i]);
    
    // erreur d'écriture en mémoire logique
    if (m.IN) return (m);

    // écriture réussie
    m.PC += 1;
    return m;
}


/**********************************************************
** saut inconditionnel.
**
** JUMP offset
**   PC := offset
***********************************************************/

PSW cpu_JUMP(PSW m) {
    m.PC = m.RI.ARG;
    return m;
}


/**********************************************************
** saut si positif.
**
** IFGT offset
**   if (m.AC > 0) PC := offset
**   else PC := (PC + 1)
***********************************************************/

PSW cpu_IFGT(PSW m) {
    if (m.AC > 0) {
        m.PC = m.RI.ARG;
    } else {
        m.PC += 1;
    }
    return m;
}


/**********************************************************
** appel au système (interruption SYSC)
**
** SYSC Ri, Rj, arg
**   PC := (PC + 1)
**   <interruption cause SYSC>
***********************************************************/

static PSW cpu_SYSC(PSW m) {
    m.IN = INT_SYSC;
    m.PC += 1;
    return m;
}


/**********************************************************
** stopper la machine.
***********************************************************/

static PSW cpu_HALT(PSW m) {
    printf("poweroff.\n");
    exit(EXIT_SUCCESS);
    return (m);
}


/**********************************************************
** génération d'une interruption clavier toutes les
** trois secondes.
***********************************************************/

static char keyboard_data = '\0';

char get_keyboard_data() {
    char data = keyboard_data;
    keyboard_data = '\0';
    return data;
}


static PSW keyboard_event(PSW m) {
    static time_t next_kbd_event = 0;
    static char* data_sample = "Keyboard DATA.\n\n";
    static int kbd_data_index = 0;

    time_t now = time(NULL);
    if (next_kbd_event == 0) {
        next_kbd_event = (now + 5);
    } else if (now >= next_kbd_event) {
        m.IN = INT_KEYBOARD;
        next_kbd_event = (now + 3); // dans 3 secondes
        keyboard_data = data_sample[ kbd_data_index ];
        kbd_data_index = ((kbd_data_index + 1) % strlen(data_sample));
    }
    return m;
}


/**********************************************************
** Simulation de la CPU (mode utilisateur)
***********************************************************/

PSW cpu(PSW m) {

    /* pas d'interruption */
    m.IN = INT_NONE;

    m = keyboard_event(m);
    if (m.IN) return (m);
    
    /*** lecture et décodage de l'instruction ***/
    WORD value = read_logical_mem(m.PC, &m);
    if (m.IN) return (m);
    
    m.RI = decode_instruction(value);
    
    /*** exécution de l'instruction ***/
    switch (m.RI.OP) {
    case INST_SET:
        m = cpu_SET(m);
        break;
    case INST_ADD:
        m = cpu_ADD(m);
        break;
    case INST_SUB:
        m = cpu_SUB(m);
        break;
    case INST_CMP:
        m = cpu_CMP(m);
        break;
    case INST_NOP:
        m = cpu_NOP(m);
        break;
    case INST_LOAD:
        m = cpu_LOAD(m);
        break;
    case INST_STORE:
        m = cpu_STORE(m);
        break;
    case INST_JUMP:
        m = cpu_JUMP(m);
        break;
    case INST_IFGT:
        m = cpu_IFGT(m);
        break;
    case INST_SYSC:
        m = cpu_SYSC(m);
        break;
    case INST_HALT:
        m = cpu_HALT(m);
        break;
    default:
        /*** interruption instruction inconnue ***/
        m.IN = INT_INST;
        return (m);
    }
    
    /*** arrêt si l'instruction a provoqué une interruption ***/
    if (m.IN) return m;

    /*** interruption après chaque instruction ***/
    m.IN = INT_TRACE;
    return m;
}


/**********************************************************
** afficher les registres de la CPU
***********************************************************/

void dump_cpu(PSW m) {
    static char* interrupts[] = {
        "NONE","SEGV","INST","TRACE","SYSC"};
    static char* instructions[] = {
        "ADD","CMP","HALT","IFGT","JUMP","LOAD",
        "NOP","SET","STORE","SUB","SYSC"};
    
    fprintf(stdout, "PC = %6d\n", m.PC);
    fprintf(stdout, "SB = %6d | ", m.PC);
    fprintf(stdout, "SS = %6d\n", m.SS);
    if (m.IN < 5) {
        fprintf(stdout, "IN = %6s | ", interrupts[m.IN]);
    } else {
        fprintf(stdout, "IN = %6d | ", m.IN);
    }
    fprintf(stdout, "AC = %6d\n", m.AC);
    for(int i=0; (i<8); i++) {
        fprintf(stdout, "R%d = %6d", i, m.DR[i]);
        fprintf(stdout, ((i % 2) ? "\n" : " | "));
    }
    char* name = ((m.RI.OP < 11) ? instructions[m.RI.OP] : ("?"));
    fprintf(stdout, "RI  = %s R%d, R%d, %d \n", name, m.RI.i, m.RI.j, m.RI.ARG);
    fprintf(stdout, "\n");
}


/**********************************************************
** initialiser la machine (notamment la mémoire)
***********************************************************/

void init_cpu() {
    // vérifier la taille des structures
    assert(sizeof(WORD) == sizeof(INST));
    
    for(int adr=0; (adr < MAX_MEM); adr++) {
        mem[adr] = -1;
    }
}






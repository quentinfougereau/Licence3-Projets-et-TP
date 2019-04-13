
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <assert.h>

#include "cpu.h"


/**********************************************************
** fonctions utilitaires de création d'un programme simple
** directement en mémoire.
***********************************************************/

static WORD physical_adr = 0; /* adresse d'implantation */
static WORD logical_adr = 0;  /* adresse logique        */


/**********************************************************
** Stockage des labels utilisés
***********************************************************/

#define MAX_LABELS      (30)

static int labels[ MAX_LABELS ];

static int nb_used_labels = 0;

static struct USED_LABEL {

    int label;
    int adr;
    int code_op;
    
} used_labels[ MAX_LABELS ];


/**********************************************************
** implanter une instruction en mémoire.
***********************************************************/

static void make_inst(int adr, unsigned code, unsigned i, unsigned j, short arg) {
    WORD w = encode_instruction(code, i, j, arg);
    write_mem(adr, w);
}


/**********************************************************
** Début du codage en mémoire (initialisation).
***********************************************************/

void begin(int begin_physical_adr) {
    physical_adr = begin_physical_adr;
    logical_adr = 0;
    for(int i=0; (i<MAX_LABELS); i++) {
        labels[i] = -1;
        used_labels[i].label = -1;
        used_labels[i].adr = -1;
    }
    nb_used_labels = 0;
}


/**********************************************************
** Fin du codage en mémoire (résolution des labels).
***********************************************************/

void end() {
    for(int i=0; (i<nb_used_labels); i++) {
        struct USED_LABEL used = used_labels[i];
        int label = used.label;
        if (labels[ label ] < 0) {
            fprintf(stderr, "ERROR: label %d unknown\n", label);
            exit(EXIT_FAILURE);
        }
        make_inst(used.adr, used.code_op, 0, 0, labels[ label ]);
    }
}


/**********************************************************
** Fonctions utilitaires de codage des instructions.
***********************************************************/

void set(int ri, int arg) {
    assert( (0 <= ri) && (ri <= 7) );
    make_inst(physical_adr, INST_SET, ri, 0, arg);
    physical_adr++;
    logical_adr++;
}

void add(int ri, int rj, int arg) {
    assert( (0 <= ri) && (ri <= 7) );
    assert( (0 <= rj) && (rj <= 7) );
    make_inst(physical_adr, INST_ADD, ri, rj, arg);
    physical_adr++;
    logical_adr++;
}

void sub(int ri, int rj, int arg) {
    assert( (0 <= ri) && (ri <= 7) );
    assert( (0 <= rj) && (rj <= 7) );
    make_inst(physical_adr, INST_SUB, ri, rj, arg);
    physical_adr++;
    logical_adr++;
}

void load(int ri, int rj, int arg) {
    assert( (0 <= ri) && (ri <= 7) );
    assert( (0 <= rj) && (rj <= 7) );
    make_inst(physical_adr, INST_LOAD, ri, rj, arg);
    physical_adr++;
    logical_adr++;
}

void store(int ri, int rj, int arg) {
    assert( (0 <= ri) && (ri <= 7) );
    assert( (0 <= rj) && (rj <= 7) );
    make_inst(physical_adr, INST_STORE, ri, rj, arg);
    physical_adr++;
    logical_adr++;
}

void sysc(int ri, int rj, int arg) {
    assert( (0 <= ri) && (ri <= 7) );
    assert( (0 <= rj) && (rj <= 7) );
    make_inst(physical_adr, INST_SYSC, ri, rj, arg);
    physical_adr++;
    logical_adr++;
}

void cmp(int ri, int rj) {
    assert( (0 <= ri) && (ri <= 7) );
    assert( (0 <= rj) && (rj <= 7) );
    make_inst(physical_adr, INST_CMP, ri, rj, 0);
    physical_adr++;
    logical_adr++;
}

void label(int label) {
    assert( (0 <= label) && (label <= MAX_LABELS) );
    if (labels[ label ] >= 0) {
        fprintf(stderr, "ERROR: label %d already used\n", label);
        exit(EXIT_FAILURE);
    }
    labels[ label ] = logical_adr;
}

void jump(int label) {
    assert( (0 <= label) && (label <= MAX_LABELS) );
    assert(nb_used_labels < MAX_LABELS);
    struct USED_LABEL used = {label, physical_adr, INST_JUMP};
    used_labels[ nb_used_labels++ ] = used;
    physical_adr++;
    logical_adr++;
}

void if_gt(int label) {
    assert( (0 <= label) && (label <= MAX_LABELS) );
    assert(nb_used_labels < MAX_LABELS);
    struct USED_LABEL used = {label, physical_adr, INST_IFGT};
    used_labels[ nb_used_labels++ ] = used;
    physical_adr++;
    logical_adr++;
}

void nop() {
    make_inst(physical_adr, INST_NOP, 0, 0, 0);
    physical_adr++;
    logical_adr++;
}

void halt() {
    make_inst(physical_adr, INST_HALT, 0, 0, 0);
    physical_adr++;
    logical_adr++;
}


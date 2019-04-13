
/************************************************************
 *
 *  FONCTIONS DE BASE POUR LES E/S DE BLOCKS.
 *
 ***********************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <assert.h>

#include "sgf-header.h"


/************************************************************
 Informations sur le disque simulé
 ************************************************************/

int trace_sgf_disk = 0;


static struct HARD_DISK {
    FILE*   file;
    int     size;
    int     exist;
    char    name[32];
    }
    hd = {NULL, 0, 0, ""};


#define NU_BLOC_OK(n)           (((n) >= 0) && ((n) < hd.size))


/************************************************************
 Initialisation du module
 ************************************************************/

static void init_sgf_disk() {
    char *name = "disk0";
    long size;
    FILE* file;
    
    hd.file = NULL;
    hd.exist = 0;
    hd.size = 0;
    strcpy(hd.name, "");
    
    file = fopen(name, "r+b");
    if (file == NULL)  {
        fclose(file);
        panic("sgf-disk: init_sgf_disk: impossible de trouver un disque");
        return;
    }
    
    if (fseek(file, 0, SEEK_END) != 0) {
        fclose(file);
        panic("sgf-disk: init_sgf_disk: impossible de trouver un disque");
        return;
    }
    
    size = (ftell(file) / BLOCK_SIZE);
    if (size <= 0) {
        fclose(file);
        panic("sgf-disk: init_sgf_disk: impossible de trouver un disque");
        return;
    }
    
    if (size > 10000) {
        fclose(file);
        panic("sgf-disk: init_sgf_disk: disque %s trop important.", name);
        return;
    }
    
    hd.file = file;
    hd.exist = 1;
    hd.size = size;
    strcpy(hd.name, name);
}


/*****************************************************************
 Lire un bloc directement sur le disque physique.
 ****************************************************************/

void read_block(int n, BLOCK* bloc) {
    if (!hd.exist) init_sgf_disk();
    
    if (!NU_BLOC_OK(n)) {
        panic("sgf-disk: read_block: n° de bloc incorrect.");
    }
        
    if (fseek(hd.file, (n * BLOCK_SIZE), SEEK_SET) == 0)
        if (BLOCK_SIZE == fread(bloc, 1, BLOCK_SIZE, hd.file)) {
            if (trace_sgf_disk) {
                fprintf(stderr, "read block %d\n", n);
            }
            return ;
        }
    
    panic("sgf-disk: read_block: impossible de lire le bloc %d\n", n);
    exit(EXIT_FAILURE);
}


/************************************************************
 Ecrire un bloc directement sur le disque physique.
 ************************************************************/

void write_block(int n, BLOCK* b) {
    if (!hd.exist) init_sgf_disk();
    
    if (n < 0  ||  n >= hd.size) {
        panic("sgf-disk: write_block: n° de bloc incorrect.");
    }
        
    if (fseek(hd.file, (n * BLOCK_SIZE), SEEK_SET) == 0) {
        if (BLOCK_SIZE == fwrite(b, 1, BLOCK_SIZE, hd.file)) {
            fflush(hd.file);
            if (trace_sgf_disk) {
                fprintf(stderr, "write block %d\n", n);
            }
            return ;
        }
    }
    
    panic("sgf-disk: impossible d'écrire le bloc %d\n", n);
    exit(EXIT_FAILURE);
}


/************************************************************
 Récupérer la taille du disque (en blocs)
 ************************************************************/

int get_disk_size() {
    if (!hd.exist) init_sgf_disk();
    
    return (hd.size);
}


/************************************************************
 écrire/lire un INODE dans/depuis un bloc
 ************************************************************/

INODE read_inode(int n) {
    BLOCK b;
    INODE inode;

    assert(sizeof(INODE) <= sizeof(BLOCK));
    read_block(n, &b);
    memcpy(&inode, &b, sizeof(INODE));
    return inode;
}


void write_inode(int n, INODE inode) {
    BLOCK b;
    
    assert(sizeof(INODE) <= sizeof(BLOCK));
    memcpy(&b, &inode, sizeof(INODE));
    write_block(n, &b);
}


/************************************************************
 écrire/lire le répertoire dans/depuis un bloc
 ************************************************************/

void read_dir_block(DIRECTORY *dir) {
    BLOCK b;

    assert(sizeof(DIRECTORY) <= sizeof(BLOCK));
    read_block(ADR_DIR_BLOCK, &b);
    memcpy(dir, &b, sizeof(*dir));
}


void write_dir_block(DIRECTORY *dir) {
    BLOCK b;

    assert(sizeof(DIRECTORY) <= sizeof(BLOCK));
    memcpy(&b, dir, sizeof(*dir));
    write_block(ADR_DIR_BLOCK, &b);
}


/************************************************************
 Afficher le message d'erreur et stopper la simulation.
 ************************************************************/

void panic (const char *format, ...) {
    va_list ap;
    
    fprintf(stderr, "Panique: ");
    va_start(ap, format);
    vfprintf(stderr, format, ap);
    va_end(ap);
    fprintf(stderr, "\n");
    exit(EXIT_FAILURE);
    /* ARRET DE LA SIMULATION */
}

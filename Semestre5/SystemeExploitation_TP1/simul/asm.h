
#ifndef __ASM_H
#define __ASM_H


/**********************************************************
** fonctions utilitaires de création d'un programme simple
** directement en mémoire.
***********************************************************/

enum {R0, R1, R2, R3, R4, R5, R6, R7 };

void begin(int begin_adr); /* début du programme */
void end();                /* fin du programme   */

void set(int ri, int arg);           // Ri = arg
void add(int ri, int rj, int arg);   // Ri = Ri + Rj + arg
void sub(int ri, int rj, int arg);   // Ri = Ri - Rj - arg
void load(int ri, int rj, int arg);  // Ri = mem[ Rj + arg ]
void store(int ri, int rj, int arg); // mem[ Rj + arg ] = Ri
void cmp(int ri, int rj);            // AC = (Ri - Rj)
void nop();                          // ne rien faire
void label(int label);               // fixer une étiquette
void if_gt(int label);               // si (AC > 0) aller à
void jump(int label);                // aller à
void sysc(int ri, int rj, int arg);  // appel du système
void halt();                         // arrêter la machine


#endif

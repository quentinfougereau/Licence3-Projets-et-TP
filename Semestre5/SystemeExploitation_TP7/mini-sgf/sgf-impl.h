
#ifndef __SGF_IO_IMPL__
#define __SGF_IO_IMPL__


/************************************************************
 *
 *  FONCTIONS DE LECTURE/ECRITURE DANS UN FICHIER
 *
 ************************************************************/

/************************************************************
 Lister les fichiers du répertoire avec leur taille.
 ************************************************************/

void list_directory_impl (void);

/************************************************************
 *  Ecrire un caractère/une chaîne sur un fichier ouvert.
 ************************************************************/

void sgf_putc_impl (OFILE* f, char  c);

/************************************************************
 *  Lire un caractère sur un fichier ouvert en lecture.
 *  renvoyer -1 en cas de fin de fichier.
 ************************************************************/

int sgf_getc_impl (OFILE* f);

/************************************************************
 *  Ouvrir/Fermer un fichier.
 ************************************************************/

void sgf_close_impl (OFILE* f);

/************************************************************
 * Lire dans le "buffer" le bloc logique "block_number" dans 
 * le fichier ouvert "file".
 ************************************************************/

void sgf_read_block_impl(OFILE* file, int block_number);

/************************************************************
 * Ajouter le bloc contenu dans le tampon au fichier 
 * ouvert décrit par "f".
 ************************************************************/

void sgf_append_block_impl(OFILE* f);

/************************************************************
 * Détruire un fichier.
 ************************************************************/

void sgf_remove_impl(int adr_inode);

/************************************************************
 * Créer un inode vide (renvoie -1 en cas d'erreur)
 ************************************************************/

int create_inode_impl(INODE *inode);

#endif

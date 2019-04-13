
#ifndef __SYSTEM_H
#define __SYSTEM_H


/**********************************************************
** initialisation du système
***********************************************************/

PSW system_init(void);

/**********************************************************
** appel du système (traitement des interruptions)
***********************************************************/

PSW process_interrupt(PSW m);

#endif


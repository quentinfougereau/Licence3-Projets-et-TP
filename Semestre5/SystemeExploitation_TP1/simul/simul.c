
#include <stdlib.h>

#include "cpu.h"
#include "systeme.h"


/**********************************************************
** fonction principale (à ne pas modifier !)
***********************************************************/

int main(void) {
    init_cpu();
    for(PSW mep = system_init();;) {
        mep = cpu(mep);
        mep = process_interrupt(mep);
    }
    return (EXIT_SUCCESS);
}


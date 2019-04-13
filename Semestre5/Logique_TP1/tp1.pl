/***** TP1 EXERCICE 1 *****/
/*--------------------------------- fils(Pere, Mere, Fils) -*/
fils(jean, sophie, pierre).
fils(jean, sophie, jacques).
fils(jacques, sylvie, michel).
fils(michel, laurence, thomas).
fils(thomas, stephanie, alexandre).
/*------------------------------- fille(Pere, Mere, Fille) -*/
fille(jacques, sylvie, nathalie).
fille(francois, sylvie, mathilde).
fille(paul, virginie, stephanie).
fille(pierre, marie, virginie).

/* enfant(X,Y,Z) */
/* Vrai si Z est l'enfant de X et Y */
enfant(X, Y, Z) :- fils(X, Y, Z).
enfant(X, Y, Z) :- fille(X, Y, Z).

/*---------------------------------------------- homme(X) -*/
/* Vrai si X est un homme */
homme(X) :- fils(_,_,X).
homme(X) :- fils(X,_,_).
homme(X) :- fille(X,_,_).

/*---------------------------------------------- femme(X) -*/
/* Vrai si X est une femme */
femme(X) :- fille(_,_,X).
femme(X) :- fils(_,X,_).
femme(X) :- fille(_,X,_).

/*-------------------------------- parent(Enfant, Parent) -*/
parent(Enfant, Pere) :- enfant(Pere, _, Enfant).
parent(Enfant, Mere) :- enfant(_, Mere, Enfant).

/*-----------------grand_parent(PetitEnfant, GrandParent) -*/
grand_parent(PetitEnfant, GrandParent) :- parent(GrandParent,X), parent(X,PetitEnfant).

grand_pere(PetitEnfant, GrandPere) :- grand_parent(PetitEnfant, GrandPere), homme(GrandPere).
grand_mere(PetitEnfant, GrandMere) :- grand_parent(PetitEnfant, GrandMere), femme(GrandMere).

/*--------------------------------------- frere(X, Frere) -*/
frere(X, Frere) :- parent(X, Pere), parent(Frere, Pere), X\=Frere.

/*------------------------- ancetre(Descendant, Ancetre) -*/
ancetre(Descendant, Ancetre) :- parent(Descendant, Ancetre).
ancetre(Descendant, Ancetre) :- parent(Descendant, Y), ancetre(Y, Ancetre).

/****** TP1 EXERCICE 3 *******/

/* ---------------- 1 : entier ---------------- */
    /* entier(X) : Vrai si X est un entier */
    entier(0).
    entier(s(X)) :- entier(X).

/* ---------------- 2 : Pair ------------------ */
    /* pair(X) : vrai si X est pair */
    pair(0).
    pair(s(s(X))) :- pair(X).

/* ---------------- 2 : Impair ---------------- */
    /* impair(X) : vrai si X est impair */
    impair(s(0)).
    impair(s(s(X))) :- impair(X).

/* ---------------- 3 : Inférieur ------------- */
    /* inf(X,Y) : Vrai si X < Y */
    inf(X, s(X)).
    inf(X, s(Y)) :- inf(X, Y).

/* ---------------- 3 : Inférieur EGAL -------- */
    /* infeq(X,Y) : Vrai si X <= Y */
    infeq(X, X).
    infeq(X, s(Y)) :- infeq(X, Y).

/* ---------------- 4 : Opérations ------------ */
    /* add(X,Y,Z) : Vrai si X+Y=Z */
    add(X, 0, X).
    add(X, s(Y), s(Z)) :- add(X, Y, Z).

    /* Soustraction
    ** sub(X,Y,Z) : Vrai si X-Y=Z */
    sub(X, 0, X).
    sub(X, s(Y), Z) :- sub(X, Y, s(Z)).

    /* Multiplication
    ** mul(X,Y,Z) : Vrai si X*Y=Z */
    mul(X,0,0).
    mul(X,s(Y),Z) :- mul(X,Y, W), add(W, X, Z).

    /* Division
    ** div(X,Y,Z) : Vrai si X/Y=Z */
    div(X,X,s(0)).
    div(X,Y,Z) :- mul(Z,Y,X).

    /* Puissance
    ** exp : exposant
    ** res : resultat
    ** pow(X, exp, res) : Vrai si X puissance exp = res */
    pow(X, 0, s(0)).
    pow(X, s(exp), res) :- pow(X, exp, Z), mul(Z, X, res).

    /* fibo(N, Resultat) Vrai si Resultat est le N-ième nombre de Fibonacci */
    fibo(0,0).
    fibo(s(0),s(0)).
    fibo(s(s(X)), Res) :- fibo(X, NMoins2), fibo(s(X), NMoins1), add(NMoins2, NMoins1, Res).


    /***** TP 1 EXERCICE 2 ****/

    droit(bac, l1).
    droit(l1, l2).
    droit(l2, l3).
    droit(l3, m1).
    droit(bts1, bts2).
    droit(d1, d2).
    droit(d2, d3).
    droit(prepa1, prepa2).
    droit(ing1, ing2).
    droit(ing2, ing3).

    selection(bac, bts1).
    selection(bac, iut1).
    selection(bac, prepa1).
    selection(m1, m2).
    selection(m2, d1).
    selection(bts2, lpro).
    selection(prepa2, ing1).
    selection(l2, ing1).
    selection(l3, ing1).
    selection(iut2, ing1).
    selection(l3, ing2).
    selection(m1, ing2).
    selection(lpro, l3).

    bacplus(bac, 0).
    bacplus(l1, s(0)).
    bacplus(bts1, s(0)).
    bacplus(iut1, s(0)).
    bacplus(sup, s(0)).
    badroit(bac, l1).
    droit(l1, l2).
    droit(l2, l3).
    droit(l3, m1).
    droit(bts1, bts2).
    droit(d1, d2).
    droit(d2, d3).
    droit(prepa1, prepa2).
    droit(ing1, ing2).
    droit(ing2, ing3).

    selection(bac, bts1).
    selection(bac, iut1).
    selection(bac, prepa1).
    selection(m1, m2).
    selection(m2, d1).
    selection(bts2, lpro).
    selection(prepa2, ing1).
    selection(l2, ing1).
    selection(l3, ing1).
    selection(iut2, ing1).
    selection(l3, ing2).
    selection(m1, ing2).
    selection(lpro, l3).

    bacplus(bac, 0).
    bacplus(l1, s(0)).
    bacplus(bts1, s(0)).
    bacplus(iut1, s(0)).
    bacplus(sup, s(0)).
    bacplus(l2, s(s(0))).
    bacplus(bts2, s(s(0))).
    bacplus(iut2, s(s(0))).
    bacplus(prepa1, s(s(0))).
    bacplus(l3, s(s(s(0)))).
    bacplus(lpro, s(s(s(0)))).
    bacplus(ing1, s(s(s(0)))).
    bacplus(m1, s(s(s(s(0))))).
    bacplus(ing2, s(s(s(s(0))))).
    bacplus(m2, s(s(s(s(s(0)))))).
    bacplus(ing3, s(s(s(s(s(0)))))).
    bacplus(d1, s(s(s(s(s(s(0))))))).
    bacplus(d2, s(s(s(s(s(s(s(0)))))))).
    bacplus(d3, s(s(s(s(s(s(s(s(0))))))))).

    diplome(l3, licence).
    diplome(bts2, bts).
    diplome(iut2, dut).
    diplome(m2, master).
    diplome(ing3, ingenieur).
    diplome(d3, docteur).
    cplus(l2, s(s(0))).
    bacplus(bts2, s(s(0))).
    bacplus(iut2, s(s(0))).
    bacplus(prepa1, s(s(0))).
    bacplus(l3, s(s(s(0)))).
    bacplus(lpro, s(s(s(0)))).
    bacplus(ing1, s(s(s(0)))).
    bacplus(m1, s(s(s(s(0))))).
    bacplus(ing2, s(s(s(s(0))))).
    bacplus(m2, s(s(s(s(s(0)))))).
    bacplus(ing3, s(s(s(s(s(0)))))).
    bacplus(d1, s(s(s(s(s(s(0))))))).
    bacplus(d2, s(s(s(s(s(s(s(0)))))))).
    bacplus(d3, s(s(s(s(s(s(s(s(0))))))))).

    diplome(l3, licence).
    diplome(bts2, bts).
    diplome(iut2, dut).
    diplome(m2, master).
    diplome(ing3, ingenieur).
    diplome(d3, docteur).

    /**** temps_minimum_bac_diplome(D, T) ****/
    /* Vrai si le diplôme D s’acquiert au minimum en T ans après qu’on ait passé le bac. */
    temps_minimum_bac_diplome(D,T) :- diplome(X,D), bacplus(X,T).

    /**** passage(A1, A2) ****/
    /* Vrai s’il est possible de passer directement de l’année A1 à l’année A2 (de droit ou par sélection) */
    passage(A1, A2) :- droit(A1, A2).
    passage(A1, A2) :- selection(A1, A2).

    /**** parcours(Debut, Fin) ****/
    /* Vrai s’il existe une façon d’aller de l’année Debut à l’année Fin en passant par une suite d’années. */
    parcours(Debut, Fin) :- passage(Debut, Fin).
    parcours(Debut, Fin) :- passage(Debut, Y), parcours(Y, Fin).

    /**** parcours_selectif(Debut, Fin) ****/
    /* vrai s’il existe un parcours de Debut à Fin tel qu’au moins un passage dune année à une autre se fait par sélection. */
    parcours_selectif(Debut, Fin) :- selection(Debut, Fin).
    parcours_selectif(Debut, Fin) :- selection(Debut, Y), parcours(Y, Fin).
    parcours_selectif(Debut, Fin) :- droit(Debut, Y), parcours_selectif(Y, Fin).

    /**** temps_parcours(Debut, Fin, T) ****/
    /* vrai si on peut aller de l'année Debut à l'année Fin en T ans. */
    temps_parcours(Debut, Fin, s(0)) :- passage(Debut, Fin).
    temps_parcours(Debut, Fin, s(T)) :- passage(Debut, X), temps_parcours(X, Fin, T).



    /***** TP1 EXERCICE 4 *****/

    /**** appartient(E,L) ****/
    /* Vrai si E appartient à la liste L */
    appartient(E,[E|_]).
    appartient(E,[_|L]) :- appartient(E,L).

    /**** longueur(L, N)  ****/
    /* Vrai si la longueur de la liste L vaut N */
    longueur([], 0).
    longueur([_|L], s(N)) :- longueur(L, N).

    /**** concat(L1, L2, L) ****/
    /* Vrai si L est la concatenation des listes L1 et L2 */
    concat([], L, L).
    concat([H|L1], L2, [H|L]) :- concat(L1, L2, L).

    /**** supprime(E, L, L1) ****/
    /* Vrai si l'element E appartient à L et n'appartient pas à L1 */
    supprime(E, [E|T], T).
    supprime(E, [H|L], [H|L1]) :- supprime(E, L, L1).

    /**** inverse(L, L1) ****/
    /* Vrai si la liste L1 est l'inverse de la liste L */
    inverse([],[]).
    inverse([H|T], L1) :- inverse(T, X), concat(X, [H], L1).

    /**** infeq(X, Y) ****/
    infeq(X, X).
    infeq(X, s(Y)) :- infeq(X,Y).

    /**** triee(L) ****/
    /* Vrai si L est triée dans l’ordre croissant */
    triee([]).
    triee([_]).
    triee([A,B|C]) :- infeq(A,B), triee([B|C])

    /**** permutation(L, Lp) ****/
    /* Vrai si Lp est une permutation de L */
    permutation([],[]).
    permutation(L,[E|L1]) :- supprime(E,L,L2), permutation(L2,L1).

    /**** tri_naif(L, Lt) ****/
    tri_naif(L, Lt) :- permutation(L, Lt), triee(Lt).


    /***** TP2 EXERCICE 1 *****/
    /**** clause sat(C) ****/
    /* Vrai si au moins un des littéraux de la clause C est vrai */
    p(v).
    n(f).
    clause_sat(p(v)).
    clause_sat(n(f)).
    clause_sat([H|T]) :- clause_sat(H), clause_sat(T).

    /**** Vrai si la formule F représentée comme indiqué ci-dessus est satisfiable ****/
    sat([H|T]) :- sat([H]), sat([T]).


    /***** TP2 EXERICE 2a *****/

    /**** puissance(X, Y, Z) ****/
    /* vrai si, Z = X^Y */
    puissance(_,0,1).
    puissance(X,Y,Z) :- Y1 is Y - 1,  puissance(X,Y1,Z1), Z is Z1 * X.

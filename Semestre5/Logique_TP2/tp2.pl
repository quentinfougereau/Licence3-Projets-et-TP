/**** clause sat(C) ****/
/* Vrai si au moins un des littéraux de la clause C est vrai */
p(v).
n(f).
clause_sat(p(v)).
clause_sat(n(f)).
clause_sat([H|T]) :- clause_sat(H), clause_sat(T).

/**** Vrai si la formule F représentée comme indiqué ci-dessus est satisfiable ****/
sat([H|T]) :- sat([H]), sat([T]).

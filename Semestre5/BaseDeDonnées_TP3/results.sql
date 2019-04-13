-- 2 operations ("select statement" et "table access full")
-- Chaque opération porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
1. SELECT IdP FROM Vente;

-- 2 operations ("select statement" et "INDEX FAST FULL SCAN")
-- Chaque opération porte sur 13 lignes, coûte 2 (0% du CPU) et dure 1 seconde
1. SELECT IdP FROM Produit;

-- Une requête SELECT sur une clé primaire est plus performante que sur une clé étrangère.

-- 2 operations ("select statement" et "table access full")
-- Chaque opération porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
2. SELECT Quantite FROM Vente;

-- 3 operations ("select statement", "hash unique" and "table access full")
-- Chaque opération dure 1 seconde. Les deux premières coûtent 4 (25% du CPU), la troisième coûte 3 (0% du CPU)
3. SELECT DISTINCT Quantite FROM Vente;

-- Utiliser DISTINCT est plus coûteux en termes de nombre d'operations et de coût CPU.

-- 2 operations ("select statement" et "table access full")
-- Chaque opération porte sur 33 lignes, coûte 3 (0% du CPU) et dure 1 seconde
4. SELECT Quantite FROM Vente WHERE Quantite = <cst>; -- Testé avec la valeur 19

-- 2 operations ("SELECT STATEMENT" et "INDEX RANGE SCAN")
-- Chaque opération porte sur 77 lignes, coûte 2 (0% du CPU) et dure 1 seconde
5. SELECT IdP FROM Vente WHERE IdP = <cst>; -- Testé avec la valeur 19

-- Une sélection est plus performante quand elle est faite sur une clé.

-- 3 operations ("SELECT STATEMENT", "SORT AGGREGATE", "INDEX FAST FULL SCAN")
-- La première opération porte sur 1 ligne, coûte 3 (0% du CPU) et dure 1 seconde
-- La seconde opération porte sur 1 ligne
-- La troisième opération porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
6. SELECT COUNT( Quantite ) FROM Vente;

-- 3 operations ("SELECT STATEMENT", "HASH GROUP BY", "TABLE ACCESS FULL")
-- Chaque opération dure 1 seconde. Les deux premières portent sur 25 lignes et coûtent 4 (25% du CPU)
-- La troisième opération porte sur 999 lignes et coûte 3 (0% du CPU)
7. SELECT COUNT( Quantite ) FROM Vente GROUP BY Quantite;

-- Utiliser GROUP BY est coûteux pour le CPU.

-- 6 operations
-- -"SELECT STATEMENT" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"MERGE JOIN" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"TABLE ACCESS BY INDEX ROWID" : porte sur 10 lignes, coûte 2 (0% du CPU) et dure 1 seconde
-- -"INDEX FULL SCAN" : porte sur 10 lignes, coûte 1 (0% du CPU) et dure 1 seconde
-- -"SORT JOIN" : porte sur 13 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 13 lignes, coûte 3 (0% du CPU) et dure 1 seconde
8. SELECT Libelle, PrixAchat, Designation
   FROM PRODUIT, CATEGORIE
   WHERE PRODUIT.CodeCat = CATEGORIE.CodeCat;

9.
CREATE VIEW Requete8 AS
SELECT Libelle, PrixAchat, Designation
FROM PRODUIT, CATEGORIE
WHERE PRODUIT.CodeCat = CATEGORIE.CodeCat;

-- 6 operations :
-- -"SELECT STATEMENT" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"MERGE JOIN" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"TABLE ACCESS BY INDEX ROWID" : porte sur 10 lignes, coûte 2 (0% du CPU) et dure 1 seconde
-- -"INDEX FULL SCAN" : porte sur 10 lignes, coûte 1 (0% du CPU) et dure 1 seconde
-- -"SORT JOIN" : porte sur 13 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 13 lignes, coûte 3 (0% du CPU) et dure 1 seconde
10. SELECT * FROM Requete8;

-- 6 operations :
-- -"SELECT STATEMENT" : porte sur 9 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"MERGE JOIN" : porte sur 9 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"TABLE ACCESS BY INDEX ROWID" : porte sur 10 lignes, coûte 2 (0% du CPU) et dure 1 seconde
-- -"INDEX FULL SCAN" : porte sur 10 lignes, coûte 1 (0% du CPU) et dure 1 seconde
-- -"SORT JOIN" : porte sur 9 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 9 lignes, coûte 3 (0% du CPU) et dure 1 seconde
11. SELECT Libelle, Designation FROM Requete8 WHERE PrixAchat >= 10;

12. -- Les requêtes de questions 8, 10 et 11 sont équivalentes en terme d'optimisation.
-- Elles sont composées de 6 opérations et chacune de ces opérations durent le même temps et coûte le même nombre de coeur du CPU.

-- 6 operations :
-- -"SELECT STATEMENT" : porte sur 30 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"MERGE JOIN" : porte sur 30 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"TABLE ACCESS BY INDEX ROWID" : porte sur 20 lignes, coûte 2 (0% du CPU) et dure 1 seconde
-- -"INDEX FULL SCAN" : porte sur 20 lignes, coûte 1 (0% du CPU) et dure 1 seconde
-- -"SORT JOIN" : porte sur 30 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 30 lignes, coûte 3 (0% du CPU) et dure 1 seconde
13. SELECT Nom, Type, NomLieu FROM MAGASIN, LIEU WHERE MAGASIN.IdL = Lieu.IdL;

-- 6 operations :
-- -"SELECT STATEMENT" : porte sur 30 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"MERGE JOIN" : porte sur 30 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"TABLE ACCESS BY INDEX ROWID" : porte sur 20 lignes, coûte 2 (0% du CPU) et dure 1 seconde
-- -"INDEX FULL SCAN" : porte sur 20 lignes, coûte 1 (0% du CPU) et dure 1 seconde
-- -"SORT JOIN" : porte sur 30 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 30 lignes, coûte 3 (0% du CPU) et dure 1 seconde
14. SELECT Nom, Type, NomLieu FROM MAGASIN JOIN LIEU ON (MAGASIN.IdL = LIEU.IdL);

-- 4 opérations :
-- -"SELECT STATEMENT" : porte sur 1 ligne, coûte 3 (0% du CPU) et dure 1 seconde
-- -"FILTER"
-- -"TABLE ACCESS FULL" : porte sur 30 lignes, coûte 3 (0% du CPU) et dure 1 seconde
-- -"INDEX UNIQUE SCAN" : porte sur 1 ligne, coûte 0 (0% du CPU) et dure 1 seconde
15. SELECT Nom, Type, Nom FROM MAGASIN WHERE MAGASIN.IdL = (SELECT IdL FROM LIEU WHERE LIEU.IdL = MAGASIN.IdL);

-- Parmis les requêtes 13, 14 et 15 :
-- -Les requêtes 13 et 14 sont similaires en terme d'optimisation.
-- -La requête 15 est plus optimisée que les requêtes 13 et 14 car elle n'est composée que de 4 operations (contre 6),
--  et le coût maximal d'une opération et de 3 (contre 6).

16. CREATE INDEX IndexCodeCat ON PRODUIT(CodeCat);

-- 6 operations :
-- -"SELECT STATEMENT" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"MERGE JOIN" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"TABLE ACCESS BY INDEX ROWID" : porte sur 10 lignes, coûte 2 (0% du CPU) et dure 1 seconde
-- -"INDEX FULL SCAN" : porte sur 10 lignes, coûte 1 (0% du CPU) et dure 1 seconde
-- -"SORT JOIN" : porte sur 13 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 13 lignes, coûte 3 (0% du CPU) et dure 1 seconde
17. SELECT * FROM PRODUIT, CATEGORIE WHERE PRODUIT.CodeCat = CATEGORIE.CodeCat;

-- 6 operations :
-- -"SELECT STATEMENT" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"MERGE JOIN" : porte sur 13 lignes, coûte 6 (17% du CPU) et dure 1 seconde
-- -"TABLE ACCESS BY INDEX ROWID" : porte sur 10 lignes, coûte 2 (0% du CPU) et dure 1 seconde
-- -"INDEX FULL SCAN" : porte sur 10 lignes, coûte 1 (0% du CPU) et dure 1 seconde
-- -"SORT JOIN" : porte sur 13 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 13 lignes, coûte 3 (0% du CPU) et dure 1 seconde
18. SELECT * FROM PRODUIT JOIN CATEGORIE ON (PRODUIT.CodeCat = CATEGORIE.CodeCat);

-- 4 opérations :
-- -"SELECT STATEMENT" : porte sur 1 ligne, coûte 3 (0% du CPU) et dure 1 seconde
-- -"FILTER"
-- -"TABLE ACCESS FULL" : porte sur 13 lignes, coûte 3 (0% du CPU) et dure 1 seconde
-- -"INDEX UNIQUE SCAN" : porte sur 1 ligne, coûte 0 (0% du CPU) et dure 1 seconde
19. SELECT * FROM PRODUIT WHERE PRODUIT.CodeCat = (SELECT CodeCat FROM CATEGORIE WHERE CATEGORIE.CodeCat = PRODUIT.CodeCat);

-- Parmis les requêtes 17, 18 et 19 :
-- -Les requêtes 17 et 18 sont similaires en terme d'optimisation.
-- -La requête 19 est plus optimisée que les requêtes 17 et 18 car elle n'est composée que de 4 operations (contre 6),
--  et le coût maximal d'une opération et de 3 (contre 6).

20. DROP INDEX IndexCodeCat;

-- 3 operations :
-- -"SELECT STATEMENT" : porte sur 25 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"HASH GROUP BY" : porte sur 25 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
21. SELECT Quantite, COUNT( Quantite ) FROM Vente GROUP BY Quantite;

-- 5 operations :
-- -"SELECT STATEMENT" : porte sur 25 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"HASH GROUP BY" : porte sur 25 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"VIEW" : porte sur 25 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
22. SELECT Quantite, COUNT( DISTINCT Quantite ) FROM Vente GROUP BY Quantite;

-- 3 operations :
-- -"SELECT STATEMENT" : porte sur 25 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"HASH GROUP BY" : porte sur 25 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
23. SELECT Quantite, COUNT( PrixVenteUnitaire ) FROM Vente GROUP BY Quantite;

-- 5 operations :
-- -"SELECT STATEMENT" : porte sur 1 ligne, coûte 4 (25% du CPU) et dure 1 seconde
-- -"SORT AGGREGATE" : porte sur 1 ligne
-- -"VIEW" : porte sur 816 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"HASH GROUP BY" : porte sur 816 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
24. SELECT COUNT( Quantite ), COUNT( DISTINCT PrixVenteUnitaire ) FROM Vente;

-- 3 operations :
-- -"SELECT STATEMENT" : porte sur 999 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"HASH GROUP BY" : porte sur 999 lignes, coûte 4 (25% du CPU) et dure 1 seconde
-- -"TABLE ACCESS FULL" : porte sur 999 lignes, coûte 3 (0% du CPU) et dure 1 seconde
25. SELECT COUNT( Quantite ), COUNT( PrixVenteUnitaire ) FROM Vente GROUP BY Quantite, PrixVenteUnitaire;

-- Utiliser DISTINCT est plus coûteux en terme du nombre d'opérations.
-- Utiliser la fonction COUNT avec un GROUP BY améliore la performance de la requête.

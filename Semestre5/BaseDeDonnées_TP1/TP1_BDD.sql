01 - SELECT NumVol FROM VOLS WHERE VilleD = 'Paris';
02 - SELECT NumPil FROM VOLS WHERE VilleD NOT LIKE 'Paris' AND VilleA NOT LIKE 'Marseille';
03 - SELECT NumVol FROM VOLS WHERE (DateA - DateD)*24 BETWEEN 2 AND 4;
04 - SELECT NumAv, NomAv FROM AVIONS WHERE NomAv NOT LIKE '%a%' AND NomAv NOT LIKE '%A%';
05 - SELECT NomPil, (to_number(to_char(SYSDATE, 'YYYY')) - NaisPil) AS AGE FROM PILOTES WHERE VillePil = 'Nice' AND (to_number(to_char(SYSDATE, 'YYYY')) - NaisPil) > 35;
06 - SELECT NumVol FROM VOLS WHERE NumAv <> 101 AND NumAv <> 401;
07 - SELECT NumPil, NVL(VillePil, 'Ville inconnue') AS VILLE FROM PILOTES;
08 - SELECT NumVol, VilleD, to_char(DateD, 'DD/MM/YYYY') as DateDep, to_char(DateD, 'HH24:MI') AS HeureDep FROM VOLS WHERE VilleA = 'Marseille' ORDER BY to_char(DateD, 'YYYY-MM-DD') ASC, HeureDep DESC;
09 - SELECT NomAv, SUBSTR(NomAv, 1, DECODE(INSTR(NomAv, ' ', 1, 1)-1, -1, 10, INSTR(NomAv, ' ', 1, 1)-1)) FROM AVIONS;
10 - SELECT COUNT(NumPil) FROM PILOTES WHERE VillePil = 'Nice';
11 - SELECT AVG(to_number(to_char(SYSDATE, 'YYYY')) - NaisPil) FROM PILOTES;
12 - SELECT DISTINCT COUNT(DateA) FROM VOLS;
13 - ------ SELECT COUNT(NumAv) FROM AVIONS WHERE CapAv IS NULL OR CapAv = MIN(CapAv);
14 - SELECT COUNT(*) FROM RESERVATIONS WHERE NumVol = 'V101';
14b - SELECT COUNT(*) FROM RESERVATIONS WHERE NumVol = 'V222';
15 - SELECT DISTINCT VilleD, VilleA FROM VOLS;
16 - SELECT NomCl, Classe FROM CLIENTS, RESERVATIONS WHERE CLIENTS.NumCl = RESERVATIONS.NumCl AND NumVol = 'V790';
17 - SELECT COUNT(NumCl), NumCl FROM RESERVATIONS GROUP BY NumCl HAVING COUNT(NumCl) >= 3;
18 - SELECT NumPil, SUM(DateA-DateD)*24 AS HeuresDeVol FROM VOLS WHERE to_char(DateD, 'MM') = 12 HAVING (SUM(DateA-DateD)*24)>170 GROUP BY NumPil;
19 - SELECT NumCl, SUM(CoeffPrix) FROM RESERVATIONS, DEFCLASSES WHERE RESERVATIONS.NumVol = DEFCLASSES.NumVol GROUP BY NumCl;
20 - SELECT NumVol, MIN(DateD) FROM VOLS GROUP BY NumVol;
21 - SELECT COUNT(NumVol) FROM VOLS WHERE VilleD = 'Paris' AND EXTRACT(month from DateD) = 12;
22 - SELECT DISTINCT (COUNT(NumVol)*100 / (SELECT COUNT(*) FROM VOLS)) AS PrcVolsAssures From VOLS WHERE to_char(DateD, 'MM') = 12 GROUP BY NumVol;
23 - ------
24 - SELECT NumVol FROM VOLS WHERE VilleD = (SELECT VilleA FROM VOLS WHERE NumVol = 'V101') AND VilleA = (SELECT VilleD FROM VOLS WHERE NumVol = 'V101');
25 - SELECT DISTINCT CLIENTS.NumCl, CLIENTS.NomCl FROM CLIENTS, RESERVATIONS WHERE EXISTS(SELECT CLIENTS.NumCl FROM CLIENTS, RESERVATIONS WHERE CLIENTS.NumCl = RESERVATIONS.NumCl AND CLIENTS.NomCl LIKE '%Lorentz%');
26 - SELECT DISTINCT NumPil FROM VOLS WHERE NumPil NOT IN(SELECT NumPil FROM VOLS WHERE VilleD = 'Paris');
27 - ------ SELECT NumAv FROM VOLS WHERE VOLS.NumPil NOT IN(SELECT PILOTES.NumPil FROM VOLS, PILOTES WHERE PILOTES.NumPil = VOLS.NumPil) AND NumPil = '5050';
28 - ------ SELECT NumPil, COUNT(nbAvions) FROM (SELECT NumAv AS nbAvions FROM VOLS, PILOTES WHERE VOLS.NumPil = PILOTES.NumPil), PILOTES GROUP BY NumPil;
29 - SELECT NumVol, NomAv FROM VOLS, AVIONS WHERE AVIONS.NumAv = VOLS.NumAv AND VilleA = 'Marseille';
30 - SELECT Classe, (CapAv * CoeffPlace) as NbPlacesExistant FROM VOLS, DEFCLASSES, AVIONS WHERE VOLS.NumVol = DEFCLASSES.NumVol AND VOLS.NumAv = AVIONS.NumAv AND VOLS.NumVol = 'V240';
31 - SELECT DEFCLASSES.Classe, COUNT(NbPlaces) FROM RESERVATIONS, DEFCLASSES WHERE RESERVATIONS.Classe = DEFCLASSES.Classe AND DEFCLASSES.NumVol = 'V101' GROUP BY DEFCLASSES.Classe;
32 - ------ SELECT DEFCLASSES.Classe, COUNT(NbPlaces) FROM RESERVATIONS, DEFCLASSES WHERE DEFCLASSES.Classe NOT IN (SELECT DEFCLASSES.Classe FROM RESERVATIONS, DEFCLASSES WHERE RESERVATIONS.Classe = DEFCLASSES.Classe AND DEFCLASSES.NumVol = 'V101') GROUP BY DEFCLASSES.Classe;
33 - SELECT COUNT(NumVol), NumPil FROM VOLS HAVING COUNT(NumVol)=(SELECT MAX(COUNT(NumVol)) FROM VOLS GROUP BY NumPil) GROUP BY NumPil;
34 - ------ SELECT COUNT(NbPlaces), NumCl FROM RESERVATIONS HAVING COUNT(NbPlaces)=(SELECT MAX(COUNT(NbPlaces)) FROM RESERVATIONS GROUP BY NumCl) GROUP BY NumCl;
35 - ------ SELECT DISTINCT NumPil, AVIONS.NumAv FROM VOLS, AVIONS WHERE VOLS.NumAv = AVIONS.NumAv AND NomAv = 'Airbus A340';

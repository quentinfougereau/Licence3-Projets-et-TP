CREATE VIEW ReservationsAvecHoraire AS
SELECT NumR, NumC, Reservations.NumT, VilleD, VilleA, Nbplaces, Etapes.DateD, Etapes.DateA
FROM Reservations, Etapes
WHERE Reservations.NumT = Etapes.NumT;

CREATE VIEW Liaison AS
SELECT Trajets.NumT, Reservations.VilleD, Reservations.VilleA, Etapes.DateA, Etapes.DateD
FROM Reservations, Trajets, Etapes
WHERE Trajets.NumT = Reservations.NumT AND Trajets.NumT = Etapes.NumT
ORDER BY Trajets.NumT;

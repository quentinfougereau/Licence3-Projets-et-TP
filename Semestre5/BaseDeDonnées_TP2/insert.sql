INSERT INTO Villes (NomV) VALUES ('Pointe-Noire');
INSERT INTO Villes (NomV) VALUES ('Lamentin');
INSERT INTO Villes (NomV) VALUES ('Pointe-a-Pitre');
INSERT INTO Villes (NomV) VALUES ('Baillif');
INSERT INTO Villes (NomV) VALUES ('Abymes');
INSERT INTO Villes (NomV) VALUES ('Bokit-sur-mer');
INSERT INTO Villes (NomV) VALUES ('Vieux-Habitants');
INSERT INTO Villes (NomV) VALUES ('Sainte-Rose');
INSERT INTO Villes (NomV) VALUES ('Morne-a-Leau');
INSERT INTO Villes (NomV) VALUES ('Capesterre-belle-eau');
INSERT INTO Villes (NomV) VALUES ('Gourbeyre');

INSERT INTO Bus (NumB, CapaciteB) VALUES (1, 50);
INSERT INTO Bus (NumB, CapaciteB) VALUES (2, 60);
INSERT INTO Bus (NumB, CapaciteB) VALUES (3, 55);
INSERT INTO Bus (NumB, CapaciteB) VALUES (4, 40);
INSERT INTO Bus (NumB, CapaciteB) VALUES (5, 40);
INSERT INTO Bus (NumB, CapaciteB) VALUES (6, 30);
INSERT INTO Bus (NumB, CapaciteB) VALUES (7, 70);
INSERT INTO Bus (NumB, CapaciteB) VALUES (8, 55);
INSERT INTO Bus (NumB, CapaciteB) VALUES (9, 55);
INSERT INTO Bus (NumB, CapaciteB) VALUES (10, 40);
INSERT INTO Bus (NumB, CapaciteB) VALUES (11, 60);
INSERT INTO Bus (NumB, CapaciteB) VALUES (12, 60);
INSERT INTO Bus (NumB, CapaciteB) VALUES (13, 50);
INSERT INTO Bus (NumB, CapaciteB) VALUES (14, 50);
INSERT INTO Bus (NumB, CapaciteB) VALUES (15, 50);
INSERT INTO Bus (NumB, CapaciteB) VALUES (16, 40);
INSERT INTO Bus (NumB, CapaciteB) VALUES (17, 40);
INSERT INTO Bus (NumB, CapaciteB) VALUES (18, 55);
INSERT INTO Bus (NumB, CapaciteB) VALUES (19, 70);
INSERT INTO Bus (NumB, CapaciteB) VALUES (20, 70);

INSERT INTO Trajets (NumT, NumB) VALUES (1, 1);
INSERT INTO Trajets (NumT, NumB) VALUES (2, 4);
INSERT INTO Trajets (NumT, NumB) VALUES (3, 5);
INSERT INTO Trajets (NumT, NumB) VALUES (4, 3);
INSERT INTO Trajets (NumT, NumB) VALUES (5, 8);
INSERT INTO Trajets (NumT, NumB) VALUES (6, 10);
INSERT INTO Trajets (NumT, NumB) VALUES (7, 9);
INSERT INTO Trajets (NumT, NumB) VALUES (8, 9);
INSERT INTO Trajets (NumT, NumB) VALUES (9, 11);
INSERT INTO Trajets (NumT, NumB) VALUES (10, 8);
INSERT INTO Trajets (NumT, NumB) VALUES (11, 7);
INSERT INTO Trajets (NumT, NumB) VALUES (12, 19);
INSERT INTO Trajets (NumT, NumB) VALUES (13, 18);
INSERT INTO Trajets (NumT, NumB) VALUES (14, 17);
INSERT INTO Trajets (NumT, NumB) VALUES (15, 20);
INSERT INTO Trajets (NumT, NumB) VALUES (16, 12);
INSERT INTO Trajets (NumT, NumB) VALUES (17, 13);
INSERT INTO Trajets (NumT, NumB) VALUES (18, 7);
INSERT INTO Trajets (NumT, NumB) VALUES (19, 6);
INSERT INTO Trajets (NumT, NumB) VALUES (20, 13);

INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (1, 'Baillif', '02-03-2018 12:05:45', '02-03-2018 12:28:23');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (2, 'Baillif', '02-03-2018 12:45:45', '02-03-2018 12:56:42');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (10, 'Sainte-Rose', '02-03-2018 13:05:45', '02-03-2018 13:17:43');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (6, 'Baillif', '02-03-2018 18:05:45', '02-03-2018 18:20:13');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (7, 'Morne-a-Leau', '03-03-2018 12:01:45', '03-03-2018 12:12:43');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (8, 'Sainte-Rose', '03-03-2018 13:15:01', '03-03-2018 13:21:12');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (9, 'Morne-a-Leau', '03-03-2018 16:22:12', '03-03-2018 12:29:08');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (3, 'Baillif', '04-03-2018 06:45:35', '04-03-2018 07:00:41');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (4, 'Capesterre-belle-eau', '04-03-2018 09:06:13', '04-03-2018 09:21:36');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (5, 'Lamentin', '04-03-2018 12:10:09', '04-03-2018 12:15:12');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (11, 'Morne-a-Leau', '04-03-2018 13:07:54', '04-03-2018 13:14:14');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (12, 'Baillif', '05-03-2018 08:05:15', '05-03-2018 08:21:12');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (13, 'Sainte-Rose', '05-03-2018 11:50:42', '05-03-2018 11:56:34');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (14, 'Gourbeyre', '05-03-2018 17:20:45', '05-03-2018 17:28:47');
INSERT INTO Etapes (NumT, NomV, DateA, DateD) VALUES (15, 'Vieux-Habitants', '05-03-2018 19:02:54', '05-03-2018 19:03:12');

INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Baillif', 'Gourbeyre', 2.20);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Sainte-Rose', 'Gourbeyre', 4.60);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Baillif', 'Morne-a-Leau', 2.20);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Baillif', 'Capesterre-belle-eau', 3.20);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Lamentin', 'Sainte-Rose', 2.20);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Vieux-Habitants', 'Gourbeyre', 1.60);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Sainte-Rose', 'Pointe-a-Pitre', 2.40);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Sainte-Rose', 'Abymes', 2.20);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Sainte-Rose', 'Gourbeyre', 2.20);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Pointe-Noire', 'Abymes', 2.20);
INSERT INTO Tarifs (VilleD, VilleA, Prix) VALUES ('Abymes', 'Pointe-a-Pitre', 0.80);

INSERT INTO Clients (NumC, NomC, AdresseC) VALUES (1, 'Ildevert Oguenin', 'Devant chez didine');
INSERT INTO Clients (NumC, NomC, AdresseC) VALUES (2, 'Isidor Malafe', '4 lot des palmiers');
INSERT INTO Clients (NumC, NomC, AdresseC) VALUES (3, 'Jacky Boboy', 'Adan 4 croise');
INSERT INTO Clients (NumC, NomC, AdresseC) VALUES (4, 'Meddy Koko', '46 résidence de Bellecour');
INSERT INTO Clients (NumC, NomC, AdresseC) VALUES (5, 'Joel Belamour', '2 sik a canne');
INSERT INTO Clients (NumC, NomC, AdresseC) VALUES (6, 'Nadine Gwotet', 'Cote Ildev');
INSERT INTO Clients (NumC, NomC, AdresseC) VALUES (7, 'Gardy Rokotomalala', 'Derrié la crete, caz bleue a gauche. Bah y pa la, continye tout dwet deyé kokotié la');

INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (1, 1, 2, 'Baillif', 'Morne-a-Leau', 12);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (2, 2, 3, 'Lamentin', 'Sainte-Rose', 2);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (3, 2, 5, 'Sainte-Rose', 'Vieux-Habitants', 8);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (4, 1, 10, 'Baillif', 'Gourbeyre', 30);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (5, 3, 6, 'Sainte-Rose', 'Pointe-a-Pitre', 4);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (6, 4, 7, 'Baillif', 'Vieux-Habitants', 4);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (7, 7, 1, 'Vieux-Habitants', 'Gourbeyre', 13);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (8, 6, 12, 'Sainte-Rose', 'Lamentin', 21);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (9, 6, 18, 'Baillif', 'Gourbeyre', 9);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (10, 5, 19, 'Lamentin', 'Gourbeyre', 13);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (11, 4, 16, 'Pointe-a-Pitre', 'Gourbeyre', 18);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (12, 2, 13, 'Baillif', 'Abymes', 15);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (13, 7, 15, 'Vieux-Habitants', 'Gourbeyre', 22);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (14, 7, 15, 'Gourbeyre', 'Gourbeyre', 22);
INSERT INTO Reservations (NumR, NumC, NumT, VilleD, VilleA, NbPlaces) VALUES (15, 7, 15, 'Pointe-Noire', 'Gourbeyre', 0);

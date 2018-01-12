DROP SCHEMA `bd_systdist` ;
CREATE SCHEMA `bd_systdist`;

CREATE TABLE `bd_systdist`.`patient` (
  `idPatient` INT NOT NULL AUTO_INCREMENT,
  `Nom` VARCHAR(45) NULL,
  `Prenom` VARCHAR(45) NULL,
  `Login` VARCHAR(45) NULL,
  UNIQUE(`Login`),
  PRIMARY KEY (`idPatient`));

CREATE TABLE `bd_systdist`.`medecin` (
  `idMedecin` INT NOT NULL,
  `Nom` VARCHAR(45) NULL,
  `Prenom` VARCHAR(45) NULL,
  `Login` VARCHAR(45) NULL,
  UNIQUE(`Login`),
  PRIMARY KEY (`idMedecin`));

CREATE TABLE `bd_systdist`.`demande` (
  `idDemande` INT NOT NULL AUTO_INCREMENT,
  `RefPatient` INT NULL,
  `RefMedecin` INT NULL,
  `DateHeureDemande` DATETIME NULL,
  `Urgent` BOOLEAN NULL,
  `ResultatsDisponibles` BOOLEAN NOT NULL,
  PRIMARY KEY (`idDemande`),
  CONSTRAINT demande_patient_ref FOREIGN KEY (RefPatient)
	REFERENCES Patient(idPatient),
  CONSTRAINT demande_medecin_ref FOREIGN KEY(RefMedecin)
	REFERENCES Medecin(idMedecin));

CREATE TABLE `bd_systdist`.`analyses` (
  `idAnalyses` INT NOT NULL AUTO_INCREMENT,
  `Item` VARCHAR(45) NULL,
  `Valeur` VARCHAR(45) NULL,
  `Demande` INT NOT NULL,
  PRIMARY KEY (`idAnalyses`),
  CONSTRAINT analyses_demande_ref FOREIGN KEY(Demande)
	REFERENCES Demande(idDemande));

CREATE TABLE `bd_systdist`.`logs` (
  `idLogs` INT NOT NULL AUTO_INCREMENT,
  `Infos` VARCHAR(45) NULL,
  PRIMARY KEY (`idLogs`));


INSERT INTO bd_systdist.medecin(idMedecin, Nom, Prenom, Login) VALUES('1', 'Dimartino', 'Philippe', 'philippedimartino');
INSERT INTO bd_systdist.medecin(idMedecin, Nom, Prenom, Login) VALUES('2', 'Fourgon', 'Maxime', 'maximefourgon');

/* INSERT INTO bd_systdist.patient(idPatient, Nom, Prenom, Login) VALUES('1', 'Serrhini', 'Souad', 'sousou'); */

COMMIT;
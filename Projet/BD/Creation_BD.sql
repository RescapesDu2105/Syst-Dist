DROP SCHEMA `bd_systdist` ;
CREATE SCHEMA `bd_systdist`;

CREATE TABLE `bd_systdist`.`patient` (
  `idPatient` INT NOT NULL,
  `Nom` VARCHAR(45) NULL,
  `Prenom` VARCHAR(45) NULL,
  `Login` VARCHAR(45) NULL,
  PRIMARY KEY (`idPatient`));

CREATE TABLE `bd_systdist`.`medecin` (
  `idMedecin` INT NOT NULL,
  `Nom` VARCHAR(45) NULL,
  `Prenom` VARCHAR(45) NULL,
  `Login` VARCHAR(45) NULL,
  PRIMARY KEY (`idMedecin`));

CREATE TABLE `bd_systdist`.`demande` (
  `idDemande` INT NOT NULL,
  `RefPatient` INT NULL,
  `RefMedecin` INT NULL,
  `DateHeureDemande` DATETIME NULL,
  `Urgent` INT NULL,
  PRIMARY KEY (`idDemande`),
  CONSTRAINT demande_patient_ref FOREIGN KEY (RefPatient)
	REFERENCES Patient(idPatient),
  CONSTRAINT demande_medecin_ref FOREIGN KEY(RefMedecin)
	REFERENCES Medecin(idMedecin));

CREATE TABLE `bd_systdist`.`analyses` (
  `idAnalyses` INT NOT NULL,
  `Item` VARCHAR(45) NULL,
  `Valeur` VARCHAR(45) NULL,
  PRIMARY KEY (`idAnalyses`));

CREATE TABLE `bd_systdist`.`logs` (
  `idLogs` INT NOT NULL,
  `Infos` VARCHAR(45) NULL,
  PRIMARY KEY (`idLogs`));


INSERT INTO bd_systdist.medecin VALUES('1', 'Dimartino', 'Philippe', 'philippedimartino');

INSERT INTO bd_systdist.patient VALUES('1', 'Serrhini', 'Souad', 'sousou');

COMMIT;
SELECT * FROM bd_systdist.medecin;

SELECT * FROM Medecin m WHERE login = 'philippedimartino';
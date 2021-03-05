DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `document`;
DROP TABLE IF EXISTS `type`;
DROP TABLE IF EXISTS `livre`;
DROP TABLE IF EXISTS `dvd`;
DROP TABLE IF EXISTS `cd`;

CREATE TABLE `user` (
`id_user` INT PRIMARY KEY AUTO_INCREMENT,
`login` VARCHAR(20) NOT NULL UNIQUE,
`pwd` VARCHAR(256) NOT NULL,
`name` VARCHAR(30),
`age` INT UNSIGNED,
`mail` VARCHAR(30),
`address` VARCHAR(30),
`isAdmin` TINYINT(1) NOT NULL
);

CREATE TABLE `document` (
`id_doc` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`description` VARCHAR(256),
`id_borrower` INT,
`id_type` INT NOT NULL
);

CREATE TABLE `livre` (
`id_livre` INT PRIMARY KEY,
`auteur` VARCHAR(30),
`nbPage` INT UNSIGNED
);

CREATE TABLE `dvd` (
`id_dvd` INT PRIMARY KEY,
`realisateur` VARCHAR(30),
`release_year` INT,
`duree_min` INT
);

CREATE TABLE `cd` (
`id_cd` INT PRIMARY KEY
);

CREATE TABLE `type` (
`id_type` INT PRIMARY KEY AUTO_INCREMENT,
`typeName` VARCHAR(20) NOT NULL
);

ALTER TABLE `document`
ADD CONSTRAINT `FK_document_user`
	FOREIGN KEY(`id_borrower`)
	REFERENCES `user`(`id_user`)
	ON DELETE SET NULL ON UPDATE CASCADE,
ADD CONSTRAINT `FK_document_type`
	FOREIGN KEY(`id_type`)
	REFERENCES `type`(`id_type`)
	ON DELETE CASCADE
;

ALTER TABLE `livre`
ADD CONSTRAINT `FK_livre_doc`
	FOREIGN KEY(`id_livre`)
	REFERENCES `document`(`id_doc`)
	ON DELETE CASCADE
;

ALTER TABLE `dvd`
ADD CONSTRAINT `FK_dvd_doc`
	FOREIGN KEY(`id_dvd`)
	REFERENCES `document`(`id_doc`)
	ON DELETE CASCADE
;

ALTER TABLE `cd`
ADD CONSTRAINT `FK_cd_doc`
	FOREIGN KEY(`id_cd`)
	REFERENCES `document`(`id_doc`)
	ON DELETE CASCADE
;

INSERT INTO `user`(`login`,`pwd`,`isAdmin`) VALUES("Admin","Admin",1);
INSERT INTO `user`(`login`,`pwd`,`name`,`age`,`mail`,`isAdmin`) 
VALUES("Lin","Lin","Xiumin",20,"Xiumin.lin@laposte.net",0);

INSERT INTO `type`(`typeName`) VALUES("Livre");
INSERT INTO `type`(`typeName`) VALUES("DVD");
INSERT INTO `type`(`typeName`) VALUES("CD");

INSERT INTO `document`(`title`,`description`,`id_type`) 
VALUES("Le Parfum","Le Parfum, sous-titré Histoire d'un meurtrier",1);
INSERT INTO `document`(`title`,`id_type`) VALUES("Les Évadés",2);
INSERT INTO `document`(`title`,`id_type`) VALUES("Un CD",3);

INSERT INTO `livre` VALUES(1,"Patrick Susking",300);
INSERT INTO `dvd` VALUES(2,"Frank Darabont",1994,142);
INSERT INTO `cd` VALUES(3);

COMMIT;
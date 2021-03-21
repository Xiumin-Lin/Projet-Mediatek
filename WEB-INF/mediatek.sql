DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `document`;
DROP TABLE IF EXISTS `type`;
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `dvd`;
DROP TABLE IF EXISTS `cd`;

CREATE TABLE `user` (
`id_user` INT PRIMARY KEY AUTO_INCREMENT,
`login` VARCHAR(50) NOT NULL UNIQUE,
`pwd` VARCHAR(256) NOT NULL,
`name` VARCHAR(30),
`age` INT UNSIGNED,
`address` VARCHAR(50),
`isAdmin` TINYINT(1) NOT NULL
);

CREATE TABLE `document` (
`id_doc` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`description` VARCHAR(256),
`id_borrower` INT,
`id_type` INT NOT NULL
);

CREATE TABLE `book` (
`id_book` INT PRIMARY KEY,
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
`id_cd` INT PRIMARY KEY,
`artist` VARCHAR(30)
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

ALTER TABLE `book`
ADD CONSTRAINT `FK_book_doc`
	FOREIGN KEY(`id_book`)
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
-- INSERT USER
INSERT INTO `user`(`login`,`pwd`,`isAdmin`) VALUES("admin@mediatek.com","admin",1);
INSERT INTO `user`(`login`,`pwd`,`name`,`age`,`isAdmin`) VALUES("xiumin.lin@laposte.net","1234","Xiumin LIN",20,0);
INSERT INTO `user`(`login`,`pwd`,`name`,`age`,`isAdmin`) VALUES("steven-van@outlook.fr","56782","Steven VAN",19,0);
INSERT INTO `user`(`login`,`pwd`,`name`,`age`,`isAdmin`) VALUES("harry.potter@hogwarts.com","gryffindor","Harry POTTER", 12,0);
INSERT INTO `user`(`login`,`pwd`,`name`,`age`,`isAdmin`) VALUES("tony.stark@starkindustries.com","ironman","Tony STARK", 53,0);
INSERT INTO `user`(`login`,`pwd`,`name`,`age`,`isAdmin`) VALUES("michael.jackson@gmail.com","kingofpop","Michael JACKSON", 50,0);
-- INSERT TYPE
INSERT INTO `type`(`typeName`) VALUES("book");
INSERT INTO `type`(`typeName`) VALUES("dvd");
INSERT INTO `type`(`typeName`) VALUES("cd");
-- INSERT DOCUMENT
INSERT INTO `document`(`title`,`description`, `id_borrower`,`id_type`) 
VALUES("Harry Potter and the Philosopher's Stone","First Harry Potter novel", 4 , 1);
INSERT INTO `document`(`title`,`description`,`id_type`) 
VALUES("Harry Potter and the Chamber of Secrets","Second Harry Potter novel", 1);
INSERT INTO `document`(`title`,`description`,`id_type`) 
VALUES("Harry Potter and the Prisoner of Azkaban","Third Harry Potter novel", 1);
INSERT INTO `document`(`title`, `description`, `id_borrower`, `id_type`) VALUES("Iron Man", "First Iron Man movie", 5, 2);
INSERT INTO `document`(`title`, `description`, `id_type`) VALUES("Iron Man 2", "Second Iron Man movie", 2);
INSERT INTO `document`(`title`, `description`, `id_type`) VALUES("Iron Man 3", "Third Iron Man movie", 2);
INSERT INTO `document`(`title`,`id_borrower`, `id_type`) VALUES("Thriller 25", 6, 3);
INSERT INTO `document`(`title`, `id_type`) VALUES("Bad", 3);
INSERT INTO `document`(`title`, `id_type`) VALUES("Off the Wall", 3);
-- INSERT BOOK
INSERT INTO `book` VALUES(1,"J.K. Rowling",308);
INSERT INTO `book` VALUES(2,"J.K. Rowling",384);
INSERT INTO `book` VALUES(3,"J.K. Rowling",480);
-- INSERT DVD
INSERT INTO `dvd` VALUES(4,"Jon Favreau", 2008, 126);
INSERT INTO `dvd` VALUES(5,"Jon Favreau", 2010, 125);
INSERT INTO `dvd` VALUES(6,"Shane Black", 2013, 130);
-- INSERT CD
INSERT INTO `cd` VALUES(7, "Michael Jackson");
INSERT INTO `cd` VALUES(8, "Michael Jackson");
INSERT INTO `cd` VALUES(9, "Michael Jackson");

COMMIT;
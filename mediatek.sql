DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `document`;
DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `user` (
`id_user` INT PRIMARY KEY,
`login` VARCHAR(20) NOT NULL UNIQUE,
`pwd` VARCHAR(256) NOT NULL,
`name` VARCHAR(30),
`age` INT,
`mail` VARCHAR(30),
`address` VARCHAR(30),
`isAdmin` TINYINT(1) DEFAULT 1
);

CREATE TABLE `document` (
`id_doc` INT PRIMARY KEY,
`label` VARCHAR(50) NOT NULL,
`description` VARCHAR(256),
`type` VARCHAR(10) NOT NULL,
`id_borrow` INT
);

CREATE TABLE `borrow` (
`id_borrow` INT PRIMARY KEY,
`borrow_date` DATE NOT NULL,
`id_user` INT NOT NULL
);

ALTER TABLE `document`
ADD CONSTRAINT `FK_document_borrow`
	FOREIGN KEY(`id_borrow`)
	REFERENCES `borrow`(`id_borrow`)
	ON DELETE CASCADE
;

ALTER TABLE `borrow`
ADD CONSTRAINT `FK_borrow_user`
	FOREIGN KEY(`id_user`)
	REFERENCES `user`(`id_user`)
	ON DELETE CASCADE
;

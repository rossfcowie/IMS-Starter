CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    `UserID` int(11) not null unique,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) not NULL,
    `value` Double not NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders` (
   `id` INT(11) NOT NULL AUTO_INCREMENT,
   `CustomerID` INT(11) NOT NULL,
    PRIMARY KEY (`id`)
);
Create table if not exists `ims`.`orderItems`(
   `ItemID` INT(11) NOT NULL,
   `OrderID` INT(11) NOT NULL
);
Create table if not exists `ims`.`Users`(
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `Username` VARCHAR(40) DEFAULT NULL,
 `Password` VARCHAR(40) DEFAULT NULL,
 `permissions` int default 0
);
Create table if not exists `ims`.`ItemEdits`(
`EditorID` INT(11) not null,
`ItemID` INT(11) not null,
`ChangeType` char(6) default null
);
Create table if not exists `ims`.`OrderEdits`(
`EditorID` INT(11) not null,
`OrderID` INT(11) not null,
`ChangeType` char(6) default null
);
Create table if not exists `ims`.`CustomerEdits`(
`EditorID` INT(11) not null,
`CustomerID` INT(11) not null,
`ChangeType` char(6) default null
);
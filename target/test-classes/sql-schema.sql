DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `orderitems`;
DROP TABLE IF EXISTS `ItemEdits`;
DROP TABLE IF EXISTS `OrderEdits`;
DROP TABLE IF EXISTS `CustomerEdits`;
DROP TABLE IF EXISTS `Users`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    `UserID` int(11) not null,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) not NULL,
    `value` Double not NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `orders` (
   `id` INT(11) NOT NULL AUTO_INCREMENT,
   `CustomerID` INT(11) NOT NULL,
    PRIMARY KEY (`id`)
);
Create table if not exists `orderItems`(
   `ItemID` INT(11) NOT NULL,
   `OrderID` INT(11) NOT NULL
);

Create table if not exists `Users`(
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `Username` VARCHAR(40) DEFAULT NULL,
 `Password` VARCHAR(40) DEFAULT NULL,
 `permissions` int default 0,
 PRIMARY KEY (`id`)
);

Create table if not exists `ItemEdits`(
`EditorID` INT(11) not null,
`ItemID` INT(11) not null,
`ChangeType` char(6) default null
);
Create table if not exists `OrderEdits`(
`EditorID` INT(11) not null,
`OrderID` INT(11) not null,
`ChangeType` char(6) default null
);
Create table if not exists `CustomerEdits`(
`EditorID` INT(11) not null,
`CustomerID` INT(11) not null,
`ChangeType` char(6) default null
);
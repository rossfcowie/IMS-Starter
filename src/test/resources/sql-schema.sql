DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `orderitems`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) not NULL,
    `value` float not NULL,
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
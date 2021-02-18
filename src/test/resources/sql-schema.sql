DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `orders`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `orders` (
   `id` INT(11) NOT NULL,
   `CustomerID` INT(11) NOT NULL,
   `ItemID` INT(11)
);
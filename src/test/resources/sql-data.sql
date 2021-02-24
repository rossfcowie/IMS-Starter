INSERT INTO `Users` (`Username`,`Password`,`permissions`) values ("admin","admin",4);
INSERT INTO `customers` (`first_name`, `surname`,`UserID`) VALUES ('jordan', 'harrison',1);
INSERT INTO `items` (`name`, `value`) VALUES ('Carrot', 1.25);
INSERT INTO `orders` ( `CustomerID`) VALUES (1);
INSERT INTO `orderItems` ( `ItemID`,`OrderID`) VALUES (1,1);


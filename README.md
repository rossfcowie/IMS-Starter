Coverage: 80%
# IMS

The inventory management system is designed to allow a company in control of a warehouse of goods to manage what items are sold and their value, as well as allowing customers to create orders for items using their own user accounts.  Additinally changes made to the system can be tracked to a specific user so management can trace any issues that occur.

## Getting Started
Upon startup you will be prompted to log into the program, by default the system will come with one user account.

Username:admin
Password:admin

This acocunt is a system admin account and can be used to contol the entire database.  Once you have loged in you will be presented with options to view or edit the customers, items, order or users as well as an option to exit the program.  It is recomended that to secure the admin account you enter the user domain and update account id 1(the admin account) to have a new username and password.

In order to do this first when the screen presents you with-

`Which entity would you like to use?`
`CUSTOMER: Information about customers`
`ITEM: Individual Items`
`ORDER: Purchases of items`
`STOP: To close the application`
`USER: Information about user accounts`

Enter user and it will bring you to this screen,

`What would you like to do with user:`
`CREATE: To save a new entity into the database`
`READ: To read an entity from the database`
`UPDATE: To change an entity already in the database`
`DELETE: To remove an entity from the database`
`RETURN: To return to domain selection`

Enter update then it will ask for the ID of the user to update, as the admin acount is the first one(id = 1) enter 1. 
It will then ask for a new username for the admin acount, enter the admin acount name of your choice.
Next it will ask for the password for this user, set it to something memorable and keep it secure from others.
Finally it will ask for the level of permission for this user.  Keep it set to 4(System admin) as you will always want to have a system admin acount to create new users.

Once you have secured the admin acount you can start creating more accounts.

### Prerequisites

You will require Mysql server 5.7 or greater, and Java 1.8 or greater in order to run this program.

### Installing

Download the git repository and place it in it's own folder.  From the command line enter the IMS-Starter/target folder and run the command 

`java -jar ims-0.0.1-jar-with-dependencies.jar`

This will open the program and you will be presented with the login screen.  Enter the username and password for the admin account as instructed in the getting started section above.

An alternative is provided in the form of a batch file which will perform the above steps for you and bring you to the login screen on a command line. 

## Running the tests

Tests can be run by opening a command line inside the IMS-Starter folder and running the command `mvn test`  this will require that you have maven installed on your machine.

### Unit Tests 

Unit tests are performed for each of the DAO, editDAO and controller classes

These test the full functionality of each of these, as they handle the heavy lifting of the program in regards to accessing and manipulating the database, ensuring that each of them work was paramount to the programs accuracy.  Additonally tests were performed on the Action and Domain classes to make sure that they were returning the correct value when called to get one from a string value.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Jordan Harrison** - *IMS-Starter author* [Jharry444](https://github.com/JHarry444)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* Thank you to Jordan Harrison for the IMS-starter which made creation of the full program far easier as it served as both foundation and scaffolding for my own additions.
* Thanks also to Edward Reynolds for his help when things weren't working and for beating me in the head with order lines until I added the orderitems table.  It was far better, sorry for being obstinate.
* And of course thanks to my chair for keeping me up and having my back through all of this.

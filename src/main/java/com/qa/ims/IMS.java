package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.controller.UserController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.CustomerEditDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.ItemEditDAO;
import com.qa.ims.persistence.dao.LoginDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderEditsDAO;
import com.qa.ims.persistence.dao.UserDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class IMS {

	public static final Logger LOGGER = LogManager.getLogger();
	public static User userLogin = null;
	private final CustomerController customers;
	private final ItemController items;
	private final Utils utils;
	private final OrderController orders;
	private final UserController users;
	private final LoginDAO login = new LoginDAO();

	public IMS() {
		this.utils = new Utils();
		final CustomerDAO custDAO = new CustomerDAO();
		final CustomerEditDAO customerEDAO = new CustomerEditDAO();
		final ItemDAO itemDAO = new ItemDAO();
		final ItemEditDAO itemEDAO = new ItemEditDAO();
		final OrderDAO orderDAO = new OrderDAO();
		final UserDAO userDAO = new UserDAO();
		final OrderEditsDAO orderEDAO = new OrderEditsDAO();
		this.customers = new CustomerController(custDAO, utils,customerEDAO);
		this.orders = new OrderController(orderDAO, utils,orderEDAO);
		this.items = new ItemController(itemDAO, utils,itemEDAO);
		this.users = new UserController(userDAO,utils);
	}


	public void imsSystem() {
		LOGGER.info("Welcome to the Inventory Management System!");
		DBUtils.connect();
		DBUtils.getInstance().init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");
		do {
			LOGGER.info("Please enter your username.");
			String username = utils.getString();
			LOGGER.info("Please enter your password.");
			String password = utils.getString();
			userLogin = this.login.attemptLogin(username, password);
		} while (null == userLogin);
		Domain domain = null;
		int permissionLevel = userLogin.getPermission();
		do {
			
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains(permissionLevel);
			domain = Domain.getDomain(utils, permissionLevel);
			domainAction(domain);
		} while (domain != Domain.STOP);

		tearDown();
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?,?> active = null;
			switch (domain) {
			case CUSTOMER:
				active = this.customers;
				break;
			case ITEM:
				active = this.items;
				break;
			case ORDER:
				active = this.orders;
				break;
			case USER:
				active = this.users;
				break;
			case STOP:
				return;
			default:
				break;
			}

			LOGGER.info(() -> "What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions(userLogin.getPermission(),domain);
			Action action = Action.getAction(utils,userLogin.getPermission(),domain);

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?,?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case EDITS:
			crudController.readEdits();
		case RETURN:
			break;
		default:
			break;
		}
	}

	private void tearDown() {
		utils.tearDown();
	}
}

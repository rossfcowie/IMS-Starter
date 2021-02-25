package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.UserDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.Utils;

public class UserController implements CrudController<User, User>{

	public static final Logger LOGGER = LogManager.getLogger();

	private UserDAO userDAO;
	private Utils utils;
	
	public UserController(UserDAO userDAO, Utils utils) {
		this.userDAO = userDAO;
		this.utils = utils;
	}

	@Override
	public List<User> readAll() {
		List<User> users = userDAO.readAll();
		for (User user : users) {
			LOGGER.info(user);
		}
		return users;
	}

	@Override
	public User create() {
		LOGGER.info("Please enter the username");
		String name = utils.getString();
		LOGGER.info("Please enter the permission level");
		Long level = utils.getPermissions();
		User user = userDAO.create(new User(name, level));
		LOGGER.info("User" + name + "created, password set to default 'password'");
		return user;
	}

	@Override
	public User update() {
		LOGGER.info("Please enter the id of the user you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please the new username for this user");
		String username = utils.getString();
		LOGGER.info("Please the new password for this user");
		String password = utils.getString();
		LOGGER.info("Please enter the level of access for the user");
		Long permissions = utils.getPermissions();
		User user = userDAO.update(new User(id, username, password,permissions));
		LOGGER.info("User Updated");
		return user;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the user you would like to delete");
		Long id = utils.getLong();
		return userDAO.delete(id);
	}

	@Override
	public List<User> readEdits() {
		// Edits to users are not tracked and therefore this cannot be used
		LOGGER.info("It is not possible to track modifications to users. Sorry.");
		return null;
	}

}

package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.UserDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.Utils;

public class UserController implements CrudController<User>{

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
		LOGGER.info("User" + user.getUsername() + "created, password set to default 'password'");
		return user;
	}

	@Override
	public User update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}

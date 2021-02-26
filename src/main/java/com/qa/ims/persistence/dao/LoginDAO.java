package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class LoginDAO{

	public static final Logger LOGGER = LogManager.getLogger();

	public User modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		Long permission = resultSet.getLong("permissions");
		return new User(id,username,password,permission);
	}
	
	public User attemptLogin(String Username, String Password) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users where username =? and password = ?");) {
			statement.setString(1, Username);
			statement.setString(2, Password);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return modelFromResultSet(resultSet);
			}
		}catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


}

package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.exceptions.CustomerNotFoundException;
import com.qa.ims.exceptions.UserNotFoundException;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class UserDAO implements Dao<User> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<User> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM users");) {
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				users.add(modelFromResultSet(resultSet));
			}
			return users;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public User read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				if (resultSet.next()) {
					return modelFromResultSet(resultSet);
				} else {
					throw new UserNotFoundException();
				}
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;

	}

	public User readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Users ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public User create(User t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO Users(username, password,permissions) VALUES (?, ?,?)");) {
			statement.setString(1, t.getUsername());
			statement.setString(2, t.getPassword());
			statement.setLong(3, t.getPermission());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public User update(User t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE Users SET username = ?, password = ? WHERE id = ?");) {
			statement.setString(1, t.getUsername());
			statement.setString(2, t.getPassword());
			statement.setLong(3, t.getId());
			if(statement.executeUpdate() == 1) {
				return read(t.getId());
			}else{
				throw new UserNotFoundException();
			}
			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE id = ?");) {
			statement.setLong(1, id);
			if(statement.executeUpdate() == 1) {
				return 1;
			}else{
				throw new UserNotFoundException();
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public User modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		Long permission = resultSet.getLong("permissions");
		return new User(id, username, password, permission);
	}

}

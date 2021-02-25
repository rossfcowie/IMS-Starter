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

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.CustomerEdit;
import com.qa.ims.persistence.domain.OrderEdit;
import com.qa.ims.utils.DBUtils;

public class CustomerEditDAO {

public static final Logger LOGGER = LogManager.getLogger();
	
	public List<CustomerEdit> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select users.Username, customerEdits.CustomerID, customerEdits.ChangeType from `customerEdits` join `users` on customerEdits.editorID = users.id");) {
			List<CustomerEdit> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	public CustomerEdit modelFromResultSet(ResultSet resultSet) throws SQLException {
		String Name = resultSet.getString("Username");
		Long CustomerID = resultSet.getLong("CustomerID");
		String change = resultSet.getString("ChangeType");
		CustomerEdit finished = new CustomerEdit(Name,change,CustomerID);

		return finished;
	}
	
	public Customer recordCreate(Customer t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CustomerEdits(EditorID, CustomerID,ChangeType) VALUES (?,?,'Create')");) {
			statement.setLong(1, IMS.userLogin.getId());
			statement.setLong(2, t.getId());
			statement.execute();
			return t;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	public Customer recordUpdate(Customer t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CustomerEdits(EditorID, CustomerID,ChangeType) VALUES (?,?,'Update')");) {
			statement.setLong(1, IMS.userLogin.getId());
			statement.setLong(2, t.getId());
			statement.execute();
			return t;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public int recordDelete(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CustomerEdits(EditorID, CustomerID,ChangeType) VALUES (?,?,'Delete')");) {
			statement.setLong(1, IMS.userLogin.getId());
			statement.setLong(2, id);
			statement.execute();
			return 1;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		
		return 0;
	}
}

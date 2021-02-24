package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	
}

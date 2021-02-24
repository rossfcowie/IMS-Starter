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

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderEdit;
import com.qa.ims.utils.DBUtils;

public class OrderEditsDAO {

	public static final Logger LOGGER = LogManager.getLogger();
	
	public List<OrderEdit> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select users.Username, orderedits.OrderID, orderedits.ChangeType from `orderedits` join `users` on orderedits.editorID = users.id");) {
			
			List<OrderEdit> orders = new ArrayList<>();
			System.out.println(resultSet.toString());
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

	
	public OrderEdit modelFromResultSet(ResultSet resultSet) throws SQLException {
		String Name = resultSet.getString("Username");
		Long OrderID = resultSet.getLong("OrderID");
		String change = resultSet.getString("ChangeType");
		OrderEdit finished = new OrderEdit(Name,change,OrderID);
		System.out.println(finished.toString());
		
		return finished;
	}
	
	
}

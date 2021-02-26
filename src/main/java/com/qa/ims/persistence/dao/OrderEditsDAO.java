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
	
	public Order recordCreate(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO OrderEdits(EditorID, OrderID,ChangeType) VALUES (?,?,'Create')");) {
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
	
	public Order recordAdd(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO OrderEdits(EditorID, OrderID,ChangeType) VALUES (?,?,'Add')");) {
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
	
	public Order recordRemove(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO OrderEdits(EditorID, OrderID,ChangeType) VALUES (?,?,'Remove')");) {
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
				PreparedStatement statement = connection.prepareStatement("INSERT INTO OrderEdits(EditorID, OrderID,ChangeType) VALUES (?,?,'Delete')");) {
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

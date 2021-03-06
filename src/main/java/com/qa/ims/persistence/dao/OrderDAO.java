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
import com.qa.ims.exceptions.OrderNotFoundException;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	
	private CustomerDAO customerDAO = new CustomerDAO();
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders group by id");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	public List<Order> readAsCustomer() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where CustomerID =" + customerDAO.readFromUser(IMS.userLogin.getId()).getId() +  " group by id");) {
			
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				if (resultSet.next()) {
					return modelFromResultSet(resultSet);
				} else {
					throw new OrderNotFoundException();
				}
			}

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(CustomerID) VALUES (?)");) {
			statement.setLong(1, t.getCustomerID());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Add item to order
	@Override
	public Order update(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderitems(ItemID,OrderID) VALUES (?,?)");) {
			statement.setLong(1, t.getItemIDs().get(0));
			statement.setLong(2, t.getId());
			if(statement.executeUpdate() == 1) {
				return read(t.getId());
			}else{
				throw new OrderNotFoundException();
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	


	// Delete item from order
	public Order update(Long oid, List<Long> itemIDs) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orderitems WHERE OrderID = ? and ItemID = ?");) {
			statement.setLong(1, oid);
			statement.setLong(2, itemIDs.get(0));
			if(statement.executeUpdate() == 1) {
				return read(oid);
			}else{
				throw new OrderNotFoundException();
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
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			if(statement.executeUpdate() == 1) {
				
				return 1;
			}else{
				throw new OrderNotFoundException();
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
public boolean checkAcceptableID(Long ID) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT id FROM orders WHERE CustomerID = ?");) {
		statement.setLong(1, customerDAO.readFromUser(IMS.userLogin.getId()).getId());
		try (ResultSet rs = statement.executeQuery();) {
			while (rs.next()) {
				if(rs.getLong("id") == ID) {
					
					return true;
				}
			}
			LOGGER.info("This order is not connected to your acocunt.");
			return false;
		}
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return false;
}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {

		Long id = resultSet.getLong("id");
		Long customerID = resultSet.getLong("CustomerID");
		Order finished = new Order(id, customerID);
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM orderitems WHERE OrderID = ?");) {
			statement.setLong(1, id);
			try (ResultSet rs = statement.executeQuery();) {
				while (rs.next()) {
					finished.addItemID(rs.getLong("ItemID"));
				}
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return finished;
	}

	public Double getOrderCost(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("\r\n"
						+ "SELECT sum(items.value) as total from orders JOIN orderItems on orders.id=orderitems.OrderID join items on orderitems.ItemID=items.id where orders.id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return resultSet.getDouble(1);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0D;
	}
}

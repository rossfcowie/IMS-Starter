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
import com.qa.ims.exceptions.CustomerNotFoundException;
import com.qa.ims.exceptions.ItemNotFoundException;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		Double value = resultSet.getDouble("value");
		return new Item(id,name,value);
	}

	/**
	 * Reads all entries in the items table, displays them in the logger
	 * and returns them as a List of items.
	 */
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Item read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items where id =" + id);) {
			if(resultSet.next()) {
				return modelFromResultSet(resultSet);
			}else{
				throw new ItemNotFoundException();
			}
			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Item create(Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO items(name, value) VALUES (?,?)");) {
				statement.setString(1, t.getName());
				statement.setDouble(2, t.getValue());
				statement.executeUpdate();
				return recordCreate(readLatest());
		}catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	private Item recordCreate(Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO ItemEdits(EditorID, ItemID,ChangeType) VALUES (?,?,'Create')");) {
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
	private Item recordUpdate(Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO ItemEdits(EditorID, ItemID,ChangeType) VALUES (?,?,'Update')");) {
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
	public Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
			Item item = null;
			resultSet.next();
			item=modelFromResultSet(resultSet);
			return item;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Item update(Item t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE items Set name=?, value=? where id = ?");) {
				statement.setString(1, t.getName());
				statement.setDouble(2, t.getValue());
				statement.setLong(3, t.getId());
				if(statement.executeUpdate() == 1) {
					return recordUpdate(read(t.getId()));
				}else{
					throw new ItemNotFoundException();
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
				PreparedStatement statement = connection.prepareStatement("Delete From items where id = ?");) {
				statement.setLong(1, id);
				if(statement.executeUpdate() == 1) {
					return recordDelete(id);
				}else{
					throw new ItemNotFoundException();
				}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	private int recordDelete(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO ItemEdits(EditorID, ItemID,ChangeType) VALUES (?,?,'Delete')");) {
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

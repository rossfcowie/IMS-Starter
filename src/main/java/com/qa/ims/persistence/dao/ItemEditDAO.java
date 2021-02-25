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
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.ItemEdit;
import com.qa.ims.persistence.domain.OrderEdit;
import com.qa.ims.utils.DBUtils;

public class ItemEditDAO {

	public static final Logger LOGGER = LogManager.getLogger();
	
	public List<ItemEdit> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select users.Username, itemedits.ItemID, itemedits.ChangeType from `itemedits` join `users` on itemedits.editorID = users.id");) {
			List<ItemEdit> edits = new ArrayList<>();
			while (resultSet.next()) {
				edits.add(modelFromResultSet(resultSet));
			}
			return edits;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	public ItemEdit modelFromResultSet(ResultSet resultSet) throws SQLException {
		String Name = resultSet.getString("Username");
		Long ItemID = resultSet.getLong("ItemID");
		String change = resultSet.getString("ChangeType");
		ItemEdit finished = new ItemEdit(Name,change,ItemID);

		return finished;
	}
	
	public Item recordCreate(Item t) {
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
	
	public Item recordUpdate(Item t) {
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
	
	public int recordDelete(Long id) {
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

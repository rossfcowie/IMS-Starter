package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
}

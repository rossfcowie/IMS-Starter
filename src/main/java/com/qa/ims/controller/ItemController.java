package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.ItemEditDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.ItemEdit;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item,ItemEdit> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private ItemEditDAO itemEDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	public ItemController(ItemDAO itemDAO, Utils utils, ItemEditDAO itemEDAO) {
		super();
		this.itemDAO = itemDAO;
		this.itemEDAO = itemEDAO;
		this.utils = utils;
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String name = utils.getString();
		LOGGER.info("Please enter the items value");
		Double value = utils.getDouble();
		Item item = itemDAO.create(new Item(name, value));
		LOGGER.info("Item created");
		return itemEDAO.recordCreate(item);
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you wish to edit.");
		Long id = utils.getLong();
		LOGGER.info("Please enter the item's new name");
		String name = utils.getString();
		LOGGER.info("Please enter the item's new value");
		Double value = utils.getDouble();
		Item item = itemDAO.update(new Item(id,name, value));
		return  itemEDAO.recordUpdate(item);
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you wish to delete.");
		Long id = utils.getLong();
		return itemEDAO.recordDelete(Long.valueOf(itemDAO.delete(id)));
	}

	@Override
	public List<ItemEdit> readEdits() {
		List<ItemEdit> changes = itemEDAO.readAll();
		for (ItemEdit change : changes) {
			LOGGER.info(change);
		}
		return changes;
	}

}

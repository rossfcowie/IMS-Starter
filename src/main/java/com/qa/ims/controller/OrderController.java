package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Create order for what customer id?");
		Long customer = utils.getLong();
		Order order = orderDAO.create(new Order(customer));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Modify which order?");
		Long orderID = utils.getLong();
		Order order;
		LOGGER.info("Would you like to add or remove items?");
		String choice = utils.getString();
		List<Long> itemID =new ArrayList<>();
		if(choice.toLowerCase().startsWith("a")) {
			LOGGER.info("What Item would you like to add?");
			 itemID.add(utils.getLong());
				order = orderDAO.update(new Order(orderID,itemID));
		}else if(choice.toLowerCase().startsWith("r")) {
			LOGGER.info("What Item would you like to remove?");
			itemID.add(utils.getLong());
			order = orderDAO.update(orderID,itemID);
		}else {
			LOGGER.info("Please select either add or remove?");
			return null;
		}
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Delete which order?");
		Long orderID = utils.getLong();
		return orderDAO.delete(orderID);
	}

}

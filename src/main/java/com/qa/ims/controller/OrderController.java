package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.IMS;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderEditsDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderEdit;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order,OrderEdit> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderEditsDAO orderEDAO;
	private Utils utils;
	private CustomerDAO customerDAO = new CustomerDAO();
	
	public OrderController(OrderDAO orderDAO, Utils utils,OrderEditsDAO orderEDAO) {
		super();
		this.orderDAO = orderDAO;
		this.orderEDAO = orderEDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Order> readAll() {
		if(IMS.userLogin.getPermission()==1) {
			List<Order> orders = orderDAO.readAsCustomer();
			for (Order order : orders) {
				LOGGER.info(order + "\nYour total cost is\n£" + orderDAO.getOrderCost(order.getId()) + "\n");
			}
			return orders;
		}else {
			List<Order> orders = orderDAO.readAll();
			for (Order order : orders) {
				LOGGER.info(order + "\nYour total cost is\n£" + orderDAO.getOrderCost(order.getId()) + "\n");
			}
			return orders;
		}
		
	}

	@Override
	public Order create() {
		Long customer = 0L;
		if(IMS.userLogin.getPermission()==4) {
			LOGGER.info("Please enter the id of the order you would like to update");
			customer = utils.getLong();
			
		}else {
			customer = customerDAO.readFromUser(IMS.userLogin.getId()).getId();
		}
		Order order = orderDAO.create(new Order(customer));
		LOGGER.info("Order created");
		return orderEDAO.recordCreate(order);
	}

	@Override
	public Order update() {
		Long orderID =0L;
		LOGGER.info("Modify which order?");
		orderID = utils.getLong();
		if(IMS.userLogin.getPermission()==4) {
		
		}else {
			while(!orderDAO.checkAcceptableID(orderID)) {
				LOGGER.info("Modify which order? (0 to exit)");
				orderID = utils.getLong();
				if(orderID ==0) {
					return null;
				}
			}
		}
		Order order = null;
		LOGGER.info("Would you like to add or remove items?");
		String choice = utils.getString();
		List<Long> itemID =new ArrayList<>();
		if(choice.toLowerCase().startsWith("a")) {
			LOGGER.info("What Item would you like to add?");
			 itemID.add(utils.getLong());
				order = orderEDAO.recordAdd(orderDAO.update(new Order(orderID,itemID)));

		}else if(choice.toLowerCase().startsWith("r")) {
			LOGGER.info("What Item would you like to remove?");
			itemID.add(utils.getLong());
			order = orderEDAO.recordRemove(orderDAO.update(orderID,itemID));
		}else {
			LOGGER.info("Please select either add or remove?");
			return null;
		}
		return order;
	}

	@Override
	public int delete() {
		Long orderID =0L;
		LOGGER.info("Delete which order?");
		orderID = utils.getLong();
		if(IMS.userLogin.getPermission()==4) {
		
		}else {
			while(!orderDAO.checkAcceptableID(orderID)) {
				LOGGER.info("Delete which order? (0 to exit)");
				orderID = utils.getLong();
				if(orderID ==0) {
					return 0;
				}
			}
		}
		
		return orderEDAO.recordDelete(Long.valueOf(orderDAO.delete(orderID)));
	}
	public Double cost() {
		Long orderID =0L;
		LOGGER.info("Calculate cost for which order?");
		orderID = utils.getLong();
		if(IMS.userLogin.getPermission()==4) {
		
		}else {
			while(!orderDAO.checkAcceptableID(orderID)) {
				LOGGER.info("Calculate cost for which order? (0 to exit)");
				orderID = utils.getLong();
				if(orderID ==0) {
					return 0D;
				}
			}
		}
		
		return orderDAO.getOrderCost(orderID);
	}
	@Override
	public List<OrderEdit> readEdits() {
		List<OrderEdit> changes = orderEDAO.readAll();
		for (OrderEdit change : changes) {
			LOGGER.info(change);
		}
		return changes;
	}

}

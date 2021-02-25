package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.IMS;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.CustomerEditDAO;
import com.qa.ims.persistence.dao.OrderEditsDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.CustomerEdit;
import com.qa.ims.persistence.domain.OrderEdit;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class CustomerController implements CrudController<Customer,CustomerEdit> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CustomerDAO customerDAO;
	private CustomerEditDAO customerEDAO;
	private Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}

	public CustomerController(CustomerDAO custDAO, Utils utils, CustomerEditDAO customerEDAO) {
		super();
		this.customerDAO = custDAO;
		this.utils = utils;
		this.customerEDAO = customerEDAO;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Customer customer = customerDAO.create(new Customer(firstName, surname));
		LOGGER.info("Customer created");
		return customerEDAO.recordCreate(customer);
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
		Long id = 0L;
		if(IMS.userLogin.getPermission()==4) {
			LOGGER.info("Please enter the id of the customer you would like to update");
			id = utils.getLong();
			
		}else {
			id = customerDAO.readFromUser(IMS.userLogin.getId()).getId();
		}
		
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Customer customer = customerDAO.update(new Customer(id, firstName, surname));
		LOGGER.info("Customer Updated");
		return customerEDAO.recordUpdate(customer);
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return customerEDAO.recordDelete(Long.valueOf(customerDAO.delete(id)));
	}

	@Override
	public List<CustomerEdit> readEdits() {
		List<CustomerEdit> changes = customerEDAO.readAll();
		for (CustomerEdit change : changes) {
			LOGGER.info(change);
		}
		return changes;
	}

}

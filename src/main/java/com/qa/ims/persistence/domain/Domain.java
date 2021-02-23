package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.exceptions.InsufficientPermissionsException;
import com.qa.ims.utils.Utils;

public enum Domain {

	CUSTOMER("Information about customers"), ITEM("Individual Items"), ORDER("Purchases of items"),
	STOP("To close the application"),USER("Information about user accounts");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private Domain(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains(int level) {
		switch(level){
			
	case 4:
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.getDescription());
		}
		break;
	case 3:
		//Acceptable domains for a manager to access.
		break;
	case 2:
		//Acceptable domains for a worker to access.
		break;
	case 1:
		//Acceptable domains for a customer to access.
		break;
	default:
		break;
		}
		
		
	}

	public static Domain getDomain(Utils utils, int level) {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(utils.getString().toUpperCase());
				switch(level){
				
				case 4:
					
				break;
				case 3:
					//Acceptable domains for a manager to access.
					break;
				case 2:
					//Acceptable domains for a worker to access.
					break;
				case 1:
					//Acceptable domains for a customer to access.
					break;
				default:
					break;
					}
				
				
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}catch(InsufficientPermissionsException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return domain;
	}

}

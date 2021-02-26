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
	case 3:
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.getDescription());
		}
		break;
	case 2:
		LOGGER.info(Domain.ITEM.getDescription());
		LOGGER.info(Domain.ORDER.getDescription());
		LOGGER.info(Domain.USER.getDescription());
		LOGGER.info(Domain.STOP.getDescription());
		break;
	case 1:
		LOGGER.info(Domain.CUSTOMER.getDescription());
		LOGGER.info(Domain.ITEM.getDescription());
		LOGGER.info(Domain.ORDER.getDescription());
		LOGGER.info(Domain.USER.getDescription());
		LOGGER.info(Domain.STOP.getDescription());
		break;
	default:
		break;
		}
		
	}
	public static Domain getDomain(Utils utils) {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}
	public static Domain getDomain(Utils utils, int level) {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(utils.getString().toUpperCase());
				switch(level){
				
				case 4:
					return domain;
				case 3:
					return domain;
				case 2:
					if(domain.equals(Domain.CUSTOMER)) {
						throw new InsufficientPermissionsException();
					}else {
						return domain;
					}
				case 1:
						return domain;
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
		return null;
	}

}

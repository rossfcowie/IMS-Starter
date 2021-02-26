package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.exceptions.InsufficientPermissionsException;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.Utils;

/**
 * Action is a collection of commands which are used to determine the type of
 * function to apply to an entity.
 *
 */
public enum Action {
	CREATE("To save a new entity into the database"), READ("To read an entity from the database"),
	UPDATE("To change an entity already in the database"), DELETE("To remove an entity from the database"),
	RETURN("To return to domain selection"), EDITS("Check all of the recent edits made to this database");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	Action(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	/**
	 * Prints out all possible actions
	 */
	public static void printActions() {
		for (Action action : Action.values()) {
			LOGGER.info(action.getDescription());
		}
	}
	
	public static void printActions(int level, Domain d) {
		switch(level){			
	case 4:
		for (Action action : Action.values()) {
			if(d.equals(Domain.USER) && action.equals(Action.EDITS)) {
				
			}else {
			LOGGER.info(action.getDescription());
			}
		}
		break;
	case 3:
		if(d.equals(Domain.CUSTOMER)) {
			LOGGER.info(Action.READ.getDescription());
			LOGGER.info(Action.EDITS.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else if(d.equals(Domain.USER)) {
			LOGGER.info(Action.READ.getDescription());
			LOGGER.info(Action.UPDATE.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else if(d.equals(Domain.ORDER)) {
			LOGGER.info(Action.READ.getDescription());
			LOGGER.info(Action.DELETE.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else {
			for (Action action : Action.values()) {
				LOGGER.info(action.getDescription());
			}
		}
		break;
	case 2:
		if(d.equals(Domain.ORDER)) {
			LOGGER.info(Action.READ.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else if(d.equals(Domain.ITEM)) {
			LOGGER.info(Action.READ.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else if(d.equals(Domain.USER)) {
			LOGGER.info(Action.UPDATE.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}
		break;
	case 1:
		
		if(d.equals(Domain.ORDER)) {
			LOGGER.info(Action.CREATE.getDescription());
			LOGGER.info(Action.READ.getDescription());
			LOGGER.info(Action.UPDATE.getDescription());
			LOGGER.info(Action.DELETE.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else if(d.equals(Domain.CUSTOMER)) {
			LOGGER.info(Action.UPDATE.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else if(d.equals(Domain.ITEM)) {
			LOGGER.info(Action.READ.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}else if(d.equals(Domain.USER)) {
			LOGGER.info(Action.UPDATE.getDescription());
			LOGGER.info(Action.RETURN.getDescription());
		}

		break;
	default:
		break;
		}
		
		
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static Action getAction(Utils utils) {
		Action action = null;
		do {
			try {
				action = Action.valueOf(utils.getString().toUpperCase());
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		} while (action == null);
		return action;
	}

	
	
	public static Action getAction(Utils utils,int level, Domain d) {
		Action action = null;
		while (true) {
			try {
				action = Action.valueOf(utils.getString().toUpperCase());
				
				switch(level){
				
				case 4:
					switch(d){
					case CUSTOMER:
					case USER:
					case ITEM:
					case ORDER:
						return action;
					default:
						throw new InsufficientPermissionsException();
					}
					
					
				case 3:
					switch(d){
					case CUSTOMER:
						if(action.equals(READ)|| action.equals(RETURN)) {
							return action;
						}
						break;
					case USER:
						if(action.equals(UPDATE)|| action.equals(READ)|| action.equals(RETURN)) {
							return action;
						}
						break;
					case ITEM:
						return action;
					case ORDER:
						if(action.equals(DELETE)|| action.equals(READ)|| action.equals(RETURN)) {
							return action;
						}
						break;
					default:
						break;
					
					}
					throw new InsufficientPermissionsException();
				case 2:
					switch(d){
					case CUSTOMER:
						throw new InsufficientPermissionsException();
					case USER:
						if(action.equals(UPDATE)|| action.equals(RETURN)) {
							return action;
						}
						break;
					case ITEM:
						if(action.equals(READ)|| action.equals(RETURN)) {
							return action;
						}
						break;
					case ORDER:
						if(action.equals(READ)|| action.equals(RETURN)) {
							return action;
						}
						break;
					default:
						throw new InsufficientPermissionsException();
					}
				case 1:
					switch(d){
					case CUSTOMER:
						if(action.equals(UPDATE)|| action.equals(RETURN)) {
							return action;
						}
						break;
					case USER:
						if(action.equals(UPDATE)|| action.equals(RETURN)) {
							return action;
						}
						break;
					case ITEM:
						if(action.equals(READ)|| action.equals(RETURN)) {
							return action;
						}
						break;
					case ORDER:
						if(action.equals(EDITS)) {
							throw new InsufficientPermissionsException();
						}else {
							return action;
						}
					default:
						throw new InsufficientPermissionsException();
					}
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

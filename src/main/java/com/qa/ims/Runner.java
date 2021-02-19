package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.DBUtils;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		IMS ims = new IMS();
		ims.imsSystem();
		LOGGER.info("SO LONG!");
	}

}

package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.DBUtils;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		DBUtils.connect();
		DBUtils.getInstance().init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");
		IMS ims = new IMS();
		ims.imsSystem();
		LOGGER.info("SO LONG!");
	}

}

package com.qa.ims.controller;

import java.util.List;

import com.qa.ims.persistence.domain.OrderEdit;

/**
 * Create, Read, Update and Delete controller. Takes in inputs for each action
 * to be sent to a service class
 */
public interface CrudController<T,Y> {

	List<T> readAll();

	T create();

	T update();

	int delete();

	List<Y> readEdits();

}

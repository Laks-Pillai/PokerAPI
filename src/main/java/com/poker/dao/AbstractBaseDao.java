package com.poker.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.poker.dao.AbstractBaseDao;

public interface AbstractBaseDao<T> {


	/**
	 * Find an entity by its primary key
	 * 
	 * @param id
	 *            the primary key
	 * @return the entity
	 */
	public T findById(Long id);

	
	/**
	 * Save or update an entity to the persistent storage
	 * @param objToSave Object (attached or detached) to be persisted
	 * @return object with updated ID if applicable
	 */
	public T save(T objToSave);
	
	/**
	 * Merge a transient object with an existing persistent context object. Used for updating
	 * Existing objects that have left the persistence context
	 * @param objToMerge
	 * @return object with persistent context attached
	 */
	public T merge(T objToMerge);
	
	/**
	 * Remove a persistent object from the database
	 * 
	 * @param objToRemove Attached object to remove
	 */
	public void remove(T objToRemove);
}
package com.poker.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.poker.dao.AbstractBaseDao;

public class AbstractBaseDaoImpl<T> implements AbstractBaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractBaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public T findById(Long id){
		return (T) getSession().get(persistentClass, id);
	}
	
	@Override
	public void remove(T objToRemove){
		getSession().delete(objToRemove);
	}
	
	@Override
	public T save(T objToSave){
		getSession().saveOrUpdate(objToSave);
		return objToSave;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T merge(T objToMerge){
		return (T) getSession().merge(objToMerge);
	}
}
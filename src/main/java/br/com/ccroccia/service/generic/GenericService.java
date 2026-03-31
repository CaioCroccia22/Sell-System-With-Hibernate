package br.com.ccroccia.service.generic;

import java.io.Serializable;
import java.util.Collection;


import br.com.ccroccia.dao.Persistent;
import br.com.ccroccia.dao.generics.IGenericDAO;

public abstract class GenericService<T extends Persistent, E extends Serializable> implements IGenericService<T, E>{

	protected IGenericDAO<T, E> genericDao;
	
	
	public GenericService(IGenericDAO<T, E> genericDao) {
		this.genericDao = genericDao;
	}
	
	@Override
	public Boolean insert(T entity) {
		this.genericDao.register(entity);
		return true;
	}

	@Override
	public void delete(E value) {
		this.genericDao.delete(value);
		
	}

	@Override
	public T update(T entity) {
		return this.genericDao.update(entity);
	}

	@Override
	public T select(E value) {
		return this.genericDao.find(value);
	}

	@Override
	public Collection<T> selectAll() {
		return this.genericDao.findAll();
	}
	
}

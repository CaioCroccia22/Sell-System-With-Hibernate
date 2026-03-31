package br.com.ccroccia.service.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.com.ccroccia.dao.Persistent;

public interface IGenericService<T extends Persistent,E extends Serializable> {
	public Boolean insert(T entity);
	
	public void delete(E value);
	
	public T update(T entity);
	
	public T select(E value);
	
	public Collection<T> selectAll();
}

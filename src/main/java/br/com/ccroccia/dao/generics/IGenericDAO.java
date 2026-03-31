package br.com.ccroccia.dao.generics;

import br.com.ccroccia.dao.Persistent;
import br.com.ccroccia.exceptions.KeyTypeNotFoundException;

import java.io.Serializable;
import java.util.Collection;

/**
 * Generic interface for CRUD methods (Create, Read, Update and Delete)
 */
public interface IGenericDAO <T extends Persistent, E extends Serializable> {


    public T register(T entity);


    public void delete(E value);


    public T update(T entity);


    public T find(E value);


    public Collection<T> findAll();
}

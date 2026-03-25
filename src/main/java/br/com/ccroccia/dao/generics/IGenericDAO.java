package br.com.ccroccia.dao.generics;

import br.com.ccroccia.dao.Persistent;
import br.com.ccroccia.exceptions.KeyTypeNotFoundException;

import java.io.Serializable;
import java.util.Collection;

/**
 * Generic interface for CRUD methods (Create, Read, Update and Delete)
 */
public interface IGenericDAO <T extends Persistent, E extends Serializable> {

    /**
     * Method to register new records in the database
     *
     * @param entity to be registered
     * @return returns true if registered and false if not registered
     */
    public Boolean register(T entity) throws KeyTypeNotFoundException;

    /**
     * Method to delete a record from the database
     *
     * @param value unique key of the data to be deleted
     */
    public void delete(E value);

    /**
     * Method to update a record in the database
     *
     * @param entity to be updated
     */
    public void update(T entity) throws KeyTypeNotFoundException;

    /**
     * Method to find a record in the database
     *
     * @param value unique key of the data to be found
     * @return
     */
    public T find(E value);

    /**
     * Method that returns all records from the database for a given data or table
     *
     * @return Found records
     */
    public Collection<T> findAll();
}

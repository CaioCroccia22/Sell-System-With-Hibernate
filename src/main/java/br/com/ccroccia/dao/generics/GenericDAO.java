package br.com.ccroccia.dao.generics;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import annotation.*;
import br.com.ccroccia.dao.Persistent;
import br.com.ccroccia.exceptions.KeyTypeNotFoundException;
import br.com.ccrocia.dao.generic.jdbc.ConnectionFactory;

public class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T,E> {

    
	protected EntityManagerFactory entityManagerFactory;
	
	protected EntityManager entityManager;
	
	private Class<T> persistenceClass;
	
	private static final String PERSISTENCE_UNIT_NAME = null;
	
	private String persistenceUnitName;
    
    
    public GenericDAO(Class<T> persistenceClass, String persistenceUnitName) {
    	this.persistenceClass = persistenceClass;
    	this.persistenceUnitName = persistenceUnitName;
    }
  
    /// =============REGISTER =================
    @Override
    public T register(T entity) {
    	openConnection() ;
    	
    	entityManager.persist(entity);
    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
    	entityManagerFactory.close();
    	
    	return entity;
    	
    }
 
    // ================== SELECT =======================================
    public T find(E value){
    	openConnection() ;
    	
    	T entity = entityManager.find(this.persistenceClass, value);
    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
    	entityManagerFactory.close();
    	
    	return entity;
    	
    }
 //=================== DELETE =========================================================   
    public void delete(E value) {
    	openConnection() ;
    	
    	T entity = entityManager.find(this.persistenceClass, value);
    	
    	if (entity != null) {
    		entityManager.remove(entity);
    	}
    	
    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
    	entityManagerFactory.close();
    	
    	
    }
    
  // ================= UPDATE ========================================================== 
    public T update(T entity) {
    	openConnection() ;
    	
    	entity = entityManager.merge(entity);
    	

    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
    	entityManagerFactory.close();
    	
    	return entity;
    	
    }
    
  // ========================== SELECT ALL =============================================
    
    
    public List<T> findAll(){
    	openConnection() ;
		List<T> list =
				entityManager.createQuery("SELECT L FROM " + this.persistenceClass.getSimpleName(), this.persistenceClass).getResultList();
		entityManager.getTransaction().commit();
		 
		entityManager.close();
		entityManagerFactory.close();
		
		return list;
    }
    
    
    
    
    
    
  /// ====================== OTHER METHODS   ===================================================

	protected void openConnection() {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory(getPersistenceUnitName());
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}
	
	private String getPersistenceUnitName() {
		if (persistenceUnitName != null 
				&& !"".equals(persistenceUnitName)) {
			return persistenceUnitName;
		} else {
			return PERSISTENCE_UNIT_NAME;
		}
	}
	
	

}

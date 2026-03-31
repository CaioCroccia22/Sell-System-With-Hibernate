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
    
    
    public GenericDAO(Class<T> persistenceClass) {
    	this.persistenceClass = persistenceClass;
    }
  
    /// =============REGISTER =================
    @Override
    public T register(T entity) {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistence");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	entityManager.persist(entity);
    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
    	entityManagerFactory.close();
    	
    	return entity;
    	
    }
 
    // ================== SELECT =======================================
    public T find(E value){
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistence");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	
    	T entity = entityManager.find(this.persistenceClass, value);
    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
    	entityManagerFactory.close();
    	
    	return entity;
    	
    }
 //=================== DELETE =========================================================   
    public void delete(E value) {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistence");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	
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
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistence");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	
    	entity = entityManager.merge(entity);
    	

    	entityManager.getTransaction().commit();
    	
    	entityManager.close();
    	entityManagerFactory.close();
    	
    	return entity;
    	
    }
    
  // ========================== SELECT ALL =============================================
    
    
    public List<T> findAll(){
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		List<T> list =
				entityManager.createQuery("SELECT * FROM PRODUCT", this.persistenceClass).getResultList();
		entityManager.getTransaction().commit();
		 
		entityManager.close();
		entityManagerFactory.close();
		
		return list;
    }
    
    
    
    
    
    
  /// ====================== OTHER METHODS   ===================================================



}

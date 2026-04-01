package br.com.ccroccia.dao.generics;

import java.io.Serializable;

import br.com.ccroccia.dao.Persistent;

public class GenericDB1DAO<T extends Persistent, E extends Serializable> extends GenericDAO<T, E>{
	
	
	public GenericDB1DAO(Class<T> persistentClass) {
		super(persistentClass, "Postgre1");
	}
}

package br.com.ccroccia.dao.generics;

import java.io.Serializable;

import br.com.ccroccia.dao.Persistent;

public abstract class GenericDB2DAO <T extends Persistent, E extends Serializable>
extends GenericDAO<T,E> {
	

	
	public GenericDB2DAO(Class<T> persistentClass) {
		super(persistentClass, "Postgre2");
	}

}

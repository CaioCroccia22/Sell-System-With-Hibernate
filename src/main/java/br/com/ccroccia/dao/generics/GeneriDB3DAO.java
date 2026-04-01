package br.com.ccroccia.dao.generics;

import java.io.Serializable;

import br.com.ccroccia.dao.Persistent;

public abstract class GeneriDB3DAO <T extends Persistent, E extends Serializable>
extends GenericDAO<T,E> {

	public GeneriDB3DAO(Class<T> persistentClass) {
		super(persistentClass, "Mysql1");
	}

}

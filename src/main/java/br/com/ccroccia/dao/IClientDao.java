package br.com.ccroccia.dao;



import br.com.ccroccia.dao.generics.IGenericDAO;

public interface IClientDao<T extends Persistent> extends IGenericDAO<T, Long>{


}

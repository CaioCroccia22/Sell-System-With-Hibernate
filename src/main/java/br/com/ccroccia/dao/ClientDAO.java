package br.com.ccroccia.dao;




import br.com.ccroccia.dao.generics.GenericDAO;

import br.com.ccroccia.domain.Client;

public class ClientDAO extends GenericDAO<Client, Long> implements IClientDao {
	
	
	public ClientDAO() {
		super(Client.class);
	}


}
	
	

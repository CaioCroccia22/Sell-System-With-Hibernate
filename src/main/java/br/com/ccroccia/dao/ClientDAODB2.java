package br.com.ccroccia.dao;





import br.com.ccroccia.dao.generics.GenericDB1DAO;
import br.com.ccroccia.domain.Client;

public class ClientDAODB2 extends GenericDB1DAO<Client, Long> implements IClientDao<Client> {
	
	
	public ClientDAODB2() {
		super(Client.class);
		
	}
}
	
	
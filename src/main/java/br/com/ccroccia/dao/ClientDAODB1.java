package br.com.ccroccia.dao;





import br.com.ccroccia.dao.generics.GenericDB1DAO;
import br.com.ccroccia.domain.Client;

public class ClientDAODB1 extends GenericDB1DAO<Client, Long> implements IClientDao<Client> {
	
	
	public ClientDAODB1() {
		super(Client.class);
		
	}
}
	
	

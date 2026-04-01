package br.com.ccroccia;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.jupiter.api.*;


import br.com.ccroccia.dao.ClientDAODB1;
import br.com.ccroccia.dao.ClientDAODB2;
import br.com.ccroccia.dao.IClientDao;
import br.com.ccroccia.dao.generics.GenericDB2DAO;
import br.com.ccroccia.domain.Client;

public class ClientTest {

	
	private IClientDao<Client> clientDAO;
	
	private IClientDao<Client> clientDAODB2;
	
	
	private Random rd;
	
	public ClientTest() {
		this.clientDAO = new ClientDAODB1();
		this.clientDAODB2 = new ClientDAODB2();
		rd = new Random();
	}
	
	@After
	public void end() throws Exception {
		Collection<Client> list = clientDAO.findAll();
		list.forEach(cli -> {
			try {
				clientDAO.delete(cli.getId());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		});
	}
	
	@Test
	public void FindClient() throws Exception{
		Client client = createClient();
		clientDAO.register(client);
		
		Client getSucessClientRegister = clientDAO.find(client.getId());
		assertNotNull(getSucessClientRegister);
	}
	
	@Test
	public void saveClient() throws Exception{
		Client client = createClient();
		

		Client getSucessClientRegister = clientDAO.register(client);
		assertNotNull(getSucessClientRegister);
		
		Client getClient = clientDAO.find(client.getId());
		assertNotNull(getClient);
		
		clientDAO.delete(getSucessClientRegister.getId());
		
		
		
	}
	
	@Test
	public void removeClient() {
		Client client = createClient();
		

		Client getSucessClientRegister = clientDAO.register(client);
		assertNotNull(getSucessClientRegister);
		
		Client getClient = clientDAO.find(client.getId());
		assertNotNull(getClient);
		
		clientDAO.delete(null);
		
		getClient = clientDAO.find(client.getId());
		assertNull(getClient);
	}
	
	@Test
	public void updateClient() {
		Client client = createClient();
		
	
		Client getSucessClientRegister = clientDAO.register(client);
		assertNotNull(getSucessClientRegister);
		
		Client getClient = clientDAO.find(client.getId());
		assertNotNull(getClient);
		
		getClient.setName("Fulano 123");
		clientDAO.update(getClient);
		
		Client updateClient = clientDAO.find(getClient.getId());
		assertNotNull(updateClient);
		assertEquals("Fulano 123", updateClient.getName());
		
		
		clientDAO.delete(client.getId());
		
	}
	
	
	public void getAllClients() {
		Client client = createClient();
		
		Client getSucessClientRegister = clientDAO.register(client);
		assertNotNull(getSucessClientRegister);
		
		Client client1 = createClient();
		Client getSucessClientRegister1 = clientDAO.register(client);
		assertNotNull(getSucessClientRegister1);
		
		
		Collection<Client> list = clientDAO.findAll();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
	
	// =================== DB2 Tests ===================

	@Test
	public void FindClientDB2() throws Exception{
		Client client = createClient();
		clientDAODB2.register(client);

		Client getSucessClientRegister = clientDAODB2.find(client.getId());
		assertNotNull(getSucessClientRegister);
	}

	@Test
	public void saveClientDB2() throws Exception{
		Client client = createClient();


		Client getSucessClientRegister = clientDAODB2.register(client);
		assertNotNull(getSucessClientRegister);

		Client getClient = clientDAODB2.find(client.getId());
		assertNotNull(getClient);

		clientDAODB2.delete(getSucessClientRegister.getId());



	}

	@Test
	public void removeClientDB2() {
		Client client = createClient();

		Client getSucessClientRegister = clientDAODB2.register(client);
		assertNotNull(getSucessClientRegister);

		Client getClient = clientDAODB2.find(client.getId());
		assertNotNull(getClient);

		clientDAODB2.delete(getSucessClientRegister.getId());

		getClient = clientDAODB2.find(client.getId());
		assertNull(getClient);
	}

	@Test
	public void updateClientDB2() {
		Client client = createClient();

		Client getSucessClientRegister = clientDAODB2.register(client);
		assertNotNull(getSucessClientRegister);

		Client getClient = clientDAODB2.find(client.getId());
		assertNotNull(getClient);

		getClient.setName("Fulano 123");
		clientDAODB2.update(getClient);

		Client updateClient = clientDAODB2.find(getClient.getId());
		assertNotNull(updateClient);
		assertEquals("Fulano 123", updateClient.getName());


		clientDAODB2.delete(client.getId());

	}


	public void getAllClientsDB2() {
		Client client = createClient();

		Client getSucessClientRegister = clientDAODB2.register(client);
		assertNotNull(getSucessClientRegister);

		Client client1 = createClient();
		Client getSucessClientRegister1 = clientDAODB2.register(client1);
		assertNotNull(getSucessClientRegister1);


		Collection<Client> list = clientDAODB2.findAll();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}

	private Client createClient() {
		Client client = new Client();
		client.setCpf(rd.nextLong());
		client.setName("Caio Croccia");
		client.setAge("25");
		client.setCity("Santos");
		client.setAddress("End");
		client.setState("SP");
		client.setNumber(10);
		client.setPhone(1199999999L);
		return client;
	}






}
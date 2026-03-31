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

import br.com.ccroccia.dao.ClientDAO;
import br.com.ccroccia.dao.IClientDao;
import br.com.ccroccia.domain.Client;

public class ClientTest {

	
	private IClientDao clientDAO;
	
	private Random rd;
	
	public ClientTest() {
		this.clientDAO = new ClientDAO();
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
		
		/// Aqui vai ter que trocar o retorno do metodo para um tipo cliente
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
		
		/// Aqui vai ter que trocar o retorno do metodo para um tipo cliente
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
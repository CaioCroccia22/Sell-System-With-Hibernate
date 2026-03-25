package br.com.ccroccia;


import org.junit.jupiter.api.*;

import br.com.ccroccia.dao.ClientDAO;
import br.com.ccroccia.dao.IClientDao;
import br.com.ccroccia.domain.Client;

public class ClientTest {

	
	private IClientDao clientDAO;
	
	
	@Test
	public void saveTest() throws Exception {
		clientDAO = new ClientDAO();
		
		Client Client = new Client();
		Client.setCpf(121212121L);
		Client.setName("Caio Croccia");
		Integer countCad = clientDAO.save(Client);
		Assertions.assertTrue(countCad == 1);
		
		Client ClientBD = clientDAO.findByCPF(121212121L);
		Assertions.assertNotNull(ClientBD);
		Assertions.assertEquals(Client.getCpf(), ClientBD.getCpf());
		Assertions.assertEquals(Client.getName(), ClientBD.getName());
		
		Integer countDel = clientDAO.delete(Client.getCpf());
		Assertions.assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTest() throws Exception {
		clientDAO = new ClientDAO();
		
		Client Client = new Client();
		Client.setCpf(323232323L);
		Client.setName("Caio Croccia");
		Integer countCad = clientDAO.save(Client);
		Assertions.assertTrue(countCad == 1);
		
		Client ClientBD = clientDAO.findByCPF(323232323L);
		Assertions.assertNotNull(ClientBD);
		Assertions.assertEquals(Client.getCpf(), ClientBD.getCpf());
		Assertions.assertEquals(Client.getName(), ClientBD.getName());
		
		Integer countDel = clientDAO.delete(323232323L);
		Assertions.assertTrue(countDel == 1);
	}
	
	@Test
	public void excluirTest() throws Exception {
		clientDAO = new ClientDAO();
		
		Client Client = new Client();
		Client.setCpf(323232323L);
		Client.setName("Rodrigo Pires");
		Integer countCad = clientDAO.save(Client);
		Assertions.assertTrue(countCad == 1);
		
		Client ClientBD = clientDAO.findByCPF(323232323L);
		Assertions.assertNotNull(ClientBD);
		Assertions.assertEquals(Client.getCpf(), ClientBD.getCpf());
		Assertions.assertEquals(Client.getName(), ClientBD.getName());
		
		Integer countDel = clientDAO.delete(323232323L);
		Assertions.assertTrue(countDel == 1);
	}
	
	
	@Test
	public void atualizarTest() throws Exception {
		clientDAO = new ClientDAO();
		
		Client Client = new Client();
		Client.setCpf(786755765L);
		Client.setName("Caio Croccia");
		Integer countCad = clientDAO.save(Client);
		Assertions.assertTrue(countCad == 1);
		
		Client ClientBD = clientDAO.findByCPF(786755765L);
		Assertions.assertNotNull(ClientBD);
		Assertions.assertEquals(Client.getCpf(), ClientBD.getCpf());
		Assertions.assertEquals(Client.getName(), ClientBD.getName());
		
		ClientBD.setCpf(7756555446L);
		ClientBD.setName("Outro nome");
		Integer countUpdate = clientDAO.update(ClientBD);
		Assertions.assertTrue(countUpdate == 1);
		
		Client ClientBD1 = clientDAO.findByCPF(3232323L);
		Assertions.assertNotNull(ClientBD1);
		
	
	}
}
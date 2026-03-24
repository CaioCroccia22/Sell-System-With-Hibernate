package br.com.ccroccia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.ccroccia.dao.ClienteDAO;
import br.com.ccroccia.dao.ClienteDaoMock;
import br.com.ccroccia.dao.IClienteDao;
import br.com.ccroccia.domain.Cliente;
import br.com.ccroccia.service.ClienteService;


public class ClienteDAOTest {
	
	private IClienteDao clienteDao;
	
	private Cliente cliente;
	
	public ClienteDAOTest() {
		clienteDao = new ClienteDaoMock();
	}
	
	@BeforeAll
	public void init() {

		cliente = new Cliente();
		cliente.setCpf(12345678891L);
		cliente.setNome("Caio");
		cliente.setCidade("Santos");
		cliente.setEnd("Casa");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(12121323L);
		clienteDao.salvar(cliente);
	}
	
	@Test
	public void pesquisarCliente() {
		
		
		Cliente clienteConsulta = clienteDao.buscarPorCPF(cliente.getCpf());
		
		Assertions.assertNotNull(clienteConsulta);
		
	}
	
	@Test
	public void salvarCliente() {
		boolean retorno = clienteDao.salvar(cliente);
		
		Assertions.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clienteDao.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() {
		cliente.setNome("Caio Croccia");
		clienteDao.alterar(cliente);
		Assertions.assertEquals("Caio Croccia", cliente.getNome());
	}
	
}

package br.com.ccroccia;

import org.junit.jupiter.api.*;

import br.com.ccroccia.dao.ClienteDaoMock;
import br.com.ccroccia.dao.IClienteDao;
import br.com.ccroccia.domain.Cliente;
import br.com.ccroccia.service.ClienteService;
import br.com.ccroccia.service.IClienteService;

public class ClienteTest {
	
	private static IClienteService clienteService;
	
	private static Cliente cliente;
	
	public ClienteTest() {
		
	}
	
	
	@BeforeAll
	public static void init() {
		IClienteDao dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
		
		cliente = new Cliente();
		cliente.setCpf(12345678891L);
		cliente.setNome("Caio");
		cliente.setCidade("Santos");
		cliente.setEnd("Casa");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(12121323L);
		clienteService.salvar(cliente);
	}
	
	@Test
	public void pesquisarCliente() {
		
		
		Cliente clienteConsulta = clienteService.buscarPorCPF(cliente.getCpf());
		
		Assertions.assertNotNull(clienteConsulta);
		
	}
	
	@Test
	public void salvarCliente() {
		boolean retorno = clienteService.salvar(cliente);
		
		Assertions.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() {
		cliente.setNome("Caio Croccia");
		clienteService.alterar(cliente);
		Assertions.assertEquals("Caio Croccia", cliente.getNome());
	}

}

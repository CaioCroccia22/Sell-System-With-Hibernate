package br.com.ccroccia.service;

import br.com.ccroccia.dao.IClienteDao;
import br.com.ccroccia.domain.Cliente;

public class ClienteService implements IClienteService {
	
	private IClienteDao clienteDao;
	
	
	public ClienteService(IClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	public boolean salvar(Cliente cliente) {
		clienteDao.salvar(cliente);
		return true;

	}

	@Override
	public Cliente buscarPorCPF(Long cpf) {
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		return cliente;
	}

	@Override
	public void excluir(Long cpf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

}

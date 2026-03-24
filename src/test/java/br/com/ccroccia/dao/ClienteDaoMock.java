package br.com.ccroccia.dao;

import br.com.ccroccia.domain.Cliente;

public class ClienteDaoMock implements IClienteDao{

	@Override
	public boolean salvar(Cliente cliente) {
		
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

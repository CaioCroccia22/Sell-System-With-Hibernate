package br.com.ccroccia.dao;

import br.com.ccroccia.domain.Cliente;

public interface IClienteDao {

	public boolean salvar(Cliente cliente);
	
	public Cliente buscarPorCPF(Long cpf);

	public void excluir(Long cpf);

	public void alterar(Cliente cliente);
			
}

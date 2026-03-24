package br.com.ccroccia.service;

import br.com.ccroccia.domain.Cliente;

public interface IClienteService {

	boolean salvar(Cliente cliente);

	Cliente buscarPorCPF(Long cpf);

	void excluir(Long cpf);

	void alterar(Cliente cliente);

}

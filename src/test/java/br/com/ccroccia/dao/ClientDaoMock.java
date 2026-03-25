package br.com.ccroccia.dao;

import br.com.ccroccia.domain.Client;

public class ClientDaoMock implements IClientDao {

	@Override
	public Integer save(Client client) {
		return null;
	}

	@Override
	public Client findByCPF(Long cpf) {
		Client client = new Client();
		client.setCpf(cpf);
		return client;
	}

	@Override
	public Integer delete(Long cpf) {
		return null;

	}

	@Override
	public Integer update(Client client) {
		return null;

	}

}

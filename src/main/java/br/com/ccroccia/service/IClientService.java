package br.com.ccroccia.service;

import br.com.ccroccia.domain.Client;

public interface IClientService {

	boolean save(Client client) throws Exception;

	Client findByCPF(Long cpf);

	void delete(Long cpf);

	void update(Client client);

}

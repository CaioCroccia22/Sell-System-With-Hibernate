package br.com.ccroccia.service;


import java.util.Collection;

import br.com.ccroccia.dao.IClientDao;

import br.com.ccroccia.domain.Client;
import br.com.ccroccia.service.generic.GenericService;

public class ClientService extends GenericService<Client, Long>  implements IClientService{

	IClientDao clientDao;


	public ClientService(IClientDao clientDao) {
		super(clientDao);
	}

	@Override
	public Boolean insert(Client entity) {
		clientDao.register(entity);
		return true;
	}

	@Override
	public void delete(Long value) {
		clientDao.delete(value);
		
	}

	@Override
	public Client update(Client client) {
		Client clientUpdate = clientDao.update(client);
		return clientUpdate;
	}

	@Override
	public Client select(Long value) {
		Client client = clientDao.find(value);
		return client;
	}

	@Override
	public Collection<Client> selectAll() {
		return clientDao.findAll();
	}



}

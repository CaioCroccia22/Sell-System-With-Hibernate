package br.com.ccroccia.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.ccroccia.domain.Client;

public interface IClientDao {

	public Integer save(Client client) throws Exception;

	public Client findByCPF(Long id) throws Exception;

	public Integer delete(Long cpf) throws Exception;

	public Integer update(Client client) throws SQLException;
	
	public List<Client> getAll() throws Exception;

}

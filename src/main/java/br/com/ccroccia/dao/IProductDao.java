package br.com.ccroccia.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.ccroccia.domain.Product;

public interface IProductDao {

	public Integer save(Product product) throws Exception;

	public Product findById(Integer id) throws Exception;

	public Integer delete(Integer id) throws Exception;

	public Integer update(Product product) throws SQLException;

	public List<Product> getAll() throws Exception;

}

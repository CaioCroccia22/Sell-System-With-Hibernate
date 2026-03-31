package br.com.ccroccia.dao;

import br.com.ccroccia.dao.generics.GenericDAO;
import br.com.ccroccia.domain.Product;

public class ProductDAO extends GenericDAO<Product, Long> implements IProductDao {

	public ProductDAO() {
		super(Product.class);
	}



}

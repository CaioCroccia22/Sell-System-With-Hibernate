package br.com.ccroccia.service;

import java.util.Collection;

import br.com.ccroccia.dao.IProductDao;
import br.com.ccroccia.domain.Product;
import br.com.ccroccia.service.generic.GenericService;

public class ProductService extends GenericService<Product, Long>  implements IProductService {

	IProductDao productDao;


	public ProductService(IProductDao productDao) {
		super(productDao);
	}

	@Override
	public Boolean insert(Product entity) {
		productDao.register(entity);
		return true;
	}

	@Override
	public void delete(Long value) {
		productDao.delete(value);

	}

	@Override
	public Product update(Product product) {
		Product productUpdate = productDao.update(product);
		return productUpdate;
	}

	@Override
	public Product select(Long value) {
		Product product = productDao.find(value);
		return product;
	}

	@Override
	public Collection<Product> selectAll() {
		return productDao.findAll();
	}

}

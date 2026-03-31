package br.com.ccroccia.service;

import java.util.Collection;

import br.com.ccroccia.dao.ISaleDao;
import br.com.ccroccia.domain.Sale;
import br.com.ccroccia.service.generic.GenericService;

public class SaleService extends GenericService<Sale, Long>  implements ISaleService{

	ISaleDao saleDao;


	public SaleService(ISaleDao saleDao) {
		super(saleDao);
	}

	@Override
	public Boolean insert(Sale entity) {
		saleDao.register(entity);
		return true;
	}

	@Override
	public void delete(Long value) {
		saleDao.delete(value);

	}

	@Override
	public Sale update(Sale sale) {
		Sale saleUpdate = saleDao.update(sale);
		return saleUpdate;
	}

	@Override
	public Sale select(Long value) {
		Sale sale = saleDao.find(value);
		return sale;
	}

	@Override
	public Collection<Sale> selectAll() {
		return saleDao.findAll();
	}

}

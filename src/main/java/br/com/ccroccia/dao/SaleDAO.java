package br.com.ccroccia.dao;

import br.com.ccroccia.dao.generics.GenericDAO;
import br.com.ccroccia.domain.Sale;

public class SaleDAO extends GenericDAO<Sale, Long> implements ISaleDao {

	public SaleDAO() {
		super(Sale.class);
	}

}

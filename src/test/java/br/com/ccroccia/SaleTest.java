package br.com.ccroccia;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.jupiter.api.*;

import br.com.ccroccia.dao.SaleDAO;
import br.com.ccroccia.dao.ISaleDao;
import br.com.ccroccia.domain.Sale;

public class SaleTest {


	private ISaleDao saleDAO;

	private Random rd;

	public SaleTest() {
		this.saleDAO = new SaleDAO();
		rd = new Random();
	}

	@After
	public void end() throws Exception {
		Collection<Sale> list = saleDAO.findAll();
		list.forEach(sale -> {
			try {
				saleDAO.delete(sale.getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
	}

	@Test
	public void FindSale() throws Exception{
		Sale sale = createSale();
		saleDAO.register(sale);

		Sale getSucessSaleRegister = saleDAO.find(sale.getId());
		assertNotNull(getSucessSaleRegister);
	}

	@Test
	public void saveSale() throws Exception{
		Sale sale = createSale();


		Sale getSucessSaleRegister = saleDAO.register(sale);
		assertNotNull(getSucessSaleRegister);

		Sale getSale = saleDAO.find(sale.getId());
		assertNotNull(getSale);

		saleDAO.delete(getSucessSaleRegister.getId());



	}

	@Test
	public void removeSale() {
		Sale sale = createSale();

		Sale getSucessSaleRegister = saleDAO.register(sale);
		assertNotNull(getSucessSaleRegister);

		Sale getSale = saleDAO.find(sale.getId());
		assertNotNull(getSale);

		saleDAO.delete(null);

		getSale = saleDAO.find(sale.getId());
		assertNull(getSale);
	}

	@Test
	public void updateSale() {
		Sale sale = createSale();

		Sale getSucessSaleRegister = saleDAO.register(sale);
		assertNotNull(getSucessSaleRegister);

		Sale getSale = saleDAO.find(sale.getId());
		assertNotNull(getSale);

		getSale.setCode("SALE-999");
		saleDAO.update(getSale);

		Sale updateSale = saleDAO.find(getSale.getId());
		assertNotNull(updateSale);
		assertEquals("SALE-999", updateSale.getCode());


		saleDAO.delete(sale.getId());

	}


	public void getAllSales() {
		Sale sale = createSale();

		Sale getSucessSaleRegister = saleDAO.register(sale);
		assertNotNull(getSucessSaleRegister);

		Sale sale1 = createSale();
		Sale getSucessSaleRegister1 = saleDAO.register(sale1);
		assertNotNull(getSucessSaleRegister1);


		Collection<Sale> list = saleDAO.findAll();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}

	private Sale createSale() {
		Sale sale = new Sale();
		sale.setCode("SALE-" + rd.nextInt(1000));
		sale.setTotal(BigDecimal.valueOf(rd.nextDouble() * 1000));
		sale.setDate(Instant.now());
		sale.setStatus(Sale.Status.Requested);
		return sale;
	}






}

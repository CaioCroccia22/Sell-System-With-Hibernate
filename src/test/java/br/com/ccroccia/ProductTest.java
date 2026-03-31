package br.com.ccroccia;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.jupiter.api.*;

import br.com.ccroccia.dao.ProductDAO;
import br.com.ccroccia.dao.IProductDao;
import br.com.ccroccia.domain.Product;

public class ProductTest {


	private IProductDao productDAO;

	private Random rd;

	public ProductTest() {
		this.productDAO = new ProductDAO();
		rd = new Random();
	}

	@After
	public void end() throws Exception {
		Collection<Product> list = productDAO.findAll();
		list.forEach(prod -> {
			try {
				productDAO.delete(prod.getId());
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
	}

	@Test
	public void FindProduct() throws Exception{
		Product product = createProduct();
		productDAO.register(product);

		Product getSucessProductRegister = productDAO.find(product.getId());
		assertNotNull(getSucessProductRegister);
	}

	@Test
	public void saveProduct() throws Exception{
		Product product = createProduct();


		Product getSucessProductRegister = productDAO.register(product);
		assertNotNull(getSucessProductRegister);

		Product getProduct = productDAO.find(product.getId());
		assertNotNull(getProduct);

		productDAO.delete(getSucessProductRegister.getId());



	}

	@Test
	public void removeProduct() {
		Product product = createProduct();

		Product getSucessProductRegister = productDAO.register(product);
		assertNotNull(getSucessProductRegister);

		Product getProduct = productDAO.find(product.getId());
		assertNotNull(getProduct);

		productDAO.delete(null);

		getProduct = productDAO.find(product.getId());
		assertNull(getProduct);
	}

	@Test
	public void updateProduct() {
		Product product = createProduct();

		Product getSucessProductRegister = productDAO.register(product);
		assertNotNull(getSucessProductRegister);

		Product getProduct = productDAO.find(product.getId());
		assertNotNull(getProduct);

		getProduct.setName("Produto Atualizado");
		productDAO.update(getProduct);

		Product updateProduct = productDAO.find(getProduct.getId());
		assertNotNull(updateProduct);
		assertEquals("Produto Atualizado", updateProduct.getName());


		productDAO.delete(product.getId());

	}


	public void getAllProducts() {
		Product product = createProduct();

		Product getSucessProductRegister = productDAO.register(product);
		assertNotNull(getSucessProductRegister);

		Product product1 = createProduct();
		Product getSucessProductRegister1 = productDAO.register(product1);
		assertNotNull(getSucessProductRegister1);


		Collection<Product> list = productDAO.findAll();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}

	private Product createProduct() {
		Product product = new Product();
		product.setName("Produto " + rd.nextInt(1000));
		product.setDescription("Descricao do produto");
		product.setPrice(rd.nextDouble() * 100);
		product.setQuantity(rd.nextInt(100));
		return product;
	}






}

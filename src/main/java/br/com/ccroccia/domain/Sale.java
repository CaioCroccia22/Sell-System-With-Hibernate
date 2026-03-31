package br.com.ccroccia.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import br.com.ccroccia.dao.Persistent;

@Entity
@Table(name="Sale")
public class Sale implements Persistent{
	
	public enum Status{
		Requested, Completed, Canceled;
		
		public static Status getByName(String value) {
			for(Status status : Status.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_sale")
	@SequenceGenerator(name="seq_sale", sequenceName="seq_vale", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "code", nullable = false, unique = false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "id_client_fk",
	foreignKey = @ForeignKey(name = "fk_client_sale"),
	referencedColumnName = "id", nullable = false
	)
	private Client client;
	
	
	
	@ManyToMany
	@JoinTable(name = "produts_sale",
	joinColumns = @JoinColumn(name = "sale_id"),
	inverseJoinColumns = @JoinColumn(name = "products_id"))
	private List<Product> products;
	
	@Column(name = "total", nullable = false)
	private BigDecimal total;
	
	@Column(name = "date", nullable = false)
	private Instant date;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sale_status", nullable = false)
	private Status status;
	
	public Sale() {
		products = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void addProduct(Product product, Integer quantity) {
	    validateStatus();
	    Optional<ProductQuantity> op =
	            products.stream().filter(filter -> filter.getId().equals(product.getId())).findAny();
	    if (op.isPresent()) {
	        ProductQuantity productQty = op.get();
	        productQty.add(quantity);
	    } else {
	        // Create factory to create ProductQuantity
	        ProductQuantity prod = new ProductQuantity();
	        prod.setSale(this);
	        prod.setProduct(product);
	        prod.add(quantity);
	        products.add(prod);
	    }
	    recalculateTotalSaleValue();
	}

	private void validateStatus() {
	    if (this.status == Status.Completed) {
	        throw new UnsupportedOperationException("UNABLE TO MODIFY A FINALIZED SALE");
	    }
	}

	public void removeProduct(Product product, Integer quantity) {
	    validateStatus();
	    Optional<ProductQuantity> op =
	            products.stream().filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();

	    if (op.isPresent()) {
	        ProductQuantity productQty = op.get();
	        if (productQty.getQuantity() > quantity) {
	            productQty.remove(quantity);
	            recalculateTotalSaleValue();
	        } else {
	            products.remove(op.get());
	            recalculateTotalSaleValue();
	        }
	    }
	}

	public void removeAllProducts() {
	    validateStatus();
	    products.clear();
	    totalValue = BigDecimal.ZERO;
	}

	public Integer getTotalProductQuantity() {
	    // Sums the quantity getQuantity() of all ProductQuantity objects
	    int result = products.stream()
	      .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
	    return result;
	}

	public void recalculateTotalSaleValue() {
	    //validateStatus();
	    BigDecimal totalValue = BigDecimal.ZERO;
	    for (ProductQuantity prod : this.products) {
	        totalValue = totalValue.add(prod.getTotalValue());
	    }
	    this.totalValue = totalValue;
	}
	
	
	
}

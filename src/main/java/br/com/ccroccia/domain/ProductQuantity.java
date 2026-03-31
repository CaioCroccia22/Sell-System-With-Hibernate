package br.com.ccroccia.domain;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "ProductQuantity")
public class ProductQuantity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_quantity")
	@SequenceGenerator(name = "seq_product_quantity", sequenceName = "seq_product_quantity", 
			initialValue = 1, allocationSize = 1)
	private Long id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "total", nullable = false)
	private BigDecimal total;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_sale_fk", 
		foreignKey = @ForeignKey(name = "fk_product_total_sale"), 
		referencedColumnName = "id", nullable = false
	)
	private Sale sale;
	
	public ProductQuantity() {
		this.quantity = 0;
		this.total = BigDecimal.ZERO;
	}
	
	public void add(Integer quantity) {
		this.quantity += quantity;
		this.total = BigDecimal.valueOf(this.quantity).multiply(BigDecimal.valueOf(product.getPrice()));
	}
	
	public void remove(Integer quantity) {
		this.quantity -= quantity;
		this.total = BigDecimal.valueOf(this.quantity).multiply(BigDecimal.valueOf(product.getPrice()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	
}

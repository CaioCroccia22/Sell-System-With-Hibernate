package br.com.ccroccia.domain;

import javax.persistence.*;

import br.com.ccroccia.dao.Persistent;

@Entity
@Table(name = "Product")
public class Product implements Persistent{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
	@SequenceGenerator(name = "seq_product", sequenceName = "seq_product", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "nm_product", length = 50, nullable = false)
	private String name;

	@Column(name = "ds_product", length = 100, nullable = false)
	private String description;

	@Column(name = "vl_price", nullable = false)
	private Double price;

	@Column(name = "qtd_product", nullable = false)
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

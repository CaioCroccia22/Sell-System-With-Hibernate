package br.com.ccroccia.domain;


import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;


import br.com.ccroccia.dao.Persistent;

@Entity
@Table(name = "Client")
public class Client implements Persistent{

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_client")
	@SequenceGenerator(name="seq_client", sequenceName="seq_client", initialValue = 1, allocationSize = 1)
	private Long id;
	
	
	@Column(name = "cd_cpf", length = 11, nullable = false, unique = true)
	private Long cpf;
	
	@Column(name = "nm_client", length = 30, nullable = false)
	private String name;
	
	@Column(name = "nr_age", length = 2, nullable = false)
	private String age;
	
	@Column(name = "nr_phone", length = 12, nullable = false)
	private Long phone;
	
	@Column(name = "ds_address", length = 20, nullable = false)
	private String address;
	
	@Column(name = "nr_adress_number", length = 5, nullable = false)
	private Integer number;
	
	@Column(name = "ds_city", length = 30, nullable = false)
	private String city;
	
	@Column(name = "ds_state", length = 2, nullable = false)
	private String state;


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
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

}

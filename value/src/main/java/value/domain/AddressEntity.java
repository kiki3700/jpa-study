package value.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {
	@Id @GeneratedValue
	private int id;
	
	private Address address;

	public AddressEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AddressEntity(String city, String street, String zipcode) {
		address = new Address(city, street, zipcode);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}

package jpashop.domain;

import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class Address {
	private String city;
	private String street;
	private String zipcode;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCity() {
		return city;
	}
	private void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	private void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	private void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Address address = (Address) obj;
        return Objects.equals(getCity(), address.getCity())&&
        		Objects.equals(getStreet(), address.getStreet())&&
        		Objects.equals(getZipcode(), address.getZipcode());
    }
	@Override
	public int hashCode() {return Objects.hash(getCity(), getStreet(), getZipcode());}
}

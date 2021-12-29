package entities;

public class Address {

	private String address;
	private int zipcode;
	private String area;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Address(String address, int zipcode, String area) {
		this.address = address;
		this.zipcode = zipcode;
		this.area = area;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", zipcode=" + zipcode + ", area=" + area + "]";
	}

}

package entities;

import java.util.ArrayList;
import java.util.List;

import events.Reservation;
import main.CarFactory;

public class Office {

	private int officeNumber;
	private Address address;
	private int telephoneNumber;
	List<Car> cars;


	public Office(int officeNumber, Address address, int telephoneNumber) {
		this.officeNumber = officeNumber;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		cars = new ArrayList<>();
		
	}
	
	public void addCars(List<Car> car) {
		this.cars = car;
	}
	
	public List<Car> getCars(){
		return cars;
	}

	public int getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(int officeNumber) {
		this.officeNumber = officeNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public String toString() {
		return "[Number=" + officeNumber + ", Address=" + address + ", Phone=" + telephoneNumber + "]";
	}

}

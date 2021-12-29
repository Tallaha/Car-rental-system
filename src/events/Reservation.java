package events;

import java.util.Date;

import entities.Car;
import entities.Customer;
import entities.Office;

public class Reservation {

	private String creditCardNumber;
	private Car car;
	private Customer customer;
	private Date rentalDate;
	private Date returnDate;
	private int numberOfDays;
	private int sum;
	private boolean payment;

	public Reservation() {

	}

	public Reservation(String creditCardNumber, Customer customer, Car car, Date rentalDate, Date returnDate, int numberOfDays, int sum,
			boolean payment) {

		this.creditCardNumber = creditCardNumber;
		this.car = car;
		this.customer = customer;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.numberOfDays = numberOfDays;
		this.sum = sum;
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the creditCardNumber
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * @param creditCardNumber the creditCardNumber to set
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * @return the rentalDate
	 */
	public Date getRentalDate() {
		return rentalDate;
	}

	/**
	 * @param rentalDate the rentalDate to set
	 */
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return the numberOfDays
	 */
	public int getNumberOfDays() {
		return numberOfDays;
	}

	/**
	 * @param numberOfDays the numberOfDays to set
	 */
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Reservation [creditCardNumber=" + creditCardNumber + ", car=" + car + ", rentalDate=" + rentalDate
				+ ", returnDate=" + returnDate + ", numberOfDays=" + numberOfDays + ", sum=" + sum + ", payment="
				+ payment + "]";
	}

}
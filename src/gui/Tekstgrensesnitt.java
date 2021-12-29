package gui;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.Address;
import entities.Car;
import entities.Company;
import entities.Customer;
import entities.Office;
import events.Reservation;

public class Tekstgrensesnitt {
	Car car;
	Company company;
	Office office;
	Scanner sc;

	public Tekstgrensesnitt() {
		company = new Company("Abu Tallahs Rental Company", 51853920);
		sc = new Scanner(System.in);
		sc.useDelimiter(System.lineSeparator());
	}

	public int showMainMenu() {
		int choice = 0;

		System.out.println("Welcome to Abu Tallah's Rental Company");
		System.out.println("1: Search for available cars");
		System.out.println("2: Search for reservation");
		System.out.println("3: Exit the program.");

		String input = sc.next();

		try {
			choice = Integer.parseInt(input);
			return choice;
		} catch (NumberFormatException e) {
			System.out.println("Error: The input is not a valid choice");
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * the customer type in which city, rental/return date and is given a list of
	 * all the available cars
	 */
	public void searchAndMakeReservation() {

		Car rentalCar = null;
		Reservation reservation = null;

		System.out.println("Please enter the city you want to rent from '1: Longyearbyen' or '2: Oslo'.");
		int city = Integer.parseInt(sc.next());

		Date rentalDate = rentalDate();

		Date returnDate = returnDate();

		int numberOfDays = calculateDays(returnDate, rentalDate);

		List<Car> cars = company.cityCars(city);

		List<Car> availableCars = Car.availableCars(cars, rentalDate, returnDate);

		int numberOfCars = printAvailableCars(availableCars, numberOfDays);

		System.out.println("Chose the car you want to rent, type '0' to exit the service");

		int choice = Integer.parseInt(sc.next());

		rentalCar = pickCar(choice, numberOfCars, availableCars);

		Customer newCustomer = registerCustomer();

		reservation = completeReservation(newCustomer, rentalCar, rentalDate, returnDate, numberOfDays);

		addReservationToCustomer(newCustomer, reservation);

	}

	/**
	 * the user enter credit card number and we find the reservation. The user user
	 * is giving a choice to return the car or exit
	 */
	public void searchAndReturnCar() {

		System.out.println("Please enter your credit card number");
		String creditCardNumber = sc.next();

		Reservation reservation = searchForReservation(creditCardNumber);

		if (reservation == null) {
			System.out.println("The reservation does not exist");
		} else {

			System.out.println("If you want to return the rental car, type '1' and '0' for exit");
			int choice = Integer.parseInt(sc.next());

			if (choice == 1) {
				returnCar(reservation);

			} else {
				exit();
			}
		}

	}

	/**
	 * 
	 * @param number of available cars
	 * @param cars,  list of cars
	 * @return the chosen car
	 */
	public Car pickCar(int choice, int number, List<Car> cars) {

		Car car = null;

		if (choice == 0) {
			exit();
		}
		if (choice >= 1 && choice <= number) {
			car = cars.get(choice - 1);
		}

		return car;

	}

	/**
	 * when customer makes a reservation, the reservation object in costumer gets
	 * updated add the customer to the costumer list in Company
	 * 
	 * @param customer    to be updated
	 * @param reservation to be added
	 */
	public void addReservationToCustomer(Customer customer, Reservation reservation) {

		Customer updatedCustomer = customer;

		updatedCustomer.setReservation(reservation);

		company.addCustomer(updatedCustomer);

		System.out.println("Reservation was successful");
	}

	/**
	 * ask customer to type in credit card number, and if the customer wants to pay
	 * the given 'sum' makes a reservation, and add reservation to the Car
	 * 
	 * @return the reservation that was made
	 */
	public Reservation completeReservation(Customer customer, Car car, Date rentalDate, Date returnDate,
			int numberOfDays) {

		Reservation reservation = null;

		System.out.println("To make a reservation type in your credit card number");

		String creditcardNumber = sc.next();

		int price = price(numberOfDays, car.getPrice());

		System.out.println("Your sum is: " + price + "\n" + "Type '1' to confirm payment, type '0' to exit");
		int number = Integer.parseInt(sc.next());

		if (number == 0) {
			exit();
		}
		if (number == 1) {
			System.out.println("Your order was successful");
			reservation = customer.makeReservation(creditcardNumber, customer, car, rentalDate, returnDate,
					numberOfDays, price);

			car.addReservation(reservation);
		}

		return reservation;
	}

	/**
	 * register a customer with help from Scanner
	 * 
	 * @return the Customer
	 */
	public Customer registerCustomer() {

		System.out.println("To confirm the reservation you need to sign up as a customer");
		System.out.println("Please enter your firstname");
		String firstname = sc.next();
		System.out.println("Please enter your lastname");
		String lastname = sc.next();
		System.out.println("Please enter your address");
		String address = sc.next();
		System.out.println("Please enter your zip code");
		int zipCode = Integer.parseInt(sc.next());
		System.out.println("Please enter your area");
		String area = sc.next();

		System.out.println("Please enter your phone number");
		String phone = sc.next();

		while (!checkPhone(phone)) {
			System.out.println("Phone number can only contain digits, please enter again");
			phone = sc.next();
		}

		int phoneNumber = Integer.parseInt(phone);

		Customer customer = company.addCustomer(firstname, lastname, address, zipCode, area, phoneNumber);

		return customer;
	}

	/**
	 * check if a phone number only contains digits
	 * 
	 * @param phone number
	 * @return true or false
	 */
	public boolean checkPhone(String phone) {
		if (phone.matches("[0-9]+") && phone.length() == 8) {
			return true;
		}
		return false;
	}

	/**
	 * with the help of the reservation we can find the car. After calculating for
	 * any fees, we set the kmstand, delete reservation from the reservation list
	 * (in car) and remove the reservation from the customer object
	 * 
	 * @param reservation
	 */
	public void returnCar(Reservation reservation) {

		boolean yes = true;

		Car car = reservation.getCar();

		System.out.println("Type in new km stand");
		int km = Integer.parseInt(sc.next());

		Date newReturnDate = returnDate();

		Date returnDate = reservation.getReturnDate();

		int days = calculateDays(newReturnDate, returnDate);

		if (days > 0) {

			int newPrice = price(days, car.getPrice() * 2);
			System.out.println("You went " + days + " over your rental time" + "\n" + "Your fee is on " + newPrice);

			while (yes) {
				System.out.println("Type '1' to confirm your payment");
				int number = Integer.parseInt(sc.next());

				if (number == 1) {
					System.out.println("Your payment was successful");
					yes = false;
				}
			}
		}

		car.setKm(car.getKm() + km);

		car.deleteReservation(reservation);

		company.returnCar(reservation.getCustomer());

	}

	/**
	 * search after a reservation with the help of credit card number
	 * 
	 * @return the reservation
	 */
	public Reservation searchForReservation(String creditCardNumber) {

		Reservation reservation = company.findReservation(creditCardNumber);

		return reservation;

	}

	/**
	 * prints available cars
	 * 
	 * @param list of cars
	 * @return number of cars in the list
	 */
	public int printAvailableCars(List<Car> cars, int numberOfDays) {
		int i = 0;
		for (Car c : cars) {

			int price = price(numberOfDays, c.getPrice());
			i++;
			System.out.println(i + " " + c.toString() + " the price for " + numberOfDays + " days: " + price);
		}

		return i;
	}

	/**
	 * calculate the price to rent a car
	 * 
	 * @param days,    number of rental days
	 * @param carPrice
	 * @return sum
	 */
	public int price(int days, int carPrice) {
		int sum = days * carPrice;
		return sum;
	}

	/**
	 * with Scanner, user type in rental date
	 * 
	 * @return the Date
	 */
	public Date rentalDate() {

		System.out.println("Please enter rental year");
		int year = Integer.parseInt(sc.next());

		System.out.println("Please enter rental month");
		int month = Integer.parseInt(sc.next());

		System.out.println("Please enter rental day number");
		int day = Integer.parseInt(sc.next());

		Date rentalDate = new Date(year, month, day, 07, 00);

		return rentalDate;
	}

	/**
	 * with Scanner, user type in return date
	 * 
	 * @return the Date
	 */
	public Date returnDate() {

		System.out.println("Please enter return year");
		int returnYear = Integer.parseInt(sc.next());

		System.out.println("Please enter return month");
		int returnMonth = Integer.parseInt(sc.next());

		System.out.println("Please enter return day number");
		int returnDay = Integer.parseInt(sc.next());

		Date returnDate = new Date(returnYear, returnMonth, returnDay, 22, 00);

		return returnDate;

	}

	/**
	 * calculate if the customer went over due
	 * 
	 * @param returnDate
	 * @param rentalDate
	 * @return the number of days
	 */
	public int calculateDays(Date returnDate, Date rentalDate) {

		Long daysBetween = (returnDate.getTime() - rentalDate.getTime()) / 86400000; // 86 400 000 milliseconds in a day
		int numberOfDays = daysBetween.intValue();

		return numberOfDays;
	}

	/**
	 * exit the program, the user is giving that choice through out
	 */
	public void exit() {
		System.out.println("Shutting down...");
		System.exit(0);
	}
}

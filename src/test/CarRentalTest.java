package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Address;
import entities.Brand;
import entities.Car;
import entities.Chassis;
import entities.Company;
import entities.Customer;
import entities.Model;
import events.Reservation;
import gui.Tekstgrensesnitt;

class CarRentalTest {

	private Tekstgrensesnitt ui;
	Company company;
	Reservation r1;
	Reservation r2;
	Car car1;
	Car car2;
	List<Car> cars;

	@BeforeEach
	void setUp() {

		company = new Company("Abu Tallah's Rental Company", 45638928);
		ui = new Tekstgrensesnitt();
		cars = new ArrayList<>();

		car1 = new Car("EK98253", Brand.TESLA, Model.S, "black", Chassis.B, 100000, 2500);
		car2 = new Car("EK68530", Brand.NISSAN, Model.LEAF, "grey", Chassis.A, 80000, 800);

		int price1 = ui.price(3, car1.getPrice());
		int price2 = ui.price(6, car2.getPrice());

		Customer c1 = new Customer("Emilie", "Hinna", new Address("Sverres gate 32", 5010, "Bergen"), 47520188,
				new Reservation());
		Customer c2 = new Customer("Fun Light", "Uten sukker", new Address("Safthylle 1", 6793, "Væske"), 67946302,
				new Reservation());

		r1 = new Reservation("123456789", c1, car1, new Date(2021, 3, 1, 07, 00), new Date(2021, 3, 4, 22, 00), 3,
				price1, true);
		c1.setReservation(r1);

		r2 = new Reservation("232367893", c2, car2, new Date(2021, 2, 1, 07, 00), new Date(2021, 2, 7, 22, 00), 6,
				price2, true);
		c2.setReservation(r2);

		company.addCustomer(c1);
		company.addCustomer(c2);

		company.addToAllCars(car1);
		company.addToAllCars(car2);

		// need this to check pickCar(), because getAllCars() in Company uses methods
		// from Factory.
		// We just want to check for a pre-defined list
		cars.add(car1);
		cars.add(car2);
	}

	@Test
	void availableCarsTest() {

		List<Car> carList1 = Car.availableCars(cars, new Date(2021, 2, 1, 07, 00), new Date(2021, 2, 7, 22, 00));

		assertEquals(car1, carList1.get(0));

	}

	@Test
	void findReservationTest() {

		assertEquals(r1, company.findReservation("123456789"));
	}

	@Test
	void priceTest() {

		assertEquals(3000, ui.price(3, 1000));

	}

	@Test
	void pickCarTest() {

		assertEquals(car1, ui.pickCar(1, 2, cars));
	}

	@Test
	void searchForReservation() {

		assertEquals(r1, company.findReservation("123456789"));
		assertEquals(null, company.findReservation("dfh384"));

	}

	@Test
	void calculateDaysTest() {

		assertEquals(3, ui.calculateDays(new Date(2021, 3, 4), new Date(2021, 3, 1)));

	}

	@Test
	void checkPhoneTest() {

		assertTrue(ui.checkPhone("47629184"));
		assertFalse(ui.checkPhone("1234"));
		assertFalse(ui.checkPhone("64gfjrf"));
	}

}

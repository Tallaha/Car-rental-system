package main;

import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Brand;
import entities.Car;
import entities.Chassis;
import entities.Model;
import entities.Office;
import entities.Company;

public class CarFactory {

	private List<Car> longyearCars;
	private List<Car> osloCars;
	private List<Car> allCars;
	private List<Office> offices;
	
	public CarFactory() {
		longyearCars = new ArrayList<>();
		osloCars = new ArrayList<>();
		allCars = new ArrayList<>();
		offices = new ArrayList<>();
	}
	
	public void setup(){
		
		Car c1 = new Car("EL47015", Brand.VW, Model.E_UP, "grey", Chassis.A, 80000, 1000);
		Car c2 = new Car("EK98253", Brand.TESLA, Model.S, "black", Chassis.B, 100000, 2500);

		longyearCars.add(c1);
		longyearCars.add(c2);
		allCars.add(c1);
		allCars.add(c2);
		
		Car c3 = new Car("EL74016", Brand.VW, Model.E_UP, "grey", Chassis.A, 80000, 1500);
		Car c4 = new Car("EK68530", Brand.NISSAN, Model.LEAF, "grey", Chassis.A, 80000, 800);

		osloCars.add(c3);
		osloCars.add(c4);
		allCars.add(c3);
		allCars.add(c4);
		
		Office o1 = new Office(1, new Address("Kong haraldsgate 12", 9170, "Longyearbyen"), 51390477);
		Office o2 = new Office(2, new Address("Helmerz Strandgate 50", 1259, "Oslo"), 51490377);
		
		o1.addCars(longyearCars);
		o2.addCars(osloCars);

		offices.add(o1);
		offices.add(o2);
		
		
	}
	
	public List<Car> getAllCars(){
		
		return allCars;
	}

	public List<Car> getLongyearCars() {

		return longyearCars;

	}

	public List<Car> getOsloCars() {

		return osloCars;

	}

	public List<Office> getOffices() {

		return offices;
	}

	//	Car c3 = new Car("EL47015", Brand.AUDI, Model.E_UP, "grey", Chassis.A, 80000);
	//	Car c4 = new Car("EK68530", Brand.NISSAN, Model.LEAF, "grey", Chassis.A, 80000);
	//	Car c5 = new Car("EL47015", Brand.BMW, Model.E_UP, "grey", Chassis.A, 80000);
	//	Car c6 = new Car("EL47015", Brand.TESLA, Model.E_UP, "grey", Chassis.A, 80000);
	//	Car c7 = new Car("EL47015", Brand.VW, Model.E_UP, "grey", Chassis.A, 80000);
	//	Car c8 = new Car("EL47015", Brand.AUDI, Model.E_UP, "grey", Chassis.A, 80000);
	//	Car c9 = new Car("EL47015", Brand.BMW, Model.E_UP, "grey", Chassis.A, 80000);
	//	Car c10 = new Car("EL47015", Brand.VW, Model.E_UP, "grey", Chassis.A, 80000);

	//	mogCars.add(c3);
	//	mogCars.add(c4);
	//	cars.add(c5);
	//	cars.add(c6);
	//	cars.add(c7);
	//	cars.add(c8);
	//	cars.add(c9);
	//	cars.add(c10);

}

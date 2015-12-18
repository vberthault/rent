package fr.efrei.rent.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.efrei.rent.model.Car;

//@Component
@Repository
public class CarRepositoryImpl implements CarRepository {
	
	@PersistenceContext
	transient EntityManager entityManager;
	
	@Override
	public List<Car> findCars() {
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car();
		car.setPlateNumber("11AA22");
		car.setRented(false);
		cars.add(car);
		car = new Car();
		car.setPlateNumber("33BB44");
		car.setRented(false);
		cars.add(car);
		return cars;
	}

}

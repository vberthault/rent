package fr.efrei.rent.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		car = saveCar(car);
		cars.add(car);
		car = new Car();
		car.setPlateNumber("33BB44");
		car.setRented(false);
		cars.add(car);
		
		Collection<Car> cars2 = findAllCars();
		
		return cars;
	}
	
	@Transactional
	public Car saveCar(Car car) {
		car = entityManager.merge(car);
		return car;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Car> findAllCars() {
		Query query = entityManager.createQuery("SELECT c FROM Car c");
		return (Collection<Car>) query.getResultList();
	}

}

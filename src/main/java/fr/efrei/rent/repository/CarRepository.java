package fr.efrei.rent.repository;

import java.util.List;

import fr.efrei.rent.model.Car;

public interface CarRepository {

	List<Car> findCars();

}

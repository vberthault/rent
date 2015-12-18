package fr.efrei.rent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.efrei.rent.model.Car;
import fr.efrei.rent.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Override
	public List<Car> getAllCars() {
		return carRepository.findCars();
	}

}

package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Car;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dto.CarDTO;

@Controller
@Component
public class MyRentController implements RentService{

	List<Car> cars = new ArrayList<Car>();
	
	public MyRentController(){
		Car car = new Car();
		car.setPlateNumber("11AA22");
		car.setRented(false);
		
		cars.add(car);

		car = new Car();
		car.setPlateNumber("33BB44");
		car.setRented(false);
		
		cars.add(car);
	}
	
	/**
	*
	* @return all cars not rented
	*/
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@Override
	public List<CarDTO> getCars() {
		
		List<CarDTO> dtos = new ArrayList<CarDTO>();
		
		for(Car car: cars){
			if(car.isRented() == false){
				dtos.add( new CarDTO(car));
			}
		}
		return dtos;
	}

	/**
	* Return specifications of a car.
	* @param plateNumber
	* @return car specifications only (if not rented)
	* @throws Exception no car with this plate number or already rented
	*/
	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@Override
	public CarDTO getCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getPlateNumber().equals(plateNumber)==false){
			i++;
		}
		if(i<cars.size()){		// trouvé 
			Car car = cars.get(i);
			if(car.isRented() == false){
				return new CarDTO(car);
			} else {
				throw new Exception("Car already rented");
			}
		} else {
			throw new Exception("No car with such a plate number");
		}
	}

	/**
	* Rent a car.
	* @param plateNumber
	* @return car specifications
	* @throws Exception no car with this plate number or already rented
	*/
	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@Override
	public void rentCar(@PathVariable("plateNumber") String plateNumber, @RequestParam(value="rent",required=true)boolean rent) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getPlateNumber().equals(plateNumber)==false){
			i++;
		}
		if(i<cars.size()){		// trouvé 
			Car car = cars.get(i);
			if(rent == true){	// location
				cars.get(i).setRented(true);
			} else {			// ramener
				cars.get(i).setRented(false);
			}
		} else {
			throw new Exception("No car with such a plate number");
		}
		
	}


}

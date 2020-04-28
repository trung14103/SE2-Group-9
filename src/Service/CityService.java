package Service;

import Model.City;

import java.sql.SQLException;
import java.util.List;

public interface CityService {
	City findCityById(Long id);

    List<City> findAll();

    void updateCity(City city);

    void createCity(City city);

    void deleteCity(Long id);

}

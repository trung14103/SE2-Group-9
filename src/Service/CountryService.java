package Service;

import Model.Country;

import java.sql.SQLException;
import java.util.List;

public interface CountryService {
    Country findCountryById(Long id);

    List<Country> findAll();

    void updateCountry(Country country);

    void createCountry(Country country);

    void deleteCountry(Long id);

    Country findCountryByName(String countryName);

    boolean checkExistCountry(String countryName, String oldCountryName);

}
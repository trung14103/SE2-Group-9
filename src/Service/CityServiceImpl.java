package Service;

import Model.City;
import Model.Country;
import Utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService {
    private static String SELECT_CITY_BY_ID = "SELECT * FROM cities WHERE id = ?";

    private static String SELECT_CITY_BY_NAME = "SELECT * FROM cities WHERE name = ?";

    private static String SELECT_ALL_CITY = "SELECT * FROM cities;";

    private static String DELETE_CITY_BY_ID = "DELETE FROM cities WHERE id = ?;";

    private static String UPDATE_CITY_BY_ID = "UPDATE cities SET name = ?, country_id = ?;";

    private static String INSERT_CITY = "INSERT INTO cities (name, country_id) VALUES (?, ?);";

    private static CountryService countryService = new CountryServiceImpl();

    @Override
    public List<City> findAll() {
        Connection con = DBConnect.getConnection();
        List<City> cityList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_CITY);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                City city = new City();
                city.setId(rs.getLong("id"));
                city.setName(rs.getString("name"));
                city.setCountryId(rs.getLong("country_id"));
                city.setCountry(countryService.findCountryById(rs.getLong("country_id")));
                cityList.add(city);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cityList;
    }

    @Override
    public void updateCity(City city) {
        Connection con = DBConnect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(UPDATE_CITY_BY_ID);
            ps.setString(1, city.getName());
            ps.setLong(2, city.getCountryId());
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createCity(City city) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_CITY);
            ps.setString(1, city.getName());
            ps.setLong(2, city.getCountryId());
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public City findCityById(Long id) {
        Connection con = DBConnect.getConnection();
        City city = new City();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_CITY_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                city.setId(rs.getLong("id"));
                city.setName(rs.getString("name"));
                city.setCountryId(rs.getLong("country_id"));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return city;
    }

    @Override
    public void deleteCity(Long id) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(DELETE_CITY_BY_ID);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean checkExistCity(String cityName, String oldCityName) {
        if (!cityName.isEmpty()) {
            City flagCity = findCityByName(cityName);
            if (oldCityName == null) {
                return flagCity.getId() == null;
            } else {
                return cityName.equals(oldCityName) || flagCity.getId() == null;
            }
        }
        return true;
    }

    @Override
    public City findCityByName(String cityName) {
        Connection con = DBConnect.getConnection();
        City city = new City();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_CITY_BY_NAME);
            ps.setString(1, cityName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                city.setId(rs.getLong("id"));
                city.setName(rs.getString("name"));
                city.setCountryId(rs.getLong("country_id"));
                city.setCountry(countryService.findCountryById(rs.getLong("country_id")));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return city;
    }
}


package Service;

import Model.Country;
import Model.User;
import Utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryServiceImpl implements CountryService {
    private static String SELECT_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id = ?";

    private static String SELECT_COUNTRY_BY_Name = "SELECT * FROM countries WHERE name = ?";

    private static String SELECT_ALL_COUNTRY = "SELECT * FROM countries;";

    private static String DELETE_COUNTRY_BY_ID = "DELETE FROM countries WHERE id = ?;";

    private static String UPDATE_COUNTRY_BY_ID = "UPDATE countries SET name = ?, updated_day = ?, continent = ? WHERE id = ?;";

    private static String INSERT_COUNTRY = "INSERT INTO countries ( name, updated_day, continent) VALUES (?, ?, ?);";

    @Override
    public List<Country> findAll() {
        Connection con = DBConnect.getConnection();
        List<Country> countryList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_COUNTRY);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	Country country = new Country();
                country.setId(rs.getLong("id"));
                country.setName(rs.getString("name"));
                country.setUpdated_day(rs.getDate("updated_day"));
                country.setContinent(rs.getString("continent"));
            	countryList.add(country);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return countryList;
    }

    @Override
    public void updateCountry(Country country) {
        Connection con = DBConnect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(UPDATE_COUNTRY_BY_ID);
            ps.setString(1, country.getName());
            ps.setDate(2, convertDate(country.getUpdated_day()));
            ps.setString(3, country.getContinent());
            ps.setLong(4, country.getId());
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createCountry(Country country) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_COUNTRY);
            ps.setString(1, country.getName());
            ps.setDate(2, convertDate(country.getUpdated_day()));
            ps.setString(3, country.getContinent());
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Country findCountryById(Long id) {
        Connection con = DBConnect.getConnection();
        Country country = new Country();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_COUNTRY_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	country.setId(rs.getLong("id"));
            	country.setName(rs.getString("name"));
            	country.setUpdated_day(rs.getDate("updated_day"));
            	country.setContinent(rs.getString("continent"));              
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return country;
    }

    @Override
    public void deleteCountry(Long id) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(DELETE_COUNTRY_BY_ID);
            GeneralDataService generalDataService = new GeneralDataServiceImpl();
            generalDataService.deleteGeneralData(generalDataService.findByCountryId(id).getId());
            ps.setLong(1, id);
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Country findCountryByName(String countryName) {
        Connection con = DBConnect.getConnection();
        Country country = new Country();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_COUNTRY_BY_Name);
            ps.setString(1, countryName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                country.setId(rs.getLong("id"));
                country.setName(rs.getString("name"));
                country.setUpdated_day(rs.getDate("updated_day"));
                country.setContinent(rs.getString("continent"));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean checkExistCountry(String countryName, String oldCountryName) {
        if (!countryName.isEmpty()) {
            Country flagUser = findCountryByName(countryName);
            if (oldCountryName == null) {
                return flagUser.getName() == null;
            } else {
                return countryName.equals(oldCountryName) || flagUser.getName()  == null;
            }
        }
        return true;
    }

    private Date convertDate(java.util.Date dateUtil) {
        return new Date(dateUtil.getTime());
    }
}

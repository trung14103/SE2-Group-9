package Service;

import Model.GeneralData;
import Utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralDataServiceImpl implements GeneralDataService {

    private CityService cityService = new CityServiceImpl();
    private CountryService countryService = new CountryServiceImpl();

    @Override
    public GeneralData findGeneralDataById(int id) {
        Connection con = DBConnect.getConnection();
        List<GeneralData> GeneralDataList = new ArrayList<>();
        GeneralData GeneralData = new GeneralData();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM covid_data WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GeneralData.setId(rs.getInt("id"));
                GeneralData.setRecovered(rs.getInt("recovered"));
                GeneralData.setInfected(rs.getInt("infected"));
                GeneralData.setCritical(rs.getInt("critical"));
                GeneralData.setDeath(rs.getInt("death"));
                GeneralData.setCountry_id(rs.getLong("country_id"));
                GeneralData.setCity_id(rs.getLong("city_id"));
                GeneralData.setCity(cityService.findCityById(rs.getLong("city_id")));
                GeneralData.setCountry(countryService.findCountryById(rs.getLong("country_id")));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return GeneralData;
    }

    @Override
    public List<GeneralData> findAll() {
        Connection con = DBConnect.getConnection();
        List<GeneralData> GeneralDataList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM covid_data");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GeneralData GeneralData = new GeneralData();
                GeneralData.setId(rs.getInt("id"));
                GeneralData.setRecovered(rs.getInt("recovered"));
                GeneralData.setInfected(rs.getInt("infected"));
                GeneralData.setCritical(rs.getInt("critical"));
                GeneralData.setDeath(rs.getInt("death"));
                GeneralData.setCountry_id(rs.getLong("country_id"));
                GeneralData.setCity_id(rs.getLong("city_id"));
                GeneralData.setCity(cityService.findCityById(rs.getLong("city_id")));
                GeneralData.setCountry(countryService.findCountryById(rs.getLong("country_id")));
                GeneralDataList.add(GeneralData);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return GeneralDataList;
    }

    @Override
    public void updateGeneralData(GeneralData GeneralData) {
        Connection con = DBConnect.getConnection();
        String sql = "UPDATE covid_data SET recovered = ?, infected = ?,critical = ?, death = ?, country_id = ?," +
                "city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, GeneralData.getRecovered());
            ps.setInt(2, GeneralData.getInfected());
            ps.setInt(3, GeneralData.getCritical());
            ps.setInt(4, GeneralData.getDeath());
            ps.setLong(5, GeneralData.getCountry_id());
            ps.setLong(6, GeneralData.getCity_id());
            ps.setInt(7, GeneralData.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createGeneralData(GeneralData GeneralData) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO covid_data (recovered, infected, critical, death, country_id, city_id) VALUES" +
                "(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, GeneralData.getRecovered());
            ps.setInt(2, GeneralData.getInfected());
            ps.setInt(3, GeneralData.getCritical());
            ps.setInt(4, GeneralData.getDeath());
            ps.setLong(5, GeneralData.getCountry_id());
            ps.setLong(6, GeneralData.getCity_id());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteGeneralData(int id) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM covid_data WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<GeneralData> findCityOfVietnam() {
        Connection con = DBConnect.getConnection();
        List<GeneralData> GeneralDataList = new ArrayList<>();
        GeneralData generalData = new GeneralData();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM covid_data WHERE country_id = ?");
            ps.setInt(1, 230);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                generalData.setId(rs.getInt("id"));
                generalData.setRecovered(rs.getInt("recovered"));
                generalData.setInfected(rs.getInt("infected"));
                generalData.setCritical(rs.getInt("critical"));
                generalData.setDeath(rs.getInt("death"));
                generalData.setCountry_id(rs.getLong("country_id"));
                generalData.setCity_id(rs.getLong("city_id"));
                generalData.setCity(cityService.findCityById(rs.getLong("city_id")));
                generalData.setCountry(countryService.findCountryById(rs.getLong("country_id")));
                GeneralDataList.add(generalData);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return GeneralDataList;
    }

    @Override
    public GeneralData findByCountryId(Long id) {
        Connection con = DBConnect.getConnection();
        List<GeneralData> GeneralDataList = new ArrayList<>();
        GeneralData GeneralData = new GeneralData();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM covid_data WHERE country_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GeneralData.setId(rs.getInt("id"));
                GeneralData.setRecovered(rs.getInt("recovered"));
                GeneralData.setInfected(rs.getInt("infected"));
                GeneralData.setCritical(rs.getInt("critical"));
                GeneralData.setDeath(rs.getInt("death"));
                GeneralData.setCountry_id(rs.getLong("country_id"));
                GeneralData.setCity_id(rs.getLong("city_id"));
                GeneralData.setCity(cityService.findCityById(rs.getLong("city_id")));
                GeneralData.setCountry(countryService.findCountryById(rs.getLong("country_id")));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return GeneralData;
    }

    @Override
    public List<GeneralData> findByContinent(String continent) {
        Connection con = DBConnect.getConnection();
        List<GeneralData> GeneralDataList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM covid_data cd INNER JOIN countries co ON (cd.country_id = co.id) WHERE co.continent = ?");
            ps.setString(1, continent);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GeneralData GeneralData = new GeneralData();
                GeneralData.setId(rs.getInt("id"));
                GeneralData.setRecovered(rs.getInt("recovered"));
                GeneralData.setInfected(rs.getInt("infected"));
                GeneralData.setCritical(rs.getInt("critical"));
                GeneralData.setDeath(rs.getInt("death"));
                GeneralData.setCountry_id(rs.getLong("country_id"));
                GeneralData.setCity_id(rs.getLong("city_id"));
                GeneralData.setCity(cityService.findCityById(rs.getLong("city_id")));
                GeneralData.setCountry(countryService.findCountryById(rs.getLong("country_id")));
                GeneralDataList.add(GeneralData);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return GeneralDataList;
    }
}
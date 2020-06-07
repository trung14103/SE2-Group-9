package Service;

import Model.GeneralData;
import Response.TotalData;
import Utils.DBConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GeneralDataServiceImpl implements GeneralDataService {
    private static final String SUM_PRESENT_QUERY = "SELECT SUM(recovered) as totalRecovered, SUM(infected) as totalInfected, SUM(critical) as totalCritical, SUM(death) as totalDeath from covid_data  group by CAST(updated_day AS DATE)";

    private static final String SAVE_TOTAL_DATA_QUERY = "INSERT INTO total_satistics (total_death, total_recovered, total_critical, total_infected, update_time) VALUES (?, ?, ?, ?, ?)";

    private static final String GET_ALL_DATA_QUERY = "SELECT * FROM total_satistics";

    private static final String GET_TOP_STATE_QUERY = "Select * from covid_data where infected > 100000";

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
                GeneralData.setUpdatedDay(rs.getDate("updated_day"));
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
                GeneralData.setUpdatedDay(rs.getDate("updated_day"));
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
    public void updateGeneralData(GeneralData generalData) {
        Connection con = DBConnect.getConnection();
        String sql = "UPDATE covid_data SET recovered = ?, infected = ?,critical = ?, death = ?, country_id = ?," +
                "city_id = ?, updated_day = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, generalData.getRecovered());
            ps.setInt(2, generalData.getInfected());
            ps.setInt(3, generalData.getCritical());
            ps.setInt(4, generalData.getDeath());
            ps.setLong(5, generalData.getCountry_id());
            ps.setLong(6, generalData.getCity_id());
            ps.setInt(8, generalData.getId());
            ps.setDate(7, convertDate(generalData.getUpdatedDay()));
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createGeneralData(GeneralData GeneralData) {
        Connection con = DBConnect.getConnection();
        String sql = "INSERT INTO covid_data (recovered, infected, critical, death, country_id, city_id, updated_day) VALUES" +
                "(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, GeneralData.getRecovered());
            ps.setInt(2, GeneralData.getInfected());
            ps.setInt(3, GeneralData.getCritical());
            ps.setInt(4, GeneralData.getDeath());
            ps.setLong(5, GeneralData.getCountry_id());
            ps.setLong(6, GeneralData.getCity_id());
            ps.setDate(7, convertDate(GeneralData.getUpdatedDay()));
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
                generalData.setUpdatedDay(rs.getDate("updated_day"));
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
                GeneralData.setUpdatedDay(rs.getDate("updated_day"));
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
                GeneralData.setUpdatedDay(rs.getDate("updated_day"));
                GeneralDataList.add(GeneralData);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return GeneralDataList;
    }

    @Override
    public TotalData sumStatistic() {
        Connection con = DBConnect.getConnection();
        TotalData totalData = new TotalData();
        try {
            PreparedStatement ps = con.prepareStatement(SUM_PRESENT_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalData.setDayItem(new java.util.Date());
                totalData.setTotalDeath(rs.getInt("totalDeath"));
                totalData.setTotalInfected(rs.getInt("totalInfected"));
                totalData.setTotalRecovered(rs.getInt("totalRecovered"));
                totalData.setTotalCritical(rs.getInt("totalCritical"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalData;
    }

    @Override
    public void saveSumData(TotalData totalData) {
        Connection connection = DBConnect.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_TOTAL_DATA_QUERY);
            ps.setInt(1, totalData.getTotalDeath());
            ps.setInt(2, totalData.getTotalRecovered());
            ps.setInt(3, totalData.getTotalCritical());
            ps.setInt(4, totalData.getTotalInfected());
            ps.setDate(5, convertDate(totalData.getDayItem()));
            ps.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void getDataAPI() throws IOException {
        GeneralDataService generalDataService = new GeneralDataServiceImpl();
        String url = "https://corona-api.com/countries";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());
        ArrayList<JSONObject> listdata = new ArrayList<JSONObject>();
        JSONArray array = myResponse.getJSONArray("data");
        List<GeneralData> generalDataList = generalDataService.findAll();

        for (int i = 0; i < array.length(); i++) {
            listdata.add(array.optJSONObject(i));
        }

        for (int i = 0; i < listdata.size(); i++) {
            JSONObject covid_data = listdata.get(i).getJSONObject("latest_data");
            JSONObject data = listdata.get(i);
            GeneralData generalData = generalDataList.get(i);
            generalData.setDeath(covid_data.getInt("deaths"));
            generalData.setRecovered(covid_data.getInt("recovered"));
            generalData.setCritical(covid_data.getInt("critical"));
            generalData.setInfected(covid_data.getInt("confirmed"));
            generalData.setUpdatedDay(new java.util.Date());
            generalDataService.updateGeneralData(generalData);
        }
    }

    @Override
    public List<GeneralData> findCountryGreater5000() {
        Connection con = DBConnect.getConnection();
        List<GeneralData> GeneralDataList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_TOP_STATE_QUERY);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GeneralData GeneralData = new GeneralData();
                GeneralData.setId(rs.getInt("id"));
                GeneralData.setRecovered(rs.getInt("recovered"));
                GeneralData.setInfected(rs.getInt("infected"));
                GeneralData.setCritical(rs.getInt("critical"));
                GeneralData.setDeath(rs.getInt("death"));
                GeneralData.setUpdatedDay(rs.getDate("updated_day"));
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
    public List<TotalData> getAllTotalData() {
        Connection con = DBConnect.getConnection();
        List<TotalData> totalDataList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(GET_ALL_DATA_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TotalData totalData = new TotalData();
                totalData.setDayItem(rs.getDate("update_time"));
                totalData.setTotalDeath(rs.getInt("total_death"));
                totalData.setTotalInfected(rs.getInt("total_infected"));
                totalData.setTotalRecovered(rs.getInt("total_recovered"));
                totalData.setTotalCritical(rs.getInt("total_critical"));
                totalDataList.add(totalData);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDataList;
    }

    private Date convertDate(java.util.Date dateUtil) {
        if (dateUtil == null) {
            return null;
        }
        return new Date(dateUtil.getTime());
    }

}
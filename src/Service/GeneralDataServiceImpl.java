package Service;

import Model.GeneralData;
import Utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralDataServiceImpl implements GeneralDataService{

    @Override
    public GeneralData findGeneralDataById(int id) {
        Connection con = DBConnect.getConnection();
        List<GeneralData> GeneralDataList = new ArrayList<>();
        GeneralData GeneralData = new GeneralData();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM GeneralData WHERE id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GeneralData.setId(rs.getInt("id"));
                GeneralData.setRecovered(rs.getInt("recovered"));
                GeneralData.setInfected(rs.getInt("infected"));
                GeneralData.setCritical(rs.getInt("critical"));
                GeneralData.setDeath(rs.getInt("death"));
                GeneralData.setCountry_id(rs.getInt("country_id"));
                GeneralData.setCity_id(rs.getInt("city_id"));

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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM GeneralData");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GeneralData GeneralData = new GeneralData();
                GeneralData.setId(rs.getInt("id"));
                GeneralData.setRecovered(rs.getInt("recovered"));
                GeneralData.setInfected(rs.getInt("infected"));
                GeneralData.setCritical(rs.getInt("critical"));
                GeneralData.setDeath(rs.getInt("death"));
                GeneralData.setCountry_id(rs.getInt("country_id"));
                GeneralData.setCity_id(rs.getInt("city_id"));
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
        String sql ="UPDATE GeneralData SET recovered = ?, infected = ?,critical = ?, death = ?, country_id = ?," +
                "city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,GeneralData.getRecovered());
            ps.setInt(2,GeneralData.getInfected());
            ps.setInt(3,GeneralData.getCritical());
            ps.setInt(4,GeneralData.getDeath());
            ps.setInt(5,GeneralData.getCountry_id());
            ps.setInt(6,GeneralData.getCity_id());
            ps.setInt(7,GeneralData.getId());
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createGeneralData(GeneralData GeneralData) {
        Connection con = DBConnect.getConnection();
        String sql ="INSERT INTO GeneralData (recovered, infected, critical, death, country_id, city_id) VALUES" +
                "(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,GeneralData.getRecovered());
            ps.setInt(2,GeneralData.getInfected());
            ps.setInt(3,GeneralData.getCritical());
            ps.setInt(4,GeneralData.getDeath());
            ps.setInt(5,GeneralData.getCountry_id());
            ps.setInt(6,GeneralData.getCity_id());
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteGeneralData(int id) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM GeneralData WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
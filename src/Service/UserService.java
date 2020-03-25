package Service;

import Model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final String INSERT_USERS = "INSERT INTO users" + " (username, password, email, address, country, gender) VALUES" + "(?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT u" + "FROM users u" + "WHERE u.id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER = "DELETE FROM users where id =?;";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, email = ?, address = ?, country = ?, gender = ? WHERE id =?;";


    DBConnection dbConnection;

    public UserService() {

    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = dbConnection.checkUser();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setInt(6, user.getGender());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean updateUser(User user) throws SQLException {
        boolean flag = false;
        try (Connection connection = dbConnection.checkUser();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setInt(6, user.getGender());
            preparedStatement.setLong(7, user.getId());

            flag = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return flag;
    }

    public User getUserById(Long id) throws SQLException {
        User user = null;

        try (Connection connection = dbConnection.checkUser();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                String address = resultSet.getString("address");
                int gender = resultSet.getInt("gender");

                user = new User(id, username, password, email, country, address, gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        try (Connection connection = dbConnection.checkUser();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                String address = resultSet.getString("address");
                int gender = resultSet.getInt("gender");

                User user = new User(id, username, password, email, country, address, gender);
                userList.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return userList;
    }
    
    public boolean deleteUser(Long id) throws SQLException {
        boolean flag = false;
        
        try (Connection connection = dbConnection.checkUser();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
            preparedStatement.setLong(1, id);
            flag = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return flag;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQL State: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code" + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable throwable = ex.getCause();
                while (throwable != null) {
                    System.out.println("Cause: " + throwable);
                    throwable = throwable.getCause();
                }
            }
        }
    }
}

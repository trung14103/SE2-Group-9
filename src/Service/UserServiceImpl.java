package Service;

import Model.User;
import Utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static String SELECT_USER_BY_NAME = "SELECT * FROM users WHERE username = ?";

    private static String SELECT_ALL_USER = "SELECT * FROM users;";

    private static String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";

    private static String UPDATE_USER_BY_ID = "UPDATE users SET username = ?, password = ?, email = ?, address =?, role = ?;";

    private static String INSERT_USER = "INSERT INTO users ( username, password, email, address, role, created_date) VALUES (?, ?, ?, ?, ?, ?);";

    private static String SELECT_USER_BY_NAME_PASSWORD = "SELECT * FROM users WHERE username = ?, password = ?;";

    @Override

    public List<User> findAll() {
        Connection con = DBConnect.getConnection();
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_USER);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setCreated_date(rs.getDate("created_date"));
                userList.add(user);
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
    }

    @Override
    public void updateUser(User user) {
        Connection con = DBConnect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(UPDATE_USER_BY_ID);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getAddress());
            ps.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createUser(User user) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getAddress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User findUserById(Long id) {
        Connection con = DBConnect.getConnection();
        User user = new User();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setCreated_date(rs.getDate("created_date"));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        Connection con = DBConnect.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(DELETE_USER_BY_ID);
            ps.setLong(1, id);
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean login(String username, String password) {
        Connection con = DBConnect.getConnection();
        boolean isSuccess = false;
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_NAME_PASSWORD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                con.close();
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    @Override
    public User findUserByName(String username) {
        Connection con = DBConnect.getConnection();
        User user = new User();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_NAME);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setCreated_date(rs.getDate("created_date"));
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean checkExistUser(String username, String oldUsername) {
        if (!username.isEmpty()) {
            User flagUser = findUserByName(username);
            if (oldUsername == null) {
                return flagUser == null;
            } else {
                return username.equals(oldUsername) || flagUser == null;
            }
        }
        return true;
    }
}

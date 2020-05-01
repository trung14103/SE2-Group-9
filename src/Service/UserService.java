package Service;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User findUserById(Long id);

    List<User> findAll();

    void updateUser(User user);

    void createUser(User user);

    void deleteUser(Long id);

    String login(String username, String password);

    User findUserByName(String username);

    boolean checkExistUser(String username, String oldUsername);
}

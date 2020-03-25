package Servlet;

import Model.User;
import Service.UserService;
import util.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DBConnection dbConnection;
    private Connection con;
    private UserService userService;

    @Override
    public void init() {
        dbConnection = new DBConnection();
        con = dbConnection.checkUser();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<User> userList = userService.getAllUsers();
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-form.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-edit-form.jsp");
        request.setAttribute("user", user);
        requestDispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setAddress(request.getParameter("address"));
        user.setCountry(request.getParameter("country"));
        user.setEmail(request.getParameter("email"));
        user.setGender(Integer.parseInt(request.getParameter("username")));
        user.setPassword(request.getParameter("password"));

        userService.insertUser(user);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        User user = new User();
        user.setId(Long.parseLong(request.getParameter("id")));
        user.setUsername(request.getParameter("username"));
        user.setAddress(request.getParameter("address"));
        user.setCountry(request.getParameter("country"));
        user.setEmail(request.getParameter("email"));
        user.setGender(Integer.parseInt(request.getParameter("username")));
        user.setPassword(request.getParameter("password"));

        userService.insertUser(user);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.deleteUser(id);
        response.sendRedirect("list");
    }
}

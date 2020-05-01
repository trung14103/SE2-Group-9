package Controller;

import Model.Country;
import Model.User;
import Service.CountryServiceImpl;
import Service.UserService;
import Service.UserServiceImpl;
import Utils.PatternChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "userAPI", urlPatterns = "/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String dateFormat = "yyyy-MM-dd";

    private UserService userService;
    public void init() {
    	userService = new UserServiceImpl();
 	}
    private PatternChecker patternChecker;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");
        switch (command) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertUser(request, response);
                break;
            case "edit":
                try {
                    showEditForm(request, response);
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            case "update":
                try {
                    updateUser(request, response);
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            case "delete":
                try {
                    deleteUser(request,response);
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            default:
                try {
                    listUser(request, response);
                    break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userService.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-view.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User existingUser = userService.findUserById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");       
        Long id = Long.parseLong(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        Date created_date = convertToDate(request.getParameter("created_date"));

        String err = "";
        String url = "/user-form.jsp";

        if (username.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty() || role.isEmpty()) {
            err = "Please fill in necessary information";
        } else {
            if (!patternChecker.checkEmail(email)) {
                err = "Invalid Email Format";
            }
        }

        if (err.length() > 0) {
            request.setAttribute("error", err);
        }

        try {
            if (err.length() == 0) {
            	User user = new User(id, username, password, address, email, role, created_date);
                userService.updateUser(user);
                url = "/home.jsp";
            } else {
                url = "/user-form.jsp";
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.deleteUser(id);
        response.sendRedirect(request.getServletPath() + "?command=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user-form.jsp");
        requestDispatcher.forward(request, response);
    }
  
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = new User();
        String username = request.getParameter("username");
        if (userService.checkExistUser(username, null)) {
            user.setUsername(request.getParameter("username"));
            user.setAddress(request.getParameter("address"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setRole(request.getParameter("role"));
            user.setCreated_date(new Date());

            userService.createUser(user);
            response.sendRedirect(request.getServletPath() + "?command=list");
        } else {        
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
            request.setAttribute("err", "User is already existed");
            dispatcher.forward(request, response);
        }
    }

    private static Date convertToDate(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

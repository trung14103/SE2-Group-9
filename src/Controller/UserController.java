package Controller;

import Model.User;
import Service.UserService;
import Utils.PatternChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "userAPI", urlPatterns = "/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String dateFormat = "yyyy-MM-dd";

    private UserService userService;

    private PatternChecker patternChecker;

    public UserController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

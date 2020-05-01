package Controller;

import Service.UserService;
import Service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void loginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        switch (command) {
            case "view":
                loginView(request, response);
                break;
            case "check":
                checkLogin(request, response);
                break;
        }
    }

    protected void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = "/login.jsp";
        String error = "";
        String role = userService.login(username, password);

        if (username.isEmpty() && password.isEmpty()) {
            error = "Please fill in the necessary information";
        } else if (role.equals("")) {
            error = "Username Or Password Is Invalid";
        }

        try {
            if (error.length() == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("role", role);
                session.setAttribute("username", username);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("statistics.jsp");
                System.out.println(request.getSession().getAttribute("role"));
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("error", error);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

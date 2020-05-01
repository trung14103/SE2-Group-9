package Controller;

import Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private UserService userService;

    protected void loginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String command = request.getParameter("command");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = "/login.jsp";
        String error = "";

        if (username.isEmpty() && password.isEmpty()) {
            error = "Please fill in the necessary information";
        } else if (userService.login(username, password) == false) {
            error = "Username Or Password is valid";
        }

        if (error.length() > 0) {
            request.setAttribute("error", error);
        }

        try {
            if (error.length() == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                userService.login(username, password);
                Cookie loginCookie = new Cookie("username", username);

                loginCookie.setMaxAge(60*60);
                response.addCookie(loginCookie);
                response.sendRedirect("home.jsp");
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login.jsp");
        }
    }
}

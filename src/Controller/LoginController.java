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

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

package Controller;

import Model.GeneralData;
import Service.GeneralDataService;
import Service.GeneralDataServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContinentServlet", urlPatterns = "/continent")
public class ContinentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GeneralDataService dataService = new GeneralDataServiceImpl();
        String continent = request.getParameter("continent");
        List<GeneralData> generalData = dataService.findByContinent(continent);
        request.setAttribute("generalData", generalData);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("continent.jsp");
        requestDispatcher.forward(request, response);
    }
}

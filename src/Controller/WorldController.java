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

@WebServlet(name = "WorldServlet", urlPatterns = "/world")
public class WorldController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GeneralDataService dataService = new GeneralDataServiceImpl();
        List<GeneralData> generalData = dataService.findAll();
        request.setAttribute("generalData", generalData);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("world.jsp");
        requestDispatcher.forward(request, response);
    }
}

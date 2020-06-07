package Controller;

import Model.City;
import Model.GeneralData;
import Service.CityService;
import Service.CityServiceImpl;
import Service.GeneralDataService;
import Service.GeneralDataServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "HomeServlet", urlPatterns = {"/", "/home"})
public class HomeServlet extends HttpServlet {

    private CityService cityService;
    private GeneralDataService generalDataService;

    public void init() {
        cityService = new CityServiceImpl();
        generalDataService = new GeneralDataServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<City> vnData = cityService.findAll();
        GeneralData vietNamData = generalDataService.findByCountryId(Long.parseLong("230"));
//        generalDataService.saveSumAndGetDataManually();
        request.setAttribute("ovnData", vietNamData);
        request.setAttribute("sumGlobal", generalDataService.sumStatistic());
        request.setAttribute("vnData", vnData);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("statistics.jsp");
        requestDispatcher.forward(request, response);
    }
}

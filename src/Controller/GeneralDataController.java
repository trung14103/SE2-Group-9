package Controller;

import Model.City;
import Model.Country;
import Service.*;
import Model.GeneralData;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/generalData")
public class GeneralDataController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GeneralDataService generalDataService;
    private   CountryService countryService;
    private  CityService cityService;

    public void init() {
        countryService = new CountryServiceImpl();
        cityService = new CityServiceImpl();
        generalDataService = new GeneralDataServiceImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try {
            String command = request.getParameter("command");
            switch (command) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertGeneralData(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateGeneralData(request, response);
                    break;
                case "delete":
                    deleteGeneralData(request, response);
                    break;
                default:
                    listGeneralData(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listGeneralData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<GeneralData> GeneralDataList = generalDataService.findAll();
        request.setAttribute("listGeneralData", GeneralDataList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("covid-data-view.jsp");
        dispatcher.forward(request, response);
    }

    void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("covid-data-form.jsp");
        dispatcher.forward(request, response);
    }

    void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GeneralData GeneralData = generalDataService.findGeneralDataById(id);
        request.setAttribute("GeneralData", GeneralData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("covid-data-form.jsp");
        dispatcher.forward(request, response);
    }

    void insertGeneralData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int recovered = Integer.parseInt(request.getParameter("recovered"));
        int infected = Integer.parseInt(request.getParameter("infected"));
        int critical = Integer.parseInt(request.getParameter("critical"));
        int death = Integer.parseInt(request.getParameter("death"));
        long country_id = Integer.parseInt(request.getParameter("country_id"));
        long city_id = Integer.parseInt(request.getParameter("city_id"));
        Country country = countryService.findCountryById(country_id);
        City city = cityService.findCityById(city_id);
        if (country != null) {
            GeneralData generalData = new GeneralData();
            generalData.setRecovered(recovered);
            generalData.setInfected(infected);
            generalData.setCritical(critical);
            generalData.setDeath(death);
            generalData.setCity_id(city_id);
            generalData.setCountry_id(country_id);
            generalData.setCountry(country);
            generalData.setCity(city);
            generalData.setUpdatedDay(new Date());
            generalDataService.createGeneralData(generalData);
            response.sendRedirect("generalData?command=list");
        }
        else {
            String err = "City or Country is not existed";
            request.setAttribute("err", err);
            RequestDispatcher dispatcher = request.getRequestDispatcher("generalData?command=new");
            dispatcher.forward(request, response);
        };
    }

    void updateGeneralData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int recovered = Integer.parseInt(request.getParameter("recovered"));
        int infected = Integer.parseInt(request.getParameter("infected"));
        int critical = Integer.parseInt(request.getParameter("critical"));
        int death = Integer.parseInt(request.getParameter("death"));
        long country_id = Integer.parseInt(request.getParameter("country_id"));
        long city_id = Integer.parseInt(request.getParameter("city_id"));
        Country country = countryService.findCountryById(country_id);
        City city = cityService.findCityById(city_id);
        if (country != null) {
            GeneralData GeneralData = new GeneralData(id, recovered, infected, critical, death, country_id, city_id, new Date(), country,city);
            generalDataService.updateGeneralData(GeneralData);
            response.sendRedirect("generalData?command=list");}
        else {
            String err = "City or Country is not existed";
            request.setAttribute("err", err);
            RequestDispatcher dispatcher = request.getRequestDispatcher("generalData?command=new");
            dispatcher.forward(request, response);
        };
    }

    void deleteGeneralData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        generalDataService.deleteGeneralData(id);
        response.sendRedirect("generalData?command=list");
    }
}
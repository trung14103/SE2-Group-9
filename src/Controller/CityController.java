package Controller;

import Model.City;
import Model.Country;
import Service.CityService;
import Service.CityServiceImpl;
import Service.CountryService;
import Service.CountryServiceImpl;;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "city", urlPatterns = "/city")
public class CityController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String dateFormat = "yyyy-MM-dd";

    private CityService cityService;
    private CountryService countryService;

    public void init() {
        cityService = new CityServiceImpl();
        countryService = new CountryServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        String command = req.getParameter("command");
        try {
            switch (command) {
                case "new":
                    showNewForm(req, res);
                    break;
                case "insert":
                    insertCity(req, res);
                    break;
                case "edit":
                    showEditForm(req, res);
                    break;
                case "update":
                    updateCity(req, res);
                    break;
                case "delete":
                    deleteCity(req, res);
                    break;
                default:
                    listCity(req, res);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<City> listCity = cityService.findAll();
        request.setAttribute("listCity", listCity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/city-view.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/city-form.jsp");
        rd.forward(req, res);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        City existingCity = cityService.findCityById(id);
        existingCity.setCountry(countryService.findCountryById(existingCity.getCountryId()));

        RequestDispatcher rd = req.getRequestDispatcher("/city-form.jsp");
        req.setAttribute("city", existingCity);
        rd.forward(req, res);
    }

    private void insertCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        City city = new City();
        String cityName = request.getParameter("name");
        String countryName = request.getParameter("countryName");
        Country country = countryService.findCountryByName(countryName);
        int recovered = Integer.parseInt(request.getParameter("recovered"));
        int infected = Integer.parseInt(request.getParameter("infected"));
        int critical = Integer.parseInt(request.getParameter("critical"));
        int death = Integer.parseInt(request.getParameter("death"));
        String errCity = "";
        String errCountry = "";
        if (country.getId() == null) {
            errCountry = "Country is not existed";
        } if (cityName.isEmpty() || countryName == null) {
            errCity = "Please fill in necessary information";
        } if (!cityService.checkExistCity(cityName, null)) {
            errCity = "City is already existed";
        }

        if (errCity.length() == 0 && errCountry.length() == 0) {
            city.setName(request.getParameter("name"));
            city.setCountryId(country.getId());
            city.setCritical(critical);
            city.setDeath(death);
            city.setInfected(infected);
            city.setRecovered(recovered);
            cityService.createCity(city);
            response.sendRedirect(request.getServletPath() + "?command=list");
        } else {
            request.setAttribute("errorCity", errCity);
            request.setAttribute("errorCountry", errCountry);
            RequestDispatcher dispatcher = request.getRequestDispatcher(request.getServletPath() + "?command=new");
            dispatcher.forward(request, response);
        }
    }

    private void updateCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String oldName = request.getParameter("oldCityName");
        Long countryId = Long.parseLong(request.getParameter("countryId"));
        Country country = countryService.findCountryById(countryId);
        int recovered = Integer.parseInt(request.getParameter("recovered"));
        int infected = Integer.parseInt(request.getParameter("infected"));
        int critical = Integer.parseInt(request.getParameter("critical"));
        int death = Integer.parseInt(request.getParameter("death"));
        String err = "";
        String url;

        if (name.isEmpty()) {
            err = "Please fill in necessary information";
        } else if (country.getId() == null) {
            err = "Country is not existed";
        } else if (!countryService.checkExistCountry(name, oldName)) {
            err = "City is already existed";
        }

        try {
            if (err.length() == 0) {
                City city = new City(id, name, countryId, recovered, infected, critical, death, countryService.findCountryById(countryId));
                cityService.updateCity(city);
                response.sendRedirect(request.getServletPath() + "?command=list");
            } else {
                url = "/city-form.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(request.getServletPath() + "?command=edit&id=" + id);
                request.setAttribute("error", err);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        cityService.deleteCity(id);
        response.sendRedirect(request.getServletPath() + "?command=list");
    }

}

package Controller;

import Model.Country;
import Service.CountryService;
import Service.CountryServiceImpl;

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

@WebServlet(name = "country", urlPatterns = "/country")
public class CountryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String dateFormat = "yyyy-MM-dd";

    private CountryService countryService;
	public void init() {
		countryService = new CountryServiceImpl();
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
                    insertCountry(req, res);
                    break;
                case "edit":
                    showEditForm(req, res);
                    break;
                case "update":
                    updateCountry(req, res);
                    break;
                case "delete":
                    deleteCountry(req, res);
                    break;
                default:
                    listCountry(req, res);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Country> listCountry = countryService.findAll();
        request.setAttribute("listCountry", listCountry);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/country-view.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/country-form.jsp");
        rd.forward(req, res);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Country existingCountry = countryService.findCountryById(id);

        RequestDispatcher rd = req.getRequestDispatcher("/country-form.jsp");
        req.setAttribute("country", existingCountry);
        rd.forward(req, res);
    }

    private void insertCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Country country = new Country();
        String name = request.getParameter("name");

        if (countryService.checkExistCountry(name, null)) {
            country.setName(request.getParameter("name"));
            country.setUpdated_day(new Date());
            country.setContinent(request.getParameter("continent"));
            countryService.createCountry(country);
            response.sendRedirect(request.getServletPath() + "?command=list");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/country-form.jsp");
            request.setAttribute("error", "Country is already existed");
            rd.forward(request, response);
        }
    }

    private void updateCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Country country = new Country();
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String continent = request.getParameter("continent");
        String oldCountryName = request.getParameter("oldCountryName");

        String err = "";
        String url = "/country-form.jsp";

        if (name.isEmpty() || continent.isEmpty()) {
            err = "Please fill in necessary information";
        } if (!countryService.checkExistCountry(name, oldCountryName)) {
            err = "Country is already existed";
        }

        try {
            if (err.length() == 0) {
                country.setName(request.getParameter("name"));
                country.setUpdated_day(new Date());
                country.setContinent(request.getParameter("continent"));
                country.setId(id);

                countryService.updateCountry(country);
                response.sendRedirect(request.getServletPath() + "?command=list");
            } else {
                url = "/country-form.jsp";
                request.setAttribute("error", err);
                RequestDispatcher dispatcher = request.getRequestDispatcher(request.getServletPath() + "?command=edit&id=" + id);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void deleteCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        countryService.deleteCountry(id);
        response.sendRedirect(request.getServletPath() + "?command=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}




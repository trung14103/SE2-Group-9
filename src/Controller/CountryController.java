package Controller;

import Model.Country;
import Service.CountryService;

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

    public CountryController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 		String command = req.getParameter("command");
		try {
			switch(command) {	
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
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
    
    private void listCountry(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
			List<Country> listCountry = countryService.findAll();
			request.setAttribute("listCountry", listCountry);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/country-list.jsp");
			dispatcher.forward(request, response);
	}
    
    private void showNewForm(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			RequestDispatcher rd =  req.getRequestDispatcher("/country-form.jsp");
			rd.forward(req, res); 
	}
    
    private void showEditForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
    		Long id = Long.parseLong(req.getParameter("id"));
    		Country existingCountry = countryService.findCountryById(id);
        
    		RequestDispatcher rd =  req.getRequestDispatcher("/country-form.jsp");
    		req.setAttribute("country", existingCountry);
    		rd.forward(req, res); 
	}
    
    private void insertCountry(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
    		Country country = new Country();

    		country.setName(request.getParameter("name"));   		
    		country.setUpdated_day(convertToDate(request.getParameter("updated_day")));
    		country.setContinent(request.getParameter("continent"));
    		countryService.createCountry(country);
    		response.sendRedirect(request.getServletPath() + "?command=list");
	}
    
    private void updateCountry(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {		
    		Country country = new Country();
    		country.setName(request.getParameter("name"));   		
    		country.setUpdated_day(convertToDate(request.getParameter("updated_day")));
    		country.setContinent(request.getParameter("continent"));
    		country.setId(Long.parseLong(request.getParameter("id")));

    		countryService.updateCountry(country);
    		response.sendRedirect(request.getServletPath() + "?command=list");
		    
	}
    
    private void deleteCountry(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        	Long id = Long.parseLong(request.getParameter("id"));
        	countryService.deleteCountry(id);
        	response.sendRedirect(request.getServletPath() + "?command=list");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        Country country = new Country();
        Date updated_day =  convertToDate(request.getParameter("updated_day"));
        String continent = request.getParameter("continent");

        String err = "";
        String url = "/country-form.jsp";

        if (name.isEmpty() || continent.isEmpty()) {
            err = "Please fill in necessary information";
        }
        

        if (err.length() > 0) {
            request.setAttribute("error", err);
        }

        try {
            if (err.length() == 0) {
                 country = new Country(id, name, updated_day, continent);
                 countryService.updateCountry(country);
                url = "/home.jsp";
            } else {
                url = "/country-form.jsp";
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




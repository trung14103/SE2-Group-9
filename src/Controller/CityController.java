package Controller;

import Model.City;
import Service.CityService;;

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

    public CityController() {
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
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
    
    private void listCity(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
			List<City> listCity = cityService.findAll();
			request.setAttribute("listCity", listCity);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/city-list.jsp");
			dispatcher.forward(request, response);
	}

    private void showNewForm(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			RequestDispatcher rd =  req.getRequestDispatcher("/city-form.jsp");
			rd.forward(req, res); 
	}
    
    private void showEditForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
    		Long id = Long.parseLong(req.getParameter("id"));
    		City existingCity = cityService.findCityById(id);
        
    		RequestDispatcher rd =  req.getRequestDispatcher("/city-form.jsp");
    		req.setAttribute("city", existingCity);
    		rd.forward(req, res); 
	}
    
    private void insertCity(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
    		City city = new City();

    		city.setName(request.getParameter("name"));

    		cityService.createCity(city);
    		response.sendRedirect(request.getServletPath() + "?command=list");
	}
    
    private void updateCity(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {		
    		City city = new City();
    		city.setName(request.getParameter("name"));       
    		city.setId(Long.parseLong(request.getParameter("id")));

    		cityService.updateCity(city);
    		response.sendRedirect(request.getServletPath() + "?command=list");
		    
	}
    
    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        	Long id = Long.parseLong(request.getParameter("id"));
        	cityService.deleteCity(id);
        	response.sendRedirect(request.getServletPath() + "?command=list");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	request.setCharacterEncoding("utf-8");
        	response.setCharacterEncoding("utf-8");
        	Long id = Long.parseLong(request.getParameter("id"));
        	String name = request.getParameter("name");
        	City city = new City();
        	String err = "";
        	String url = "/city-form.jsp";

        	if (name.isEmpty()) {
        		err = "Please fill in necessary information";
        	}
        

        	if (err.length() > 0) {
        		request.setAttribute("error", err);
        	}

        	try {
        		if (err.length() == 0) {
        			city = new City(id, name);
        			cityService.updateCity(city);
        			url = "/home.jsp";
        		} else {
        			url = "/city-form.jsp";
        		}
        		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        		requestDispatcher.forward(request, response);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
    	}
	}

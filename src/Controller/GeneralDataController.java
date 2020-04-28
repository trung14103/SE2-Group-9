package Controller;

import Service.GeneralDataService;
import Model.GeneralData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/generalData")
public class GeneralDataController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GeneralDataService generalDataService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //TO-DO: Implement switch-case structure for page navigation with request-response
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "New":
                    showNewForm(request, response);
                    break;
                case "Insert":
                    insertGeneralData(request, response);
                    break;
                case "Edit":
                    showEditForm(request, response);
                    break;
                case "Update":
                    updateGeneralData(request, response);
                    break;
                case "Delete":
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("GeneralDataForm.jsp");
        dispatcher.forward(request, response);
    }

    void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GeneralData GeneralData = generalDataService.findGeneralDataById(id);
        request.setAttribute("GeneralData", GeneralData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("GeneralDataForm.jsp");
        dispatcher.forward(request, response);
    }

    void insertGeneralData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int recovered = Integer.parseInt(request.getParameter("recovered"));
        int infected = Integer.parseInt(request.getParameter("infected"));
        int critical = Integer.parseInt(request.getParameter("critical"));
        int death = Integer.parseInt(request.getParameter("death"));
        int country_id = Integer.parseInt(request.getParameter("country_id"));
        int city_id = Integer.parseInt(request.getParameter("city_id"));
        GeneralData GeneralData = new GeneralData(id, recovered, infected, critical, death, country_id, city_id);
        generalDataService.createGeneralData(GeneralData);
        response.sendRedirect(request.getContextPath() + "/GeneralData?action=List");
    }

    void updateGeneralData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int recovered = Integer.parseInt(request.getParameter("recovered"));
        int infected = Integer.parseInt(request.getParameter("infected"));
        int critical = Integer.parseInt(request.getParameter("critical"));
        int death = Integer.parseInt(request.getParameter("death"));
        int country_id = Integer.parseInt(request.getParameter("country_id"));
        int city_id = Integer.parseInt(request.getParameter("city_id"));
        GeneralData GeneralData = new GeneralData(id, recovered, infected, critical, death, country_id, city_id);
        generalDataService.updateGeneralData(GeneralData);
        response.sendRedirect(request.getContextPath() + "/GeneralData?action=List");
    }

    void deleteGeneralData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        generalDataService.deleteGeneralData(id);
        response.sendRedirect(request.getContextPath() + "/GeneralData?action=List");
    }


}

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        request.setAttribute("ovnData", vietNamData);
        request.setAttribute("sumGlobal", sumGlobalData());
        request.setAttribute("vnData", vnData);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("statistics.jsp");
        requestDispatcher.forward(request, response);
    }

    private GeneralData sumGlobalData() {
        List<GeneralData> generalDataList = generalDataService.findAll();
        GeneralData generalData = new GeneralData();
        int infectedSum = 0;
        int deathSum = 0;
        int recoveredSum = 0;
        for (int i = 0; i <  generalDataList.size(); i++) {
            infectedSum += generalDataList.get(i).getInfected();
            deathSum += generalDataList.get(i).getDeath();
            recoveredSum += generalDataList.get(i).getRecovered();
        }
        generalData.setDeath(deathSum);
        generalData.setInfected(infectedSum);
        generalData.setRecovered(recoveredSum);
        return  generalData;
    }
}

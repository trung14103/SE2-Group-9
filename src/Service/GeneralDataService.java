package Service;

import Model.GeneralData;
import Response.TotalData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface GeneralDataService {
    GeneralData findGeneralDataById(int id);

    List<GeneralData> findAll();


    void updateGeneralData(GeneralData GeneralData);

    void createGeneralData(GeneralData GeneralData);

    void deleteGeneralData(int id);

    List<GeneralData> findCityOfVietnam();

    GeneralData findByCountryId(Long id);

    List<GeneralData> findByContinent(String continent);

    TotalData sumStatistic();

    List<GeneralData> findCountryGreater5000();

    void getDataAPI() throws IOException;

    void saveSumData(TotalData totalData);

    List<TotalData> getAllTotalData();

}

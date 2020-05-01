package Service;

import Model.GeneralData;

import java.util.List;

public interface GeneralDataService {
    GeneralData findGeneralDataById(int id);

    List<GeneralData> findAll();


    void updateGeneralData(GeneralData GeneralData);

    void createGeneralData(GeneralData GeneralData);

    void deleteGeneralData(int id);
    
}

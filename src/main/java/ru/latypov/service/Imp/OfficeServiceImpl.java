package ru.latypov.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.latypov.model.Office;
import ru.latypov.repository.OfficeRepository;
import ru.latypov.service.OfficeService;

import java.util.List;
import java.util.Optional;

@Service("OfficeService")
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;
    public List<Office> retrieveOffice(){
        List<Office> office =officeRepository.findAll();
        return office;
    }
    public Office getOffice (Integer id) {
        Optional<Office> optEmp = officeRepository.findById(id);
        return optEmp.get();
    }
    public Office  getOffice (Office office){
        Optional<Office> optEmp1=officeRepository.findById(office.getId());
        return optEmp1.get();
    }
    public Office updateOffice(Office office){
        return officeRepository.save(office);
    }
    public void savesOffice (Office office){
        officeRepository.save(office);
    }
}

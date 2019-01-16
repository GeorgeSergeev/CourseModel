package ru.latypov.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.latypov.model.StatusKurs;
import ru.latypov.repository.StatusKursRepository;
import ru.latypov.service.StatusKursService;

import java.util.List;
import java.util.Optional;

@Service("OfficeService")
public class StatusKursServiceImpl implements StatusKursService {
    @Autowired
    private StatusKursRepository statusKursRepository;
    public List<StatusKurs> retrieveStatusKurs(){
        List<StatusKurs> statusKurs =statusKursRepository.findAll();
        return statusKurs;
    }
    public StatusKurs getStatusKurs (Integer id) {
        Optional<StatusKurs> optEmp = statusKursRepository.findById(id);
        return optEmp.get();
    }
    public StatusKurs  getStatusKurs (StatusKurs statusKurs){
        Optional<StatusKurs> optEmp1=statusKursRepository.findById(statusKurs.getId());
        return optEmp1.get();
    }
    public StatusKurs updateStatusKurs(StatusKurs statusKurs){
        return statusKursRepository.save(statusKurs);
    }
    public void savesStatusKurs (StatusKurs statusKurs){
        statusKursRepository.save(statusKurs);
    } public void deleteStatusKurs (StatusKurs statusKurs){
        statusKursRepository.delete(statusKurs);
    }
}

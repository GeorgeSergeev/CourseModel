package ru.latypov.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.latypov.model.Kurs;
import ru.latypov.repository.KursRepository;
import ru.latypov.service.KursService;

import java.util.List;
import java.util.Optional;
@Service("OrganizationService")
public class KursServiceImpl implements KursService {
    @Autowired
    private KursRepository kursRepository;
    public List<Kurs> retrieveKurs(){
        List<Kurs> kurs=kursRepository.findAll();
        return kurs;
    }
    public Kurs getKurs (Integer id) {
        Optional<Kurs> optEmp = kursRepository.findById(id);
        return optEmp.get();
    }
    public Kurs getKurs (Kurs kurs){
        Optional<Kurs> optEmp1=kursRepository.findById(kurs.getId());
        return optEmp1.get();
    }
    public Kurs updateKurs (Kurs kurs){
        return kursRepository.save(kurs);
    }
    public void savesKurs (Kurs kurs){
        kursRepository.save(kurs);
    }
    public void deleteKurs (Kurs kurs){
        kursRepository.delete(kurs);
    }
}

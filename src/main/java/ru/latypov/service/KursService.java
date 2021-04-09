package ru.latypov.service;

import ru.latypov.exception.KursNotFound;
import ru.latypov.model.Kurs;

import java.util.List;

/*Набор методов для контролера Курс */

public interface KursService {

    public List<Kurs> retrieveKurs();

    public Kurs getKurs(Integer id) throws KursNotFound;

    public void saveKurs (Kurs kurs);
    public void deleteKurs (Kurs kurs);

    public Kurs updateKurs(Kurs kurs);

    public Kurs getKurs(Kurs kurs);
}


package ru.latypov.service;



import ru.latypov.model.StatusKurs;

import java.util.List;

/*Набор методов для контролера Статус */
public interface StatusKursService {
    public List<StatusKurs> retrieveStatusKurs();

    public StatusKurs getStatusKurs(Integer id);

    public void savesStatusKurs(StatusKurs statusKurs);
    public void deleteStatusKurs(StatusKurs statusKurs);

    public StatusKurs updateStatusKurs(StatusKurs statusKurs);

    public StatusKurs getStatusKurs(StatusKurs statusKurs);
}

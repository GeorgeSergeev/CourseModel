package ru.khrebtov.university.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.khrebtov.university.entity.Professor;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProfessorServiceTest {

    private final Professor professor11 = new Professor(11L, "Фиксик А.Б.H", "Луна22", "+78888888898", 28f);
    private final Professor professor6 = new Professor(6L, "Фиксик А.Б.", "Луна22", "+7888888889", 25.0f);
    private final Professor professor4 = new Professor(4L, "Курчатов Ф.П.", "Василево 23", "8800253256", 2000.0f);
    private final Professor professor2 = new Professor(2L, "Павлов В.П.", "Спортивная 23", "+79787594353", 1900.0f);
    private final Professor professor1 = new Professor(1L, "Амосов В.П.", "Спортивная 23", "+79787594352", 24000.0f);
    private final Professor professor15 = new Professor(15L, "Обновленный профессор", "Обн. Новая ул", "+7333", 3382f);
    private final List<Professor> professors = Arrays.asList(professor11, professor6, professor4, professor2, professor1, professor15);


    private List<Professor> professorsFromDto(List<DtoProfessor> dtoProfessors) {
        List<Professor> professorList = new ArrayList<>();
        dtoProfessors.forEach(p -> professorList.add(new Professor(p.getId(), p.getName(),
                p.getAddress(), p.getPhone(), p.getPayment())));
        return professorList;
    }


    @Autowired
    private ProfessorService professorService;

    @Test
    void findAll() {
        List<Professor> professorDtoList = professorsFromDto(professorService.findAll());
        professors.sort(Comparator.comparing(Professor::getId));
        professorDtoList.sort(Comparator.comparing(Professor::getId));

        assertArrayEquals(professors.toArray(), professorDtoList.toArray());
    }

    @Test
    void findById() {
        assertEquals(professor11, new Professor(professorService.findById(11L)));
    }

    @Test
    void count() {
        List<DtoProfessor> professorList = professorService.findAll();
        assertEquals(professorList.size(), professorService.count());
    }

    @Test
    void insert() {
        Professor newProfessor = new Professor(null, "Новый профессор", "Новая ул", "+7333", 332f);
        professorService.insert(new DtoProfessor(newProfessor));
        List<Professor> professors = professorsFromDto(professorService.findAll());
        professors.sort(Comparator.comparing(Professor::getId));
        Professor expected = professors.get(professors.size() - 1);
        newProfessor.setId(expected.getId());
        assertEquals(expected, newProfessor);
        professorService.deleteById(expected.getId());
    }

    @Test
    void update() {
        Professor updatedProfessor = new Professor(15L, "Обновленный профессор", "Обн. Новая ул", "+7333", 3382f);
        professorService.update(new DtoProfessor(updatedProfessor));
        assertEquals(updatedProfessor, new Professor(professorService.findById(updatedProfessor.getId())));
    }


    @Test
    void deleteById() {
        Professor newProfessor = new Professor(null, "Новый профессор", "Новая ул", "+7333", 332f);
        professorService.insert(new DtoProfessor(newProfessor));

        List<Professor> professorDtoList = professorsFromDto(professorService.findAll());
        assertNotEquals(professors.size(), professorService.count());

        professorDtoList.sort(Comparator.comparing(Professor::getId));
        professors.sort(Comparator.comparing(Professor::getId));

        Professor expected = professorDtoList.get(professorDtoList.size() - 1);
        professorService.deleteById(expected.getId());
        List<Professor> professorDtoListAfterDelete = professorsFromDto(professorService.findAll());
        assertArrayEquals(professors.toArray(), professorDtoListAfterDelete.toArray());
    }

}
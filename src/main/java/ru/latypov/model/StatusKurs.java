package ru.latypov.model;

import javax.persistence.*;

/*Модель заполнения таблицы Офис*/
@Entity
@Table(name = "OFFICE")
public class StatusKurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 'Уникальный идентификатор
    @Column(name = "OCENKA")
    private Integer OCENKA; //'Оценка'
    @Column(name = "KURS_ID")
    private Integer kurs_id; //'Поле для связки с таблицей курс'
    @Column(name = "STUDENT_ID")
    private String student_id;    // 'связь с таблицей студент'
    @Column(name = "FINAL_OCENKA   ")
    private Integer final_ocenka; //'финальная оценка'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOCENKA() {
        return OCENKA;
    }

    public void setOCENKA(Integer OCENKA) {
        this.OCENKA = OCENKA;
    }

    public Integer getKurs_id() {
        return kurs_id;
    }

    public void setKurs_id(Integer kurs_id) {
        this.kurs_id = kurs_id;
    }

    public Integer getFinal_ocenka() {
        return final_ocenka;
    }

    public void setFinal_ocenka(Integer final_ocenka) {
        this.final_ocenka = final_ocenka;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }


}

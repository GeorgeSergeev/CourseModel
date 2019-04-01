package org.bajiepka.courseApp;

import lombok.extern.slf4j.Slf4j;
import org.bajiepka.courseApp.domain.Course;
import org.bajiepka.courseApp.domain.ExchangeFile;
import org.bajiepka.courseApp.domain.Student;
import org.bajiepka.courseApp.repositories.CourseRepository;
import org.bajiepka.courseApp.repositories.ExchangeFileRepository;
import org.bajiepka.courseApp.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner populateDatabase(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            ExchangeFileRepository exchangeFileRepository){

        return args -> {
            log.info("Загружаем тестового студента #0" + studentRepository.save(
                    new Student(
                            "Иванов Иван Иванович",
                            "298302, Республика Крым, г. Керчь, ул. Вознесенская, 15 / ф",
                            "+7(978)111-22-33",
                            1000121)));
            log.info("Загружаем тестового студента #1" + studentRepository.save(
                    new Student(
                            "Бобби Джэксон Младший",
                            "298302, Республика Крым, г. Керчь, ул. Провансальная, 22 / 11",
                            "+7(978)111-22-33",
                            1000122)));
            log.info("Загружаем тестового студента #2" + studentRepository.save(
                    new Student(
                            "Сэмми Смит Карлсон",
                            "298302, Республика Крым, г. Керчь, ул. Ленинская, 8а",
                            "+7(978)222-33-44",
                            1000123)));
            log.info("Загружаем тестового студента #3" + studentRepository.save(
                    new Student(
                            "Гарри Дэйл Харрисон",
                            "298302, Республика Крым, г. Керчь, ул. Оборонная, 127a / 1",
                            "+7(978)333-44-55",
                            1000124)));
            log.info("Загружаем тестового студента #4" + studentRepository.save(
                    new Student(
                            "Линдси Джэфферсон Гомез",
                            "298302, Республика Крым, г. Керчь, ул. Суздальская, 200 / 112",
                            "+7(978)555-66-77",
                            1000125)));
            log.info("Загружаем тестового студента #5" + studentRepository.save(
                    new Student(
                            "Рэйчел Джэксон Паркер",
                            "298302, Республика Крым, г. Керчь, Флотский переулок, 1",
                            "+7(978)666-77-88",
                            1000126)));
            log.info("Загружаем тестовый курс #1" + courseRepository.save(
                    new Course("Physics for 9-th grade", 100292910, 25000f)));
            log.info("Загружаем тестовый курс #2" + courseRepository.save(
                    new Course("Chemistry for 9-th grade", 202554110, 22000f)));
            log.info("Загружаем тестовый курс #3" + courseRepository.save(
                    new Course("Java for beginners", 300235650, 30000f)));

            log.info("Загружаем тестовый экспортный фаайл" + exchangeFileRepository.save(
                    new ExchangeFile("D:\\export_json_31-03-2019_20_12_58.json", Long.valueOf(1846))));

        };
    }

}

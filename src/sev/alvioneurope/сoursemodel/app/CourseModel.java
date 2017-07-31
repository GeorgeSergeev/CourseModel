package sev.alvioneurope.сoursemodel.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sev.alvioneurope.сoursemodel.impl.Course;
import sev.alvioneurope.сoursemodel.impl.DAO;
import sev.alvioneurope.сoursemodel.impl.Professor;
import sev.alvioneurope.сoursemodel.impl.Student;
import sev.alvioneurope.сoursemodel.impl.Study;

public class CourseModel {
	static Logger lg = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
	DAO dao;
	Study study;
	Student student = null;
	List<Course> courseL = new ArrayList<>();
	List<Student> studentL = new ArrayList<>();
	List<Professor> professorsL = new ArrayList<>();


	//Выбор варианта тестирования
	int modeTest = 2;
	

	switch (modeTest) {
	case 1:
		dao = DAO.getDAO(true);
		break;
	case 4:
	case 5:
	case 6:
	default:
		dao = DAO.getDAO(false);
		break;
	}
	
	study = dao.readStudy();



	switch (modeTest) {
	case 1:
	//Создание учебной конфигурации с последующим сохранением:
	//Создание студентов в 3 вариантах:
	//	- 1 вариант создания (цепочкой)
	student = dao.createStudent(Student.getNew()).setBookNum(1).setName("Игорь").setAddress("Севастополь")
												  .setEmail("igor@sev.ru").setPhone("+7934-823-53-01")
												  .build();
	studentL.add(student);
	//	- 2 вариант создания (конструктором, с последующей регистрацией в БД)
	student = Student.getNew(2, "Генадий", "Керчь", "+7912-873-99-23", "gen@sev.ru");
	student = dao.createStudent(student).build(); 
	studentL.add(student);
	//	- 3 вариант создания (смешанный)
	student = Student.getNew();
	student.setName("Юра");
	student.setAddress("Ялта");
	student = dao.createStudent(student).setPhone("+7845-028-13-85").setEmail("jura@sev.ru").setBookNum(3).build();
	studentL.add(student);
	
	//Создание курсов
	courseL.add(dao.createCourse(Course.getNew()).setNumber(1).setName("Литературы").setCost(1000).build());
	courseL.add(dao.createCourse(Course.getNew()).setNumber(2).setName("Культуры").setCost(3452.90f).build());
	courseL.add(dao.createCourse(Course.getNew()).setNumber(3).setName("Здоровья").setCost(23072).build());

	//Создание профессоров
	professorsL.add(dao.createProfessor(Professor.getNew()).setName("Иваныч").setAddress("Париж").setPhone("+5162-623-70-44").setPayment(999.99f).build());
	professorsL.add(dao.createProfessor(Professor.getNew()).setName("Петрович").setAddress("Лондон").setPhone("+1623-684-32-94").setPayment(4652898).build());
	professorsL.add(dao.createProfessor(Professor.getNew()).setName("Макарыч").setAddress("Рим").setPhone("+5534-288-10-09").setPayment(250).build());

	//Запись студентов на курсы
	courseL.get(0).actAddToCourse(studentL.get(0));
	courseL.get(1).actAddToCourse(studentL.get(1));
	courseL.get(1).actAddToCourse(studentL.get(2));
	courseL.get(2).actAddToCourse(studentL.get(0));
	courseL.get(2).actAddToCourse(studentL.get(1));
	courseL.get(2).actAddToCourse(studentL.get(2));

	//Добавление профессоров на курсы
	courseL.get(1).addProfessor(professorsL.get(1));
	courseL.get(1).addProfessor(professorsL.get(2));
	courseL.get(2).addProfessor(professorsL.get(0));
	courseL.get(2).addProfessor(professorsL.get(1));
	courseL.get(2).addProfessor(professorsL.get(2));
	courseL.get(2).addProfessor(professorsL.get(2));

	//Выставление оценок
	for (int score: IntStream.of(3,4,5,5).toArray()) 			study.setScore(studentL.get(0), courseL.get(0), score);
	for (int score: IntStream.of(2,3,4,2).toArray()) 			study.setScore(studentL.get(1), courseL.get(1), score);
	for (int score: IntStream.of(4,4,4,4).toArray()) 			study.setScore(studentL.get(2), courseL.get(1), score);
	for (int score: IntStream.of(4,2,2,4,5,3,4,3,5).toArray())	study.setScore(studentL.get(0), courseL.get(2), score);
	for (int score: IntStream.of(3,5).toArray())				study.setScore(studentL.get(1), courseL.get(2), score);
	for (int score: IntStream.of().toArray())					study.setScore(studentL.get(2), courseL.get(2), score);
	
	//Завершение курсов (выпуск)
	study.setScoreFinal(studentL.get(2), courseL.get(1), 5);
	study.setScoreFinal(studentL.get(1), courseL.get(2), 3);
	
	//Удаление студентов (досрочное прерывание курсов)
	courseL.get(2).actRemoveFromCourse(studentL.get(2));
	courseL.get(1).actRemoveFromCourse(studentL.get(1));
	
	//Сохранения
	//	- учебного процесса (Учёбы)
	dao.updateStudy();
	//	- всех курсов
	courseL.forEach(с -> DAO.getDAO().updateCourse(с));
	//	- всех студентов
	studentL.forEach(с -> DAO.getDAO().updateStudent(с));
	//	- всех профессоров
	professorsL.forEach(с -> DAO.getDAO().updateProfessor(с));
	
	lg.info("\n{}", study);
		break;
				
	case 2:		//Списочные выводы
		courseL = reportAll(dao, study);
		
		break;
	case 3:
		//Восстановление  студентов
		studentL = dao.readStudentAll();
		courseL = dao.readCourseAll();
		courseL.get(1).actAddToCourse(studentL.get(2));	//после выпуска на тотже
		courseL.get(1).actAddToCourse(studentL.get(1));	//после досрочного исключения на тотже
		reportAll(dao, study);
				
		break;
	case 4:
		//Изменение студента
		student = dao.readStudent(1);
		lg.info("\n{}", student);
		student.setAddress("Балаклава");
		dao.updateStudent(student);
		lg.info("\n{}", student);
		student = dao.readStudent(1);
		lg.info("\n{}", student);
		
		break;
	case 5:
		//Удаление студента, который на курсах
		student = dao.readStudent(1);
		lg.info("\n{}", student);
		dao.deleteStudent(student);
		courseL = dao.readCourseAll();
		courseL.get(0).actRemoveFromCourse(student);
		courseL.get(1).actRemoveFromCourse(student);
		courseL.get(2).actRemoveFromCourse(student);
		lg.info("\n{}", study.getListStudentAll());
		dao.deleteStudent(student);
		lg.info("\n{}", study.getListStudentAll());
		//reportAll(dao, study);

		break;
	case 6:
		
		break;
	default:
		break;
	}

	System.exit(0);
	}


	private static List<Course> reportAll(DAO dao, Study study) {
		List<Course> courseL;
		List<Student> studentL;
		lg.info("\n------------------------------ Вся учёба ------------------------\n");
		lg.info("\n{}", study);
		lg.info("\n{}", study.getListStudentAll());
		lg.info("\n{}", study.getListSessionAll());
		lg.info("\n{}", study.getListCourseAll());
		studentL = dao.readStudentAll();
		lg.info("\n------------------------------ Все студенты ------------------------\n{}", studentL);
		lg.info("\n------------------------------ Списочные выводы по студентам ------------------------\n");
		studentL.forEach(s -> lg.info("\n{}", s.getListPassing()));
		studentL.forEach(s -> lg.info("\n{}", s.getListFinished()));
		courseL = dao.readCourseAll();
		lg.info("\n------------------------------ Все курсы ------------------------\n{}", courseL);
		lg.info("\n------------------------------ Списочные выводы по курсам ------------------------\n");
		courseL.forEach(c -> lg.info("\n{}", c.getListSessions()));
		courseL.forEach(c -> lg.info("\n{}", c.getListProfessor()));
		return courseL;
	}

}

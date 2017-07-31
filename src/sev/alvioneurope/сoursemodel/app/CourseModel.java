package sev.alvioneurope.�oursemodel.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sev.alvioneurope.�oursemodel.impl.Course;
import sev.alvioneurope.�oursemodel.impl.DAO;
import sev.alvioneurope.�oursemodel.impl.Professor;
import sev.alvioneurope.�oursemodel.impl.Student;
import sev.alvioneurope.�oursemodel.impl.Study;

public class CourseModel {
	static Logger lg = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
	DAO dao;
	Study study;
	Student student = null;
	List<Course> courseL = new ArrayList<>();
	List<Student> studentL = new ArrayList<>();
	List<Professor> professorsL = new ArrayList<>();


	//����� �������� ������������
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
	//�������� ������� ������������ � ����������� �����������:
	//�������� ��������� � 3 ���������:
	//	- 1 ������� �������� (��������)
	student = dao.createStudent(Student.getNew()).setBookNum(1).setName("�����").setAddress("�����������")
												  .setEmail("igor@sev.ru").setPhone("+7934-823-53-01")
												  .build();
	studentL.add(student);
	//	- 2 ������� �������� (�������������, � ����������� ������������ � ��)
	student = Student.getNew(2, "�������", "�����", "+7912-873-99-23", "gen@sev.ru");
	student = dao.createStudent(student).build(); 
	studentL.add(student);
	//	- 3 ������� �������� (���������)
	student = Student.getNew();
	student.setName("���");
	student.setAddress("����");
	student = dao.createStudent(student).setPhone("+7845-028-13-85").setEmail("jura@sev.ru").setBookNum(3).build();
	studentL.add(student);
	
	//�������� ������
	courseL.add(dao.createCourse(Course.getNew()).setNumber(1).setName("����������").setCost(1000).build());
	courseL.add(dao.createCourse(Course.getNew()).setNumber(2).setName("��������").setCost(3452.90f).build());
	courseL.add(dao.createCourse(Course.getNew()).setNumber(3).setName("��������").setCost(23072).build());

	//�������� �����������
	professorsL.add(dao.createProfessor(Professor.getNew()).setName("������").setAddress("�����").setPhone("+5162-623-70-44").setPayment(999.99f).build());
	professorsL.add(dao.createProfessor(Professor.getNew()).setName("��������").setAddress("������").setPhone("+1623-684-32-94").setPayment(4652898).build());
	professorsL.add(dao.createProfessor(Professor.getNew()).setName("�������").setAddress("���").setPhone("+5534-288-10-09").setPayment(250).build());

	//������ ��������� �� �����
	courseL.get(0).actAddToCourse(studentL.get(0));
	courseL.get(1).actAddToCourse(studentL.get(1));
	courseL.get(1).actAddToCourse(studentL.get(2));
	courseL.get(2).actAddToCourse(studentL.get(0));
	courseL.get(2).actAddToCourse(studentL.get(1));
	courseL.get(2).actAddToCourse(studentL.get(2));

	//���������� ����������� �� �����
	courseL.get(1).addProfessor(professorsL.get(1));
	courseL.get(1).addProfessor(professorsL.get(2));
	courseL.get(2).addProfessor(professorsL.get(0));
	courseL.get(2).addProfessor(professorsL.get(1));
	courseL.get(2).addProfessor(professorsL.get(2));
	courseL.get(2).addProfessor(professorsL.get(2));

	//����������� ������
	for (int score: IntStream.of(3,4,5,5).toArray()) 			study.setScore(studentL.get(0), courseL.get(0), score);
	for (int score: IntStream.of(2,3,4,2).toArray()) 			study.setScore(studentL.get(1), courseL.get(1), score);
	for (int score: IntStream.of(4,4,4,4).toArray()) 			study.setScore(studentL.get(2), courseL.get(1), score);
	for (int score: IntStream.of(4,2,2,4,5,3,4,3,5).toArray())	study.setScore(studentL.get(0), courseL.get(2), score);
	for (int score: IntStream.of(3,5).toArray())				study.setScore(studentL.get(1), courseL.get(2), score);
	for (int score: IntStream.of().toArray())					study.setScore(studentL.get(2), courseL.get(2), score);
	
	//���������� ������ (������)
	study.setScoreFinal(studentL.get(2), courseL.get(1), 5);
	study.setScoreFinal(studentL.get(1), courseL.get(2), 3);
	
	//�������� ��������� (��������� ���������� ������)
	courseL.get(2).actRemoveFromCourse(studentL.get(2));
	courseL.get(1).actRemoveFromCourse(studentL.get(1));
	
	//����������
	//	- �������� �������� (�����)
	dao.updateStudy();
	//	- ���� ������
	courseL.forEach(� -> DAO.getDAO().updateCourse(�));
	//	- ���� ���������
	studentL.forEach(� -> DAO.getDAO().updateStudent(�));
	//	- ���� �����������
	professorsL.forEach(� -> DAO.getDAO().updateProfessor(�));
	
	lg.info("\n{}", study);
		break;
				
	case 2:		//��������� ������
		courseL = reportAll(dao, study);
		
		break;
	case 3:
		//��������������  ���������
		studentL = dao.readStudentAll();
		courseL = dao.readCourseAll();
		courseL.get(1).actAddToCourse(studentL.get(2));	//����� ������� �� �����
		courseL.get(1).actAddToCourse(studentL.get(1));	//����� ���������� ���������� �� �����
		reportAll(dao, study);
				
		break;
	case 4:
		//��������� ��������
		student = dao.readStudent(1);
		lg.info("\n{}", student);
		student.setAddress("���������");
		dao.updateStudent(student);
		lg.info("\n{}", student);
		student = dao.readStudent(1);
		lg.info("\n{}", student);
		
		break;
	case 5:
		//�������� ��������, ������� �� ������
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
		lg.info("\n------------------------------ ��� ����� ------------------------\n");
		lg.info("\n{}", study);
		lg.info("\n{}", study.getListStudentAll());
		lg.info("\n{}", study.getListSessionAll());
		lg.info("\n{}", study.getListCourseAll());
		studentL = dao.readStudentAll();
		lg.info("\n------------------------------ ��� �������� ------------------------\n{}", studentL);
		lg.info("\n------------------------------ ��������� ������ �� ��������� ------------------------\n");
		studentL.forEach(s -> lg.info("\n{}", s.getListPassing()));
		studentL.forEach(s -> lg.info("\n{}", s.getListFinished()));
		courseL = dao.readCourseAll();
		lg.info("\n------------------------------ ��� ����� ------------------------\n{}", courseL);
		lg.info("\n------------------------------ ��������� ������ �� ������ ------------------------\n");
		courseL.forEach(c -> lg.info("\n{}", c.getListSessions()));
		courseL.forEach(c -> lg.info("\n{}", c.getListProfessor()));
		return courseL;
	}

}

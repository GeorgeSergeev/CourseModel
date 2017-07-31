package sev.alvioneurope.сoursemodel.impl;

import static sev.alvioneurope.сoursemodel.impl.StudyImpl.SessionStatus.PASSAGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Обьект: "Учёба". Хранилище сессий
class StudyImpl implements Study {
	private transient Logger lg = LogManager.getLogger();
	private static StudyImpl studyImpl;
	private transient DAOservice daoService;
	//Ассоциативная таблица
	HashMap<Integer, List<Integer>> joinStudentToPool;	//внешний ключ по студентам
	HashMap<Integer, List<Integer>> joinCourseToPool;	//внешний ключ по курсам
	HashMap<Integer, SessionImpl> mapPrim;				//основной ключ по сессиям

//------------------------ Constructors ------------------------------------
	private StudyImpl() {
		joinStudentToPool = new HashMap<>();
		joinCourseToPool = new HashMap<>();
		mapPrim = new HashMap<>();
		studyImpl = this;
		daoService = (DAOservice) DAOimpl.getDAO();
	}

	public static StudyImpl getStudy() {
		if(studyImpl == null) studyImpl = new StudyImpl();
		return studyImpl;
	}

//------------------------ Methods ------------------------------------
	public void setScore(Student student, Course course, int score) {	//Выставление текщей оценки
		int keyPrimary;
		keyPrimary = DAOkeys.getKeyPrimary(course.getNumber(), student.getBookNum());
		if(mapPrim.containsKey(keyPrimary)) {	//Такой студент на этом курсе есть
			mapPrim.get(keyPrimary).setScoreNext(score);
		}else{
			lg.debug("Failure set score. There is no the student on this course");
		}
	}

	public void setScoreFinal(Student student, Course course,  int score) {	 //Выставление финальной оценки
		Session session;
		int keyPrimary;
		keyPrimary = DAOkeys.getKeyPrimary(course.getNumber(), student.getBookNum());
		if(mapPrim.containsKey(keyPrimary)) {	//Такой студент числиться
			session = mapPrim.get(keyPrimary);
			session.setScoreFinal(score);
//			session.setStatus(GRADUATE);
			//Notify курс, как администрацию о завершении студентом сессии для соответствующих оргвыводов
			((CourseImpl)course)._notifyOfGraduate(student);
		}else{
			lg.debug("Failure set final score. There is no the student on this course");
		}
	}

	@SuppressWarnings("unchecked")
	<T> List<T> getListCourse(Student student) {	//Предоставление списков курсов по студентам
		List<T> result;
        result = (List<T>) joinStudentToPool.getOrDefault(student.hashCode(), Collections.EMPTY_LIST) 
        						  .stream()
        						  .map(a -> mapPrim.get(a))
	                			  .collect(Collectors.toList());
	    return result;
	}

	@SuppressWarnings("unchecked")
	<T> List<T>  getListStudent(Course course) {		//Предоставление списков студентов по курсам
		List<T> result;
        result = (List<T>) joinCourseToPool.getOrDefault(course.hashCode(), Collections.EMPTY_LIST)
        						  .stream()
        						  .map(a -> mapPrim.get(a))
	                			  .collect(Collectors.toList());
	    return result;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T>  getListSessionAll() {				//Предоставление списков всех сессий
		List<T> result;
        result = (List<T>) mapPrim.values().stream().collect(Collectors.toList());
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T>  getListStudentAll() {				//Предоставление списка всех студентов на Учёбе
		List<T> result = null;
        result = (List<T>) joinStudentToPool.keySet().stream()
        		.map(a -> daoService._readStudentOnKey(a))
	            .collect(Collectors.toList());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T>  getListCourseAll() {				//Предоставление списка всех курсов на Учёбе
		List<T> result = null;
		result = (List<T>) joinCourseToPool.keySet().stream()
				.map(a -> daoService._readCourseOnKey(a))
				.collect(Collectors.toList());
		return result;
	}
	

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("\n\nСведенья обо всех учебных сессиях:\n");
		mapPrim.values().forEach(ss -> {
				Course currentC = ss.getCourse();
				int quanC = joinStudentToPool.get(ss.getStudent().hashCode()).size() - 1;
				result.append(ss);	//Вывод студента
				if(quanC > 0)
					result.append("\tПроходит дополнительные курсы ( + " + quanC + " кур.):\n");
					joinStudentToPool.get(ss.getStudent().hashCode()).forEach(ssf -> {
							Course c = mapPrim.get(ssf).getCourse();
							int quanP = c.getListProfessor().size();
							if(currentC != c) {
								result.append(String.format("\t\t- название: %s;  %s\n",
								c.getName(), (quanP == 0)?"самостоятельно":(((quanP < 2)?"профессор ":"один из " + quanP + 
								" профессоров: ") + c.getListProfessor().get(0).getName() + " из " + c.getListProfessor().get(0).getAddress())));
							}
					});
		});
		result.append("\n\nПеречень курсов на Учёбе:\n");
		if(joinCourseToPool.isEmpty())	result.append("\tКурсы отсутствуют, студентов нет\n");
		else 	joinCourseToPool.forEach((cf, ssL) -> {	//Внешний контур по схеме: СС -> ...
					result.append(daoService._readCourseOnKey(cf));	//Вывод курса
					result.append("\tНа курсе обучаются: \n");
					ssL.forEach(ssf -> {
							result.append(((StudentImpl)mapPrim.get(ssf).getStudent()).toStringAsPart());
					});
		});
		result.append("\n\nСписок всех студетов на Учёбе:\n");
		if(joinStudentToPool.isEmpty())	result.append("\tСтуденты отсутствуют, никакие курсы не проводятся\n");
		else 	joinStudentToPool.forEach((sf, ssL) -> {	//Внешний контур по схеме: СС -> ...
			result.append(daoService._readStudentOnKey(sf));	//Вывод студента
			result.append("\tПроходит обучение на следующих курсах:\n");
			ssL.forEach(ssf -> {
				result.append(((CourseImpl)mapPrim.get(ssf).getCourse()).toStringAsPart());
			});
		});
		
		return result.toString();
	}




//------------------------ Nested classes ------------------------------------

	//Интерфейс обьекта "Сессия". Цикл занятий на отдельном курсе.
	public interface Session extends DAOkeys {
		Student getStudent();
		Course getCourse();

		float getScoreAverage();
		int   getScoreFinal();

		void setScoreNext(int score);
		void setScoreFinal(int score);
		List<Integer> getListScore();
		
		SessionStatus getStatus();
		void setStatus(SessionStatus status);
	}


	//Реализация обьекта "Сессия"
	public static class SessionImpl implements Session { 
		//доменные
		private List<Integer> scoreCurrentList;
		private float scoreAverage;
		private int   scoreFinal;
		//служебные
		private transient DAOservice dao;
		private transient Student student;	//Отложенная init
		private transient Course course;	//Отложенная init
		private int _gradeBookNum;
		private int _numCourse;
		private boolean wasUpdate;
		private SessionStatus status;

//------------------------ Constructors ------------------------------------
		SessionImpl(int gradeBookNum, int numCourse) {
			this();
			_gradeBookNum = gradeBookNum;
			_numCourse = numCourse;
			scoreCurrentList = new ArrayList<Integer>();
			scoreFinal = 0;
			scoreAverage = 0;
			wasUpdate = false;
			status = PASSAGE;
		}

		SessionImpl() {
			dao = (DAOservice) DAOimpl.getDAO();
		}
		
//------------------------ Methods ------------------------------------
		//Механизм иключения замкнутых ссылков. Отложенная init.
		public Student getStudent() {
			student = (student==null)?dao.readStudent(_gradeBookNum):student;
			return student;
		}

		//Механизм иключения замкнутых ссылков. Отложенная init.
		public Course getCourse() {	
			course = (course==null)?dao.readCourse(_numCourse):course;
			return course;
		}

		@Override
		public void setScoreNext(int score) {
			scoreCurrentList.add(score);
			wasUpdate = true;
		}
		
		@Override
		public void setScoreFinal(int score) {
			scoreFinal = score;
		}

		@Override
		public int getScoreFinal() {
			return scoreFinal;
		}

		@Override
		public  List<Integer> getListScore() {	//Получить весь список текщих оценок
			return scoreCurrentList;
		}

		@Override
		public float getScoreAverage() {	//Расчёт текщего среднего бала
			float result;
			if(wasUpdate) {					//Повторный расчёт по необходимости
				OptionalDouble average;
		        average =	scoreCurrentList.stream()
		                					.mapToDouble(a -> a)
		                					.average();
		        result = (average.isPresent()) ? (float) average.getAsDouble() : 0;
		        scoreAverage = result;
		        wasUpdate = false;
			}else {
				result = scoreAverage; 
			}
	        return result;
		}

		@Override
		public SessionStatus getStatus() {
			return status;
		}

		@Override
		public void setStatus(SessionStatus status) {
			this.status = status;
		}


		@Override
		public int hashCode() {
			return hashCodeSession(_numCourse, _gradeBookNum);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (this == obj)
				return true;
			if (!(obj instanceof SessionImpl))
				return false;
			SessionImpl other = (SessionImpl) obj;
			if (_numCourse != other._numCourse || _gradeBookNum != other._gradeBookNum)	
				return false;
			return true;
		}

		@Override
		public String toString() {
			getCourse();
			List<ProfessorImpl> pl = course.getListProfessor();
			String prof = (pl.size() == 0)?", самостоятельно": String.format((pl.size() < 2)?", профессор %2$s из %3$s":
											", один из %d профессоров: %s из %s", pl.size(), pl.get(0).getName(), pl.get(0).getAddress());
			StringBuilder result = new StringBuilder("\nСессия студента %1$s на курсе \"%2$s\"").append(prof).append(":\n\tстатус: %3$s\n");
			if(status != SessionStatus.BREAK) { result
						 					  .append("\tсредний бал: %4$.2f по оценкам: %6$s")
						 					  .append((status == SessionStatus.GRADUATE)?
						 					 		 "\n\tфинальная отметка: %5$d\n":"\n");
			}
			return String.format(result.toString(), getStudent().getName(), getCourse().getName(),
								 status, getScoreAverage(), getScoreFinal(), scoreCurrentList);
		}
	}

	//-----------------------------------------------------------------------------------------------------
	public enum SessionStatus {GRADUATE("курс закончен"), PASSAGE("проходит обучение"), BREAK("курс прерван");
		private String msg;
		SessionStatus(String msg) { this.msg = msg; }

		@Override
		public String toString() {
			return msg;
		}
	}; 
	
}

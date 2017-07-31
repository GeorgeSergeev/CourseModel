package sev.alvioneurope.�oursemodel.impl;

import static sev.alvioneurope.�oursemodel.impl.StudyImpl.SessionStatus.PASSAGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//������: "�����". ��������� ������
class StudyImpl implements Study {
	private transient Logger lg = LogManager.getLogger();
	private static StudyImpl studyImpl;
	private transient DAOservice daoService;
	//������������� �������
	HashMap<Integer, List<Integer>> joinStudentToPool;	//������� ���� �� ���������
	HashMap<Integer, List<Integer>> joinCourseToPool;	//������� ���� �� ������
	HashMap<Integer, SessionImpl> mapPrim;				//�������� ���� �� �������

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
	public void setScore(Student student, Course course, int score) {	//����������� ������ ������
		int keyPrimary;
		keyPrimary = DAOkeys.getKeyPrimary(course.getNumber(), student.getBookNum());
		if(mapPrim.containsKey(keyPrimary)) {	//����� ������� �� ���� ����� ����
			mapPrim.get(keyPrimary).setScoreNext(score);
		}else{
			lg.debug("Failure set score. There is no the student on this course");
		}
	}

	public void setScoreFinal(Student student, Course course,  int score) {	 //����������� ��������� ������
		Session session;
		int keyPrimary;
		keyPrimary = DAOkeys.getKeyPrimary(course.getNumber(), student.getBookNum());
		if(mapPrim.containsKey(keyPrimary)) {	//����� ������� ���������
			session = mapPrim.get(keyPrimary);
			session.setScoreFinal(score);
//			session.setStatus(GRADUATE);
			//Notify ����, ��� ������������� � ���������� ��������� ������ ��� ��������������� ����������
			((CourseImpl)course)._notifyOfGraduate(student);
		}else{
			lg.debug("Failure set final score. There is no the student on this course");
		}
	}

	@SuppressWarnings("unchecked")
	<T> List<T> getListCourse(Student student) {	//�������������� ������� ������ �� ���������
		List<T> result;
        result = (List<T>) joinStudentToPool.getOrDefault(student.hashCode(), Collections.EMPTY_LIST) 
        						  .stream()
        						  .map(a -> mapPrim.get(a))
	                			  .collect(Collectors.toList());
	    return result;
	}

	@SuppressWarnings("unchecked")
	<T> List<T>  getListStudent(Course course) {		//�������������� ������� ��������� �� ������
		List<T> result;
        result = (List<T>) joinCourseToPool.getOrDefault(course.hashCode(), Collections.EMPTY_LIST)
        						  .stream()
        						  .map(a -> mapPrim.get(a))
	                			  .collect(Collectors.toList());
	    return result;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T>  getListSessionAll() {				//�������������� ������� ���� ������
		List<T> result;
        result = (List<T>) mapPrim.values().stream().collect(Collectors.toList());
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T>  getListStudentAll() {				//�������������� ������ ���� ��������� �� �����
		List<T> result = null;
        result = (List<T>) joinStudentToPool.keySet().stream()
        		.map(a -> daoService._readStudentOnKey(a))
	            .collect(Collectors.toList());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T>  getListCourseAll() {				//�������������� ������ ���� ������ �� �����
		List<T> result = null;
		result = (List<T>) joinCourseToPool.keySet().stream()
				.map(a -> daoService._readCourseOnKey(a))
				.collect(Collectors.toList());
		return result;
	}
	

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("\n\n�������� ��� ���� ������� �������:\n");
		mapPrim.values().forEach(ss -> {
				Course currentC = ss.getCourse();
				int quanC = joinStudentToPool.get(ss.getStudent().hashCode()).size() - 1;
				result.append(ss);	//����� ��������
				if(quanC > 0)
					result.append("\t�������� �������������� ����� ( + " + quanC + " ���.):\n");
					joinStudentToPool.get(ss.getStudent().hashCode()).forEach(ssf -> {
							Course c = mapPrim.get(ssf).getCourse();
							int quanP = c.getListProfessor().size();
							if(currentC != c) {
								result.append(String.format("\t\t- ��������: %s;  %s\n",
								c.getName(), (quanP == 0)?"��������������":(((quanP < 2)?"��������� ":"���� �� " + quanP + 
								" �����������: ") + c.getListProfessor().get(0).getName() + " �� " + c.getListProfessor().get(0).getAddress())));
							}
					});
		});
		result.append("\n\n�������� ������ �� �����:\n");
		if(joinCourseToPool.isEmpty())	result.append("\t����� �����������, ��������� ���\n");
		else 	joinCourseToPool.forEach((cf, ssL) -> {	//������� ������ �� �����: �� -> ...
					result.append(daoService._readCourseOnKey(cf));	//����� �����
					result.append("\t�� ����� ���������: \n");
					ssL.forEach(ssf -> {
							result.append(((StudentImpl)mapPrim.get(ssf).getStudent()).toStringAsPart());
					});
		});
		result.append("\n\n������ ���� �������� �� �����:\n");
		if(joinStudentToPool.isEmpty())	result.append("\t�������� �����������, ������� ����� �� ����������\n");
		else 	joinStudentToPool.forEach((sf, ssL) -> {	//������� ������ �� �����: �� -> ...
			result.append(daoService._readStudentOnKey(sf));	//����� ��������
			result.append("\t�������� �������� �� ��������� ������:\n");
			ssL.forEach(ssf -> {
				result.append(((CourseImpl)mapPrim.get(ssf).getCourse()).toStringAsPart());
			});
		});
		
		return result.toString();
	}




//------------------------ Nested classes ------------------------------------

	//��������� ������� "������". ���� ������� �� ��������� �����.
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


	//���������� ������� "������"
	public static class SessionImpl implements Session { 
		//��������
		private List<Integer> scoreCurrentList;
		private float scoreAverage;
		private int   scoreFinal;
		//���������
		private transient DAOservice dao;
		private transient Student student;	//���������� init
		private transient Course course;	//���������� init
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
		//�������� ��������� ��������� �������. ���������� init.
		public Student getStudent() {
			student = (student==null)?dao.readStudent(_gradeBookNum):student;
			return student;
		}

		//�������� ��������� ��������� �������. ���������� init.
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
		public  List<Integer> getListScore() {	//�������� ���� ������ ������ ������
			return scoreCurrentList;
		}

		@Override
		public float getScoreAverage() {	//������ ������� �������� ����
			float result;
			if(wasUpdate) {					//��������� ������ �� �������������
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
			String prof = (pl.size() == 0)?", ��������������": String.format((pl.size() < 2)?", ��������� %2$s �� %3$s":
											", ���� �� %d �����������: %s �� %s", pl.size(), pl.get(0).getName(), pl.get(0).getAddress());
			StringBuilder result = new StringBuilder("\n������ �������� %1$s �� ����� \"%2$s\"").append(prof).append(":\n\t������: %3$s\n");
			if(status != SessionStatus.BREAK) { result
						 					  .append("\t������� ���: %4$.2f �� �������: %6$s")
						 					  .append((status == SessionStatus.GRADUATE)?
						 					 		 "\n\t��������� �������: %5$d\n":"\n");
			}
			return String.format(result.toString(), getStudent().getName(), getCourse().getName(),
								 status, getScoreAverage(), getScoreFinal(), scoreCurrentList);
		}
	}

	//-----------------------------------------------------------------------------------------------------
	public enum SessionStatus {GRADUATE("���� ��������"), PASSAGE("�������� ��������"), BREAK("���� �������");
		private String msg;
		SessionStatus(String msg) { this.msg = msg; }

		@Override
		public String toString() {
			return msg;
		}
	}; 
	
}

package sev.alvioneurope.�oursemodel.impl;

import static sev.alvioneurope.�oursemodel.impl.StudyImpl.SessionStatus.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sev.alvioneurope.�oursemodel.impl.StudyImpl.Session;
import sev.alvioneurope.�oursemodel.impl.StudyImpl.SessionImpl;

//��.������: "����"
class CourseImpl implements Course, AfterJsonDeserial{
	private transient Logger lg = LogManager.getLogger();
	private static final Random id = new Random();	//Default case
	//��������
	private String name;
	private int    number;
	private float  cost;
	private List<ProfessorImpl>	 professorsList;
	//���������
//	private transient DAOimpl dao;
	private transient StudyImpl studyImpl;
	boolean emptyCourse;
	//�������� ����� � �������� ��������
	private transient HashMap<Integer, SessionImpl> mapPrim;	//�������� ����������: pool ���� ������ �� ��������� ����� 
	private transient HashMap<Integer, List<Integer>> jointS;  		//������� ���� ���������� �� ���������
	private transient HashMap<Integer, List<Integer>> jointC;  		//������� ���� ���������� �� ������
	private transient List<Integer> listSc;					   		//������� ������ ����� (����� ��� jointC)


//------------------------ Constructors ------------------------------------
    CourseImpl(){
    	studyImpl = StudyImpl.getStudy();
//		dao = (DAOimpl) DAOimpl.getDAO();
        number = id.nextInt();
        professorsList = new ArrayList<>();
        emptyCourse = true;
    };

    CourseImpl(int number, String name, float cost) {
    	this();
        this.name = name;
        this.cost = cost;
        this.number = number;
        _bindingToStudy();	//�������� � �����
    }

//------------------------ Methods ------------------------------------
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public float getCost() {
		return cost;
	}

	@Override
	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void setNumber(int number) {
		this.number = number;
		_bindingToStudy();
	}

	@Override
	public List<Session> getListSessions() {	//��������� ������ ���������/������ ����� �����
        return   studyImpl.getListStudent(this); 
	}

	@Override
	public List<ProfessorImpl> getListProfessor() {	//��������� ������ ����������� ����� �����
		return professorsList;
	}

	@Override
	public void addProfessor(Professor prof) {
		if(!professorsList.contains(prof)) professorsList.add((ProfessorImpl) prof);
		((ProfessorImpl)prof).meterFree++;		
	}
	
	@Override
	public void delProfessor(Professor prof) {
		if(professorsList.contains(prof)) {
			professorsList.add((ProfessorImpl) prof);
			((ProfessorImpl)prof).meterFree--;
		}
	}
	
	//�������� ��������.����� �������: �������� � ������������
	@Override
	public boolean actRemoveFromCourse(Student student) {
		return _remove(student, true);
	}

	//�������� ��������.����� ������� �������� (�������������� ������ ����: �� ������� ��������) 
	@Override
	public boolean actAddToCourse(Student student) {
		boolean result = false;
		Session newSession;				 //������ ��� ������������ ��������
		int keyPrim;							 //�������� ���� ���������� ��� ������������ ��������: ���� � ��� ����� ������
		int keyS;								 //���� �� ��������
		int keyC;						 		 //���� �� �����
		List<Integer> listSs;					 //������� ������ �������� (����� ��� jointS)
		
//		if(mapPrim == null) _bindingToStudy();	//��������. ����������� � ���������, �� ������� �������������
		keyPrim = DAOkeys.getKeyPrimary(number, student.getBookNum()); 	//������ ��������� �����(���) ������� ����� ������ ��� ������������ ��������

//		if(student.getListFinished().stream().anyMatch(a -> { lg.info("!!!!!!{}\n{}", a.getCourse(), this); return a.getCourse().equals(this);})) {
//		if(student.getListFinished().stream().anyMatch(a -> { lg.info("!!!!!!{}\n", a.getCourse().equals(this)); return a.getCourse().equals(this);})) {
		if(student.getListFinished().stream().anyMatch(a -> a.getCourse().equals(this))) {
			lg.debug("Failure. The student on this course have been studied earlier");
		}else if(!mapPrim.containsKey(keyPrim)) {	 //�������� �� ����� ����� ���. ��������
			keyS = student.hashCode();		//���������� �����(���) �� ������������ ��������
			keyC = hashCode();				//���������� �����(���) �� ����� �����
			listSs = jointS.get(keyS);
			_debugSynch(keyPrim, keyC, keyS, false);
//			newSession = dao.readSession(this, student);	//������� ������������ ������ �� �� ��� �������� �����. ��� ���� ��������� � ����� ���������: keyPrim
			newSession = new SessionImpl(student.getBookNum(), getNumber());	//������� ������������ ������ �� �� ��� �������� �����. ��� ���� ��������� � ����� ���������: keyPrim
			if(listSs == null) {		//��� ����� �������, ��� �������� �� ���� ����� ��� ����� ������ ���� 
				jointS.compute(keyS, _defaultList(keyPrim));	//�������� ���������� ������ �������� � ��� ������ ������
			} else {					//������� ��� ���� �� ������ ������, �� �� �� ����
				listSs.add(keyPrim);	//���������� ���������� �� ������� ��������. ���������� ����� ������ � ��� ��� ������������
			}
			if(listSc == null) {		//��� ������ ������� ��� ����� �����
				throw new IllegalStateException("Course is not tied to StudyImpl. Name of course: " + name);	//Debug
//				jointC.compute(keyC, _defaultList(keyPrim));	//�������� ���������� �� ������� ����� �� ����� ������ ���������
			}
//			 else {					//�� ����� ��� ���� ������ ��������
				listSc.add(keyPrim);	//���������� ���������� �� ������� �����. ���������� ����� ������ � ����� �����
//			}
			mapPrim.put(keyPrim, (SessionImpl) newSession);			//���������� ���� ����������: ���������� ��������� ����� ����������
			_debugSynch(keyPrim, keyC, keyS, true);
			((StudentImpl)student)._notifyOfAdd();	//Notify �������� �� ������� �����
			emptyCourse = false;				//Refresh. ��������� ������ �������
			result = true;
		}else{ 	//������� � ����� ������ ��� ����
			lg.debug("Failure. The student on this course is already");
		}
		return result;
	}

	//�����. �� ���������� ���������� ������� ������ ����������
	//�������� ������ List � ���������� ������� ��������. � ������ ������: ����� �� ����� ��� ��������
	private  BiFunction<? super Integer, ? super List<Integer>, ? extends List<Integer>> _defaultList(int id) {
		return (t, u) -> {ArrayList<Integer> r = new ArrayList<Integer>(); r.add(id); return r;};
	}


	//������� ��������
	//�������� �� ���� �������: 1. ��������� ����� ���������� ��������; 2. �� �������. ��������� ��������� ������� � ����� �������.
	private  boolean _remove(Student student, boolean isBreak) {
		boolean result = false;
		Session oldSession;				 //������ ���������� ��������
		int keyPrim;							 //�������� ���� ���������� ���������� ��������
		int keyS;								 //���� �� ��������
		int keyC;						 		 //���� �� �����
		List<Integer> listSs;					 //������� ������ �������� (����� ��� jointS)
		//���������
		Iterator<Integer> iter;
		boolean isLast = false;
		
//		if(mapPrim == null) _bindingToStudy();	//��������. ����������� � ���������, �� ������� �������������
		keyPrim = DAOkeys.getKeyPrimary(number, student.getBookNum()); //���������� ��������� �����(���) ������ ���������� ��������
		if(mapPrim.containsKey(keyPrim)) {		//����� ������� ���������: ��������
			keyS = student.hashCode();			//���������� �����(���) �� ���������� ��������
			keyC = hashCode();					//���������� �����(���) �� ����� �����
			listSs = jointS.get(keyS);
			_debugSynch(keyPrim, keyC, keyS, true);
			//���������� ���������� �� ������� ��������. ��������� ��� ������
			for(iter = listSs.iterator(); iter.hasNext();){	if(iter.next() == keyPrim) iter.remove(); } 
			//���������� ���������� �� ������� �����. ��������� ��� ������
			for(iter = listSc.iterator(); iter.hasNext();){	if(iter.next() == keyPrim) iter.remove(); }
			if(listSs.isEmpty()) { 			//������� �������� ��������� ���� �� �����: ��� ��������� ��� ��������.
				//dao.deleteStudent(student); //�� ������ �� ��������� �� �����, � �������� ���������� 
				jointS.remove(keyS);		//�� ������ �� ��������� �� �����, � �������� ���������� 
				isLast = true;
			}
			oldSession = mapPrim.remove(keyPrim);	//���������� ���� ����������: ���������� ��������� ����� ����������
			_debugSynch(keyPrim, keyC, keyS, false);
//			dao._deleteSession(oldSession);
			//Notify �������� � ������ ������� ���������� ��������
			if(isBreak) {	//���������
				oldSession.setStatus(BREAK);
				((StudentImpl)student)._notifyOfExclude(isLast);
			}else {			//������
				oldSession.setStatus(GRADUATE);
				((StudentImpl)student)._notifyOfGraduate(oldSession, isLast);
			}
			if(listSc.isEmpty()) emptyCourse = true;	//Refresh. ��������� ������ �������, ���� �� ����� �� �������� ������ ���������
			result = true;
		}else{ 	//������ �������� �� ���� ����� ���
			lg.debug("Failure. There is no the student on this course");
		}
		return result;
	}

	//Debug. Check ������������� ������� � ��������� ������ ��� ������ ����������.
	//Assert ��� 2-� �������:
	//	1. ����� ��� ������ ���� �� ��������� �����, ����� ��� ������ ���� �� ��� ������� ����������: � ����� ������� ������
	//	2. ����� �� ���� ������ ��� ��������� �����, ����� � �� ������ ���� �����: �� � ����� ������� �����
	private void _debugSynch(Integer keyPrim, Integer keyC, Integer keyS, boolean primeKeyIsHave) {		
		boolean match;
		match = (jointS.get(keyS) == null)? false: jointS.get(keyS).stream().anyMatch(a -> a.equals(keyPrim));
		if((match && !primeKeyIsHave) || (!match && primeKeyIsHave))
			throw new RuntimeException("Sessions not synch on student");
		match = (jointC.get(keyC) == null)? false: jointC.get(keyC).stream().anyMatch(a -> a.equals(keyPrim));
		if((match && !primeKeyIsHave) || (!match && primeKeyIsHave))
			throw new RuntimeException("Sessions not synch on course");
	}

	//Notify.����������� �� ����� � ���������� ��������� ����� � ����� � �������� (����������� ��������� ������)
	void _notifyOfGraduate(Student student) {
		_remove(student, false);
	}

	//��� ������, ����� �� ����� ��� ��������� � �������� ������ �� �������� ����� ����� �� �����.
	//� ���� ������ ��������� �������������� ��������: ����� �������� � �����, ������� ����� � ���.
	boolean _isEmptyCourse(boolean willDelete) {
		if(emptyCourse && willDelete) {
			if(!listSc.isEmpty()) throw new RuntimeException("Course is not ready to delete. There are still students"); //debug
			jointC.remove(listSc);
		}
		return emptyCourse;
	}

	//��������(�����������) ����� � �������� ��������. ���������� ������� ������
	CourseImpl _bindingToStudy() {
		if(mapPrim == null){
	        mapPrim = studyImpl.mapPrim;
			jointS = studyImpl.joinStudentToPool;
			jointC = studyImpl.joinCourseToPool;
			listSc = new ArrayList<>();
			List<Integer> _list = jointC.putIfAbsent(hashCode(), listSc);
			if(_list != null) listSc = _list;	
			if(!listSc.isEmpty()) emptyCourse = false;
		}
		return this;
	}

	@Override
	public void _afterJsonDeserial() {
        _bindingToStudy();	//�������� � �����
	}

	@Override
	public int hashCode() {
		return hashCodeCourse(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof CourseImpl))
			return false;
		CourseImpl other = (CourseImpl) obj;
		if (number != other.number)	
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("\n����:\n\t��������: %s\n\t�����: %s\n\t���������: %.2f\n\t������: %s\n\t"
							 + "����� ���������: %d\n\t����� �����������: %d\n",
							 name, number, cost, emptyCourse?"�����������:�� ����� ��� ���������":"�����������",
							 listSc.size(), getListProfessor().size());
	}

	String toStringAsPart() {
		String result;
		result = String.format("\t\t%s,\t%s\n", name, getListProfessor().isEmpty()?"�������� ���������������":"����� �����������: " + getListProfessor().size());
		return result;
	}
}

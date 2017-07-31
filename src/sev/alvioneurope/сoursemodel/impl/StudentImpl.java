package sev.alvioneurope.�oursemodel.impl;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import sev.alvioneurope.�oursemodel.impl.StudyImpl.Session;
import sev.alvioneurope.�oursemodel.impl.StudyImpl.SessionImpl;


//��.������: "�������"
class StudentImpl implements Student {
	private static final Random id = new Random();	//Default case
	//��������
	private String name;
	private String address;
	private String phone;
	private String email;
	private Integer bookNum;
	//���������
	private HashSet<SessionImpl> coursesArchive;
	private transient StudyImpl studyImpl;
	boolean isFree;


//------------------------ Constructors ------------------------------------
   StudentImpl(){
    	studyImpl = StudyImpl.getStudy();
        bookNum = id.nextInt();
        coursesArchive = new HashSet<>();
        isFree = false;
  };

   StudentImpl(int bookNum, String name, String address, String phone, String email) {
    	this();
        this.bookNum = bookNum;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

//------------------------ Methods (��������) ------------------------------------
	@Override
	public String getName() {
		return  name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public void setPhone(String telephone) {
		this.phone = telephone;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	@Override
	public Integer getBookNum() {
		return bookNum;
	}

	@Override
	public float getAverageScore() {	//��������� �������� �������� ����
		DoubleSummaryStatistics statCoursesFinished, statCoursesPassing;
        statCoursesPassing 	=  getListPassing().stream().filter(s -> s.getListScore().size() != 0)	//���������, ������ ��� ���� ������
        											    .collect(Collectors.summarizingDouble(a -> a.getScoreAverage()));
        statCoursesFinished = getListFinished().stream().collect(Collectors.summarizingDouble(a -> a.getScoreFinal()));
        statCoursesPassing.combine(statCoursesFinished);
	    return (float) statCoursesPassing.getAverage();
	}

	@Override
	public List<Session> getListFinished() {	//��������� ������ ������������ ������
		return coursesArchive.stream().collect(Collectors.toList());
	}

	@Override
	public List<Session> getListPassing() {	//��������� ������ ������� ������ (������)
		return studyImpl.getListCourse(this);
	}

	@Override
	public void addToCourse(Course course) {	//�������� �� ����. ��� ������ ����� ������� � ������� ����������. Redirect �� ����
			course.actAddToCourse(this);
	}


	//------------------------------- ��������� ----------------------------------------
	
	void _notifyOfGraduate(Session _session, boolean isLast) {	//�����������. �� ����� ������
		coursesArchive.add((SessionImpl) _session);
		if(isLast) isFree = true;
	}

	void _notifyOfExclude(boolean isLast) {	//�����������. ��������� ���������� �����
		if(isLast) isFree = true;
	}
	
	void _notifyOfAdd() {	//�����������. ���������� �������� ���� ������� ����� ����
		isFree = false;
	}

	@Override
	public int hashCode() {
		return hashCodeStudent(bookNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof StudentImpl))
			return false;
		StudentImpl other = (StudentImpl) obj;
		if (bookNum != other.bookNum)	
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result;
		if(isFree)	result = String.format("\n������� %s ��� �����, ��������\n", name); 
		else result = String.format("\n�������:\n\t���: \t%s\n\t�����: \t%s\n\t�������: %s\n\temail: \t%s\n\t����� �������: \t%d\n\t"
									+"������� ������������: \t%.2f\n\t����� ������, �� ������� ��������: %d\n\t����� ������, ������� ��������: %d\n",
									name, address, phone, email, bookNum, getAverageScore(), getListPassing().size(), getListFinished().size());
		return result;
	}

	String toStringAsPart() {
		String result;
		if(isFree) result = String.format("\t\t������� %s ����� �� ��������\n", name); 
		else 		 result = String.format("\t\t%s, ��� �����: %s\n", name, address);
		return result;
	}
}

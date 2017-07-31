package sev.alvioneurope.coursemodel.impl;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import sev.alvioneurope.coursemodel.impl.StudyImpl.Session;
import sev.alvioneurope.coursemodel.impl.StudyImpl.SessionImpl;


//Обьект: "Студент"
class StudentImpl implements Student {
	private static final Random id = new Random();	//Default case
	//доменные
	private String name;
	private String address;
	private String phone;
	private String email;
	private Integer bookNum;
	//служебные
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

//------------------------ Methods (доменные) ------------------------------------
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
	public float getAverageScore() {	//Получение текущего среднего бала
		DoubleSummaryStatistics statCoursesFinished, statCoursesPassing;
        statCoursesPassing 	=  getListPassing().stream().filter(s -> s.getListScore().size() != 0)	//Включение, только где есть оценки
        											    .collect(Collectors.summarizingDouble(a -> a.getScoreAverage()));
        statCoursesFinished = getListFinished().stream().collect(Collectors.summarizingDouble(a -> a.getScoreFinal()));
        statCoursesPassing.combine(statCoursesFinished);
	    return (float) statCoursesPassing.getAverage();
	}

	@Override
	public List<Session> getListFinished() {	//Получение списка прослушанных курсов
		return coursesArchive.stream().collect(Collectors.toList());
	}

	@Override
	public List<Session> getListPassing() {	//Получение списка текущих курсов (сессий)
		return studyImpl.getListCourse(this);
	}

	@Override
	public void addToCourse(Course course) {	//Добавить на курс. Как вторая точка доступа к функции добавления. Redirect на курс
			course.actAddToCourse(this);
	}


	//------------------------------- Служебные ----------------------------------------
	
	void _notifyOfGraduate(Session _session, boolean isLast) {	//Уведомление. На курсе выпуск
		coursesArchive.add((SessionImpl) _session);
		if(isLast) isFree = true;
	}

	void _notifyOfExclude(boolean isLast) {	//Уведомление. Досрочное прерывание курса
		if(isLast) isFree = true;
	}
	
	void _notifyOfAdd() {	//Уведомление. Добавление студента было сделано через курс
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
		if(isFree)	result = String.format("\nСтудент %s вне учёбы, свободен\n", name); 
		else result = String.format("\nСтудент:\n\tимя: \t%s\n\tадрес: \t%s\n\tтелефон: %s\n\temail: \t%s\n\tномер зачётки: \t%d\n\t"
									+"средняя успеваемость: \t%.2f\n\tчисло курсов, на которые зачислен: %d\n\tчисло курсов, которые закончил: %d\n",
									name, address, phone, email, bookNum, getAverageScore(), getListPassing().size(), getListFinished().size());
		return result;
	}

	String toStringAsPart() {
		String result;
		if(isFree) result = String.format("\t\tСтудент %s нигде не числится\n", name); 
		else 		 result = String.format("\t\t%s, его адрес: %s\n", name, address);
		return result;
	}
}

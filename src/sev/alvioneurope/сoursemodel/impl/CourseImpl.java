package sev.alvioneurope.сoursemodel.impl;

import static sev.alvioneurope.сoursemodel.impl.StudyImpl.SessionStatus.*;

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

import sev.alvioneurope.сoursemodel.impl.StudyImpl.Session;
import sev.alvioneurope.сoursemodel.impl.StudyImpl.SessionImpl;

//ДБ.Обьект: "Курс"
class CourseImpl implements Course, AfterJsonDeserial{
	private transient Logger lg = LogManager.getLogger();
	private static final Random id = new Random();	//Default case
	//Доменные
	private String name;
	private int    number;
	private float  cost;
	private List<ProfessorImpl>	 professorsList;
	//Служебные
//	private transient DAOimpl dao;
	private transient StudyImpl studyImpl;
	boolean emptyCourse;
	//Привязка курса к учебному процессу
	private transient HashMap<Integer, SessionImpl> mapPrim;	//Сущность ассоциации: pool всех сессий по основному ключю 
	private transient HashMap<Integer, List<Integer>> jointS;  		//Внешний ключ ассоциации по студентам
	private transient HashMap<Integer, List<Integer>> jointC;  		//Внешний ключ ассоциации по курсам
	private transient List<Integer> listSc;					   		//Выборка сессий курса (через его jointC)


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
        _bindingToStudy();	//Привязка к Учёбе
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
	public List<Session> getListSessions() {	//Получение списка студентов/сессий этого курса
        return   studyImpl.getListStudent(this); 
	}

	@Override
	public List<ProfessorImpl> getListProfessor() {	//Получение списка профессоров этого курса
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
	
	//Удаление студента.Точка доступа: основная и единственная
	@Override
	public boolean actRemoveFromCourse(Student student) {
		return _remove(student, true);
	}

	//Добавить студента.Точка доступа основная (дополнительная ссылка сюда: со стороны студента) 
	@Override
	public boolean actAddToCourse(Student student) {
		boolean result = false;
		Session newSession;				 //Сессия для добавляемого студента
		int keyPrim;							 //Основной ключ ассоциации для добавляемого студента: ключ к его новой сессии
		int keyS;								 //Ключ по студенту
		int keyC;						 		 //Ключ по курсу
		List<Integer> listSs;					 //Выборка сессий студента (через его jointS)
		
//		if(mapPrim == null) _bindingToStudy();	//Привязка. Выполняется с задержкой, до первого востребования
		keyPrim = DAOkeys.getKeyPrimary(number, student.getBookNum()); 	//Расчёт основного ключа(хеш) будущей новой сессии для добавляемого студента

//		if(student.getListFinished().stream().anyMatch(a -> { lg.info("!!!!!!{}\n{}", a.getCourse(), this); return a.getCourse().equals(this);})) {
//		if(student.getListFinished().stream().anyMatch(a -> { lg.info("!!!!!!{}\n", a.getCourse().equals(this)); return a.getCourse().equals(this);})) {
		if(student.getListFinished().stream().anyMatch(a -> a.getCourse().equals(this))) {
			lg.debug("Failure. The student on this course have been studied earlier");
		}else if(!mapPrim.containsKey(keyPrim)) {	 //Студента на таком курсе нет. Добавить
			keyS = student.hashCode();		//Вычисление ключа(хеш) по добавляемому студенту
			keyC = hashCode();				//Вычисление ключа(хеш) по этому курсу
			listSs = jointS.get(keyS);
			_debugSynch(keyPrim, keyC, keyS, false);
//			newSession = dao.readSession(this, student);	//Выборка существующей сессии из БД или создание новой. Его ключ совпадает с ранее расчётным: keyPrim
			newSession = new SessionImpl(student.getBookNum(), getNumber());	//Выборка существующей сессии из БД или создание новой. Его ключ совпадает с ранее расчётным: keyPrim
			if(listSs == null) {		//Это новый студент, для которого на этой учёбе это будет первый курс 
				jointS.compute(keyS, _defaultList(keyPrim));	//Создание ассоциации нового студента с его первым курсом
			} else {					//Студент уже есть на других курсах, но не на этом
				listSs.add(keyPrim);	//Обновление ассоциации со стороны студента. Добавление новой сессии к его уже существующим
			}
			if(listSc == null) {		//Это первый студент для этого курса
				throw new IllegalStateException("Course is not tied to StudyImpl. Name of course: " + name);	//Debug
//				jointC.compute(keyC, _defaultList(keyPrim));	//Создание ассоциации со стороны курса со своим первым студентом
			}
//			 else {					//На курсе уже есть другие студенты
				listSc.add(keyPrim);	//Обновление ассоциации со стороны курса. Добавление новой сессии к этому курсу
//			}
			mapPrim.put(keyPrim, (SessionImpl) newSession);			//Обновление пула ассоциации: обновление основного ключа ассоциации
			_debugSynch(keyPrim, keyC, keyS, true);
			((StudentImpl)student)._notifyOfAdd();	//Notify студента со стороны курса
			emptyCourse = false;				//Refresh. Уточнение своего статуса
			result = true;
		}else{ 	//Студент с таким курсом уже есть
			lg.debug("Failure. The student on this course is already");
		}
		return result;
	}

	//Инфра. По начальному заполнению внешних ключей ассоциации
	//Создание нового List с включением первого элемента. В данном случае: ключа по курсу или студенту
	private  BiFunction<? super Integer, ? super List<Integer>, ? extends List<Integer>> _defaultList(int id) {
		return (t, u) -> {ArrayList<Integer> r = new ArrayList<Integer>(); r.add(id); return r;};
	}


	//Удалить студента
	//Удаление по двум мотивам: 1. Досрочное явное прерывание обучения; 2. По выпуску. Получение финальной отметки в конце ссессии.
	private  boolean _remove(Student student, boolean isBreak) {
		boolean result = false;
		Session oldSession;				 //Сессия удаляемого студента
		int keyPrim;							 //Основной ключ ассоциации удаляемого студента
		int keyS;								 //Ключ по студенту
		int keyC;						 		 //Ключ по курсу
		List<Integer> listSs;					 //Выборка сессий студента (через его jointS)
		//служебные
		Iterator<Integer> iter;
		boolean isLast = false;
		
//		if(mapPrim == null) _bindingToStudy();	//Привязка. Выполняется с задержкой, до первого востребования
		keyPrim = DAOkeys.getKeyPrimary(number, student.getBookNum()); //Вычисление основного ключа(хеш) сессии удаляемого студента
		if(mapPrim.containsKey(keyPrim)) {		//Такой студент числиться: удаление
			keyS = student.hashCode();			//Вычисление ключа(хеш) по удаляемому студенту
			keyC = hashCode();					//Вычисление ключа(хеш) по этому курсу
			listSs = jointS.get(keyS);
			_debugSynch(keyPrim, keyC, keyS, true);
			//Обновление ассоциации со стороны студента. Подчистка его сессий
			for(iter = listSs.iterator(); iter.hasNext();){	if(iter.next() == keyPrim) iter.remove(); } 
			//Обновление ассоциации со стороны курса. Подчистка его сессий
			for(iter = listSc.iterator(); iter.hasNext();){	if(iter.next() == keyPrim) iter.remove(); }
			if(listSs.isEmpty()) { 			//Студент завершил последний курс на Учёбе: как выпускник или досрочно.
				//dao.deleteStudent(student); //Он больше не числиться на учёбе, и подлежит исключению 
				jointS.remove(keyS);		//Он больше не числиться на учёбе, и подлежит исключению 
				isLast = true;
			}
			oldSession = mapPrim.remove(keyPrim);	//Обновление пула ассоциации: обновление основного ключа ассоциации
			_debugSynch(keyPrim, keyC, keyS, false);
//			dao._deleteSession(oldSession);
			//Notify студента с учётом причины завершения обучения
			if(isBreak) {	//Досрочное
				oldSession.setStatus(BREAK);
				((StudentImpl)student)._notifyOfExclude(isLast);
			}else {			//Выпуск
				oldSession.setStatus(GRADUATE);
				((StudentImpl)student)._notifyOfGraduate(oldSession, isLast);
			}
			if(listSc.isEmpty()) emptyCourse = true;	//Refresh. Уточнение своего статуса, если на курсе не осталось больше студентов
			result = true;
		}else{ 	//Такого студента на этом курсе нет
			lg.debug("Failure. There is no the student on this course");
		}
		return result;
	}

	//Debug. Check синхронизации внешних и основного ключей для случая добавления.
	//Assert для 2-х случаев:
	//	1. Когда эта сессия есть по основному ключу, тогда она должна быть по обе стороны ассоциации: в обоих внешних ключах
	//	2. Когда по этой сессия нет основного ключа, тогда её не должно быть нигде: ни в каком внешнем ключе
	private void _debugSynch(Integer keyPrim, Integer keyC, Integer keyS, boolean primeKeyIsHave) {		
		boolean match;
		match = (jointS.get(keyS) == null)? false: jointS.get(keyS).stream().anyMatch(a -> a.equals(keyPrim));
		if((match && !primeKeyIsHave) || (!match && primeKeyIsHave))
			throw new RuntimeException("Sessions not synch on student");
		match = (jointC.get(keyC) == null)? false: jointC.get(keyC).stream().anyMatch(a -> a.equals(keyPrim));
		if((match && !primeKeyIsHave) || (!match && primeKeyIsHave))
			throw new RuntimeException("Sessions not synch on course");
	}

	//Notify.Уведомление от Учёбы о завершении студентом курса в связи с выпуском (высталением финальной оценки)
	void _notifyOfGraduate(Student student) {
		_remove(student, false);
	}

	//Для случай, когда на курсе нет студентов и приходит запрос на удаление этого курса из Учёбы.
	//В этом случае требуется дополнительные действия: снять привязку к Учёбе, удалить связь с ней.
	boolean _isEmptyCourse(boolean willDelete) {
		if(emptyCourse && willDelete) {
			if(!listSc.isEmpty()) throw new RuntimeException("Course is not ready to delete. There are still students"); //debug
			jointC.remove(listSc);
		}
		return emptyCourse;
	}

	//Привязка(подключение) курса к учебному процессу. Подготовка рабочих ссылок
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
        _bindingToStudy();	//Привязка к Учёбе
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
		return String.format("\nКурс:\n\tназвание: %s\n\tномер: %s\n\tстоимость: %.2f\n\tстатус: %s\n\t"
							 + "число студентов: %d\n\tчисло профессоров: %d\n",
							 name, number, cost, emptyCourse?"простаивает:на курсе нет студентов":"действующий",
							 listSc.size(), getListProfessor().size());
	}

	String toStringAsPart() {
		String result;
		result = String.format("\t\t%s,\t%s\n", name, getListProfessor().isEmpty()?"обучение самостоятельное":"число профессоров: " + getListProfessor().size());
		return result;
	}
}

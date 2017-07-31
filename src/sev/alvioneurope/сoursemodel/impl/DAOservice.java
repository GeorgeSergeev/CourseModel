package sev.alvioneurope.сoursemodel.impl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sev.alvioneurope.сoursemodel.impl.StudyImpl.Session;

//ДБ.Обьект: "DAO". Доступ к БД. Интерфейс
interface DAOservice extends DAO {
	Logger lg = LogManager.getLogger();

	//------------------- Сводные методы под реализатор (служебные) ---------------
	<T> T				_actCreate(T entity);
	<T> T				_actRead(Integer key, Class<?> clazz);
	List<?>				_actReadAll(Class<?> clazz);
	<T> void			_actUpdate(T entity);
	<T> void			_actDelete(T entity);



	//------------------- Публичные методы -----------------------------------------
	
	//------------------- Create -------------------
	default BuilderStudent createStudent(Student student){
		return _proxy(student, BuilderStudent.class);
	};
	
	default BuilderCourse createCourse(Course course){
		return _proxy(course, BuilderCourse.class);
	};

	default BuilderProfessor createProfessor(Professor professor){
		return _proxy(professor, BuilderProfessor.class);
	};

	
	//------------------- Read ----------------------
	default Student readStudent(int gradeBookNum){
		int key = DAOkeys.getKeyStudent(gradeBookNum);
		return _readStudentOnKey(key);
	};
	
	//Для служебного пользования
	default Student _readStudentOnKey(int key){
		return _actRead(key, StudentImpl.class);
	};

	default Course readCourse(int numCourse){
		int key = DAOkeys.getKeyCourse(numCourse);
		return _readCourseOnKey(key);
	};
	
	//Для служебного пользования
	default Course _readCourseOnKey(int key){
		return ((CourseImpl)_actRead(key, CourseImpl.class))._bindingToStudy();
	};
	
	default Professor readProfessor(String name, String address){
		int key = DAOkeys.getKeyProfessor(name, address);
		return _actRead(key, ProfessorImpl.class);
	};
	
//	default Session readSession(Course course, Student student){	//1 вариант по двум внешним ключам + default случай
//		int key = DAOkeys.getKeyPrimary(course.getNumber(), student.getBookNum());
//		Session result = _actRead(key, SessionImpl.class);
//		if(result == null) result = new SessionImpl(student.getBookNum(), course.getNumber());
//		return result;
//	};
//
//	default Session readSession(int keyPrime){		//2 вариант по основному ключу, но без default случая
//		return _actRead(keyPrime, SessionImpl.class);
//	};

	
	//------------------- ReadAll ------------------
	default List<Student>	 	readStudentAll()	 	{ return (List<Student>) 	  _actReadAll(Student.class); };
	default List<Course>		readCourseAll()		 	{ return (List<Course>) 	  _actReadAll(Course.class); };
	default List<Professor>		readProfessorAll()	 	{ return (List<Professor>)	  _actReadAll(Professor.class); };
	//default List<Session>	readCoursePassingAll()	{ return (List<Session>)_actReadAll(Session.class); };

	
	//------------------- Update --------------------
	default void updateStudent(Student student){
		 _actUpdate(student);
	};
	
	default void updateCourse(Course course){
		 _actUpdate(course);
	};
	
	default void updateProfessor(Professor professor){
		 _actUpdate(professor);
	};

//	default BuilderStudent updateStudent(Student student){
//		return _proxy(student, BuilderStudent.class, UPDATE);
//	};
//	
//	default BuilderCourse updateCourse(Course course){
//		return _proxy(course, BuilderCourse.class, UPDATE);
//	};
//	
//	default BuilderProfessor updateProfessor(Professor professor){
//		return _proxy(professor, BuilderProfessor.class, UPDATE);
//	};
	
//	default Session updateSession(Session session){
//		return _actUpdate(session);
//	};
	

	//------------------- Delete --------------------
	default void		deleteStudent(Student student) {
		if(!((StudentImpl)student).isFree) lg.warn("Failure. Student is busy. Free student from courses");
		else	_actDelete(student);};
	default void		deleteCourse(Course course)	{
		if(!((CourseImpl)course)._isEmptyCourse(true)) lg.warn("Failure. Course is busy. Free Course from studentes");
		else 	_actDelete(course);};
	default void 		deleteProfessor(Professor professor) {
		if(((ProfessorImpl)professor).meterFree != 0) lg.warn("Failure. Professor is busy. Free Professor from courses");
		else	_actDelete(professor);};
	//Для служебного пользования
	default void		_deleteSession(Session coursePas)		{ _actDelete(coursePas);};


	//------------------- Индивидуально для StudyImpl --------------------
	StudyImpl readStudy();
	void updateStudy() throws IOException;


	//------------------------- Служебные --------------------------------------------------
	//Организация создания/модификации обьектов в виде цепочки вызовов
	@SuppressWarnings("unchecked")
	default <T, R> R _proxy(T entity, Class<R> clazz){
		R result = (R) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] {clazz}, _handlerCreate(entity));
		return result;
	}

	default <T> InvocationHandler _handlerCreate(T entity) { 
		return new InvocationHandler(){	//Отдельным классом в рамках родителя нельзя.
										//Становится статическим, что неприемлено для последующих вызовов
										//Вне родителя нельзя: содержит внутренние вызовы к своему родителю
		private T _entity;

		{ _entity = entity;	}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result;
			boolean isBuider = false;	//, isUpdate = false;
			isBuider = method.getName().equals("build");
			if(!isBuider) { 			//Поддержка цепочки вызова
				//method.invoke(_entity, args);
				_entity.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(_entity, args);
				result = proxy;		
			}else {		//Итоговый вызов
				//if(_type == CREATE) {
				 result =  _actCreate(_entity);	
				//}else {				  result = 	_actUpdate(_entity);}	
				//Запуск на отработку механизма "Донастройка после JSON"
				if(result instanceof AfterJsonDeserial) ((AfterJsonDeserial)result)._afterJsonDeserial();
			}	
			return result;
		}
		};
	};

//	enum TypeProxy {CREATE, UPDATE}

	
	//------------------------- Интерфейсы для цепочек вызовов --------------------------------------------------
	interface BuilderStudent {
								BuilderStudent  setBookNum(int bookNum);
								BuilderStudent  setName(String name);
								BuilderStudent  setAddress(String address);
								BuilderStudent  setPhone(String telephone);
								BuilderStudent  setEmail(String email);
								Student			build();
	} 
	interface BuilderCourse {
								BuilderCourse  setNumber(int number);
								BuilderCourse  setName(String name);
								BuilderCourse  setCost(float cost);
								BuilderCourse  setProfessor(Professor prof);
								Course			build();
	} 
	interface BuilderProfessor {
								BuilderProfessor  setName(String name);
								BuilderProfessor  setAddress(String address);
								BuilderProfessor  setPhone(String telephone);
								BuilderProfessor  setPayment(float payment);
								Professor		  build();
	} 
}

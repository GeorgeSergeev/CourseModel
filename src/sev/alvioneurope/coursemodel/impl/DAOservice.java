package sev.alvioneurope.coursemodel.impl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sev.alvioneurope.coursemodel.impl.StudyImpl.Session;

//Обьект служебный интерфейс DAO. Де/Мультиплексор доступа.
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
		return _readStudentOnKey(DAOkeys.getKeyStudent(gradeBookNum));
	};
	
	//Для служебного пользования
	default Student _readStudentOnKey(int key){
		return _actRead(key, StudentImpl.class);
	};

	default Course readCourse(int numCourse){
		return _readCourseOnKey(DAOkeys.getKeyCourse(numCourse));
	};
	
	//Для служебного пользования
	default Course _readCourseOnKey(int key){
		return ((CourseImpl)_actRead(key, CourseImpl.class))._bindingToStudy();
	};
	
	default Professor readProfessor(String name, String address){
		return _actRead(DAOkeys.getKeyProfessor(name, address), ProfessorImpl.class);
	};
	
	
	//------------------- ReadAll ------------------
	default List<Student>	 	readStudentAll()	 	{ return (List<Student>) 	  _actReadAll(Student.class); };
	default List<Course>		readCourseAll()		 	{ return (List<Course>) 	  _actReadAll(Course.class); };
	default List<Professor>		readProfessorAll()	 	{ return (List<Professor>)	  _actReadAll(Professor.class); };

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

	//------------------- Delete --------------------
	default void		deleteStudent(Student student) {
		if(!((StudentImpl)student).isFree)	 lg.warn("Failure. Student is busy. Free student from courses");
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
				_entity.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(_entity, args);
				result = proxy;		
			}else {		//Итоговый вызов
				 result =  _actCreate(_entity);	
				if(result instanceof AfterJsonDeserial) ((AfterJsonDeserial)result)._afterJsonDeserial();
			}	
			return result;
		}
		};
	};

	
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

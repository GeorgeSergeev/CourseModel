package sev.alvioneurope.�oursemodel.impl;

import static sev.alvioneurope.�oursemodel.impl.DAOkeys.keyDEPO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import sev.alvioneurope.�oursemodel.impl.StudyImpl.Session;
import sev.alvioneurope.�oursemodel.impl.StudyImpl.SessionImpl;

//��.������: "DAO". ������ � �������� ��. ���������� 
class DAOimpl implements DAOservice{
	private static DAO dao;
	//static DAOservice daoService;
	private Logger lg = LogManager.getLogger();
	private static final String DIR_HOME =			 "_CourseModel";
	private static final String DIR_STUDENT =		 "Student";
	private static final String DIR_COURSE =		 "Course";
	private static final String DIR_PROFESSOR =		 "Professor";
	private static final String FILE_STUDY =		 "StudyImpl";
	private static final String EXT =		 	 	 ".json";
	private static final String CHARSET =		 	 "windows-1251";

	private Path dirHome = null;
	private Gson gson;
//	private HashMap<Integer, Object> depo;  //����������� �����������. �������� ���-�����
	

	//-------------------------- Constructors/������� ----------------------------
	static DAO getDAO(boolean... isClear) {
		return getDAO(null, isClear);
	}

	static DAO getDAO(String homeDir, boolean... isClear) {
		if(dao == null)	dao = new DAOimpl(homeDir, (isClear.length != 0)?isClear[0]:false);
		return dao;
	}

	private DAOimpl(String homeDir, boolean isClear){
		if(homeDir != null)
			try {
				dirHome = Files.createDirectories(Paths.get(homeDir).resolve(DIR_HOME));
			} catch (IOException e) {}
		if(dirHome == null) {
			try {
				dirHome = Files.createDirectories(FileSystems.getDefault().getPath(DIR_HOME));
			} catch (Exception e) {
				lg.error("Can't create home dir. Error: {}", () -> e.getMessage());
				System.exit(1);
			}
		}
		lg.info("Home dir is '{}'", dirHome.toAbsolutePath());
		if(isClear) _clearHomeDir();
		gson = new GsonBuilder().setPrettyPrinting()
								.registerTypeAdapter(Session.class, _adapterDeserl())
								.registerTypeAdapter(Course.class,		  _adapterDeserl())
								.registerTypeAdapter(Student.class,		  _adapterDeserl())
								.registerTypeAdapter(Professor.class,	  _adapterDeserl()).create();
//		depo = new HashMap<Integer, Object>();
	}


	//------------------- ������� ������ �� ���������� ----------------------------
	//�� ��������� ����� ������������ �� �����, �� �� ���� ������ ����������, �
	//�� ������ �����������, ��� �� default ��������. 


	//-------------------------- Create ---------------------------- 
	public <T> T _actCreate(T entity)  {	//<T> ��� ������
		int key = entity.hashCode();
		T result = _actRead(key, entity.getClass());
		if(result == null) {
			try{
				Path path = dirHome.resolve(TypeEntity.getDir(entity.getClass())).resolve(key + EXT);
				Files.write(path, gson.toJson(entity).getBytes(Charset.forName(CHARSET)));
//				depo.put(keyDEPO(key, entity.getClass()), entity);
			}catch(IOException e) {lg.debug("Fail create: {}", e);}
			result = entity;
		}
		return result;
	};

	//-------------------------- Read ----------------------------
	public <R> R _actRead(Integer key, Class<?> clazz) {	//<R> ��� ������. � ����������: ���� � �������. ������ � ������ �����������
		R result = null;
//		archive = (R) depo.get(keyDEPO(key, clazz));
//		if(archive == null) {
			  try{
				  Path path = dirHome.resolve(TypeEntity.getDir(clazz)).resolve(key + EXT);
				  String in = Files.lines(path, Charset.forName(CHARSET)).collect(Collectors.joining());
				  result = (R) gson.fromJson(in, clazz); 
//				  depo.put(keyDEPO(key, clazz), result);
			  }catch(IOException e) {lg.trace("Fail read: {}", e);}
//		}else {
//			  result = archive;
//		}
		return result;
	};

	//-------------------------- ReadAll ----------------------------
	public List<?> _actReadAll(Class<?> clazz) {	//clazz - ��� ��������, ���������� � ������
		List<?> result = null;
		try{
			Path path = dirHome.resolve(TypeEntity.getDir(clazz));
			result = (List<?>) Files.list(path).map((file)-> {
							return _actRead(Integer.valueOf(file.getFileName().toString().replace(EXT, "")), clazz);
						 }).collect(Collectors.toList());
		} catch (IOException e) {lg.debug("Fail readAll: {}", e);}
		return result == null?Collections.EMPTY_LIST:result;
	}

	//-------------------------- Update ----------------------------
	public <T> void _actUpdate(T entity)  {	//<T> ��� ������
		try{
			Path path = dirHome.resolve(TypeEntity.getDir(entity.getClass())).resolve(entity.hashCode() + EXT);
			Files.write(path, gson.toJson(entity).getBytes(Charset.forName(CHARSET)));
//			depo.putIfAbsent(keyDEPO(entity.hashCode(), entity.getClass()), entity);
		} catch (IOException e) { lg.debug("Fail update: {}", e);}
		return;
	};

	//------------------------- Delete -------------------------------
	public <T> void _actDelete(T entity)  {	//<T> ��� ������
		try {
			Path path = dirHome.resolve(TypeEntity.getDir(entity.getClass())).resolve(entity.hashCode() + EXT);
			Files.deleteIfExists(path);
//			depo.remove(keyDEPO(entity.hashCode(), entity.getClass()));
		} catch (IOException e) {lg.debug("Fail delete: {}", e);}
		return;
	};


	//------------------------- ��������� CRUD ��� StudyImpl -------------------------------
	@Override
	public StudyImpl readStudy() {
		StudyImpl result = null;
		try{
		  Path path = dirHome.resolve(TypeEntity.getDir(StudyImpl.class)).resolve(FILE_STUDY + EXT);
		  String in = Files.lines(path, Charset.forName(CHARSET)).collect(Collectors.joining());
		  result = gson.fromJson(in, StudyImpl.class); 
		}catch(IOException e) {lg.trace("Fail delete StudyImpl: {}", e);}
		if(result == null) result = StudyImpl.getStudy();
		return result;
	}
	
	@Override
	public void updateStudy() throws IOException {
		try{
			Path path = dirHome.resolve(TypeEntity.getDir(StudyImpl.class)).resolve(FILE_STUDY + EXT);
			Files.write(path, gson.toJson(StudyImpl.getStudy()).getBytes(Charset.forName(CHARSET)));
		} catch (IOException e) {lg.trace("Fail update StudyImpl: {}", e); throw e;}
		return;
	}



	//------------------------- ��������� -------------------------------

	//����������� ����������� � �� ����������, ����� JSON  �� �����  ����� ���������� � ����������������� ����� ��� ��������������
	private <T> JsonDeserializer<T> _adapterDeserl() { return new JsonDeserializer<T>() {
			public T deserialize(JsonElement elem, Type typeOfT, JsonDeserializationContext context) {
				T result = null;
				try{
					result = context.deserialize(elem, Class.forName(typeOfT.getTypeName() + "Impl"));
					//������ �� ��������� ��������� "����������� ����� JSON"
					if(result instanceof AfterJsonDeserial) ((AfterJsonDeserial)result)._afterJsonDeserial();
				} catch (Exception e) {}
				return result;
	}};}

	//������� ������� home dir
	private void _clearHomeDir() {
		try {
			Files.walkFileTree(dirHome, new SimpleFileVisitor<Path>() {
			    @Override
			    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			      Files.delete(dir);
			      return FileVisitResult.CONTINUE;
			    }
			
			    @Override
			    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			      Files.delete(file);
			      return FileVisitResult.CONTINUE;
			    }
			  });
		} catch (IOException e) {
				lg.error("Can't clear home dir. Error: {}", () -> e.getMessage());
				System.exit(1);
		}
		lg.info("Clear home dir is Ok!");
	}

	//����������� home dir ������ ����� ������ ������
	private enum TypeEntity {STUDENT(DIR_STUDENT), COURSE(DIR_COURSE), PROFESSOR(DIR_PROFESSOR), STUDY("");
		 private Path  dir;
		 TypeEntity (String dir) {
			 try{
				 this.dir = Files.createDirectories(Paths.get(DIR_HOME, dir).toAbsolutePath());
			 }catch(IOException e) {}
		 }
		 static private Path getDir(Class<?> clazz){
			 return valueOf(clazz.getSimpleName().toUpperCase().replace("IMPL", "")).dir; 
		 }
	}
}
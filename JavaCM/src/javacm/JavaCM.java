/*
Теперь посмотрим как работает наша модель.
С помощью написанных классов, проиграем некую ситуацию:
создадим 3 студента
и будет 2 образовательный курса
курс математики ведет профессор, курс черчение для самостоятельного изучения
все студенты учат математику и получают оценки
также два студента запишутся на курс черчения и буду самостоятельно его изучать

Сериализуем объекты: курс математики и курс черчение в файл формата json и сохраним writeToJson
Десириализуем объекты из файла json в объекты нашей модели readFromJson

Для сериализации и десириализации использовал библиотеку Gson-2.2.4.jar
 */
package javacm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author serega
 */
public class JavaCM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //У нас есть три студента
        Student stIv = new Student("Ivanov", "Sevastopol", "123456", "ivanov@st.ru", 4444);
        Student stPv = new Student("Petrov", "Sevastopol", "232323", "petrov@st.ru", 2222);
        Student stMa = new Student("Masha", "Sevastopol", "776677", "masha@st.ru", 5555);
        
        //Два курса
        EducCourse ecMath = new EducCourse("Mathematic", 28, 400);
        EducCourse ecDrawing = new EducCourse("Drawing", 15, 450);
        
        //Один профессор
        Professor prDrt = new Professor("Jukovskiy", "Moscow", "989898");
        
        //Все студенты решили записаться на курс математики, 
        ecMath.addProfessor(prDrt);//курс математики преподает профессор
        ecMath.addStudent(stIv);
        ecMath.addStudent(stPv);
        ecMath.addStudent(stMa);
        
        //Во время прохождения курса математики студетны получают оценки
        //Ivanov получил
        ecMath.addScoreToStudent(4444, 4f);
        ecMath.addScoreToStudent(4444, 4.4f);
        ecMath.addScoreToStudent(4444, 3.7f);
        //Petrov получил
        ecMath.addScoreToStudent(2222, 4.7f);
        ecMath.addScoreToStudent(2222, 3.4f);
        ecMath.addScoreToStudent(2222, 3.8f);
         //Masha получила
        ecMath.addScoreToStudent(5555, 4.7f);
        ecMath.addScoreToStudent(5555, 4.4f);
        ecMath.addScoreToStudent(5555, 4.9f);       
        
        //Petrov и Ivanov решили самостоятельно изучть черчение
        ecDrawing.addStudent(stIv);
        ecDrawing.addStudent(stPv);
        

        //Сериализуем объект ecDrawing и сохраним в файле
        try {
            writeToJson(ecDrawing, "Drawing.json");
        } catch (IOException ex) {
            Logger.getLogger(JavaCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Сериализуем также объект ecMath и сохраним
        try {
            writeToJson(ecMath, "Math.json");
        } catch (IOException ex) {
            Logger.getLogger(JavaCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Теперь попробуем прочесть данные из файла и восстановить 
        //объект
        EducCourse ecNew = new EducCourse();//создадим некйи курс
        Student ssNew = new Student();//и некого студента
        
        //Прочтем данные объекта из json файла и десириализуем в объект
        try {
            ecNew =(EducCourse)readFromJson(ecNew.getClass(), "Drawing.json");
        } catch (Throwable ex) {
            Logger.getLogger(JavaCM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("New course name = "+ecNew.getName()); 
        System.out.println("New course Id = "+ecNew.getId());
        System.out.println("New courseecNew Price = "+ecNew.getPrice());
        //Объект восстановлен
        System.out.println("Наш объект полностью восстановлен");
        System.out.println();
        //В восстановленом объекте можем найти студента 
        ssNew = ecNew.getStudent(4444);
        System.out.println("New student name = "+ssNew.getName()); 
        System.out.println("New student adrress = "+ssNew.getAddress()); 
        System.out.println("New student email = "+ssNew.getEmail());
        System.out.println("New student telphone = "+ssNew.getTelephone());
    }
    
    //Преобразуем объект в формат json и запишем результат в файл
    public static void writeToJson(Object obj, String fileName) throws IOException{
        
        String gsonString;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer os = new FileWriter(fileName);
        
        //С использование библиотеки gson все довольно просто
        gsonString = gson.toJson(obj);//наш объект obj теперь преобразован в формат json 
        //запишем его в файл
        os.write(gsonString);
        os.close();
        
    }
    
    //Прочтем объект из файла json 
    public static Object readFromJson(Class cls, String fileName) throws IOException{
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader is = new FileReader(fileName);
        BufferedReader br = new BufferedReader(is);
        
        String str;
        String jsonString = "";
        
        str = br.readLine();
        while(str != null){
            jsonString += str;
            str = br.readLine();
        }
        
        Object obj = new Object();
        //десириализуем с помощью gson
        obj = gson.fromJson(jsonString, cls);
        
        return obj;
    }
    
}

/* 1) Создать клон данного репозитория 
2) Разработать средствами языка Java код в соответствии с диаграммой классов (см. courses.gif)
3) Дополнить модель необходимыми CRUD методами.
4) Сериализовать объекты модели в файл формата JSON. 
5) Разработать процедуру десериализации из JSON-файла в объекты модели.
6) Создать pull request 
cd /home/maria/V_prog/CourseModel   java -cp .:gson-2.2.2.jar GsonBuildCl 
javac -cp .:gson-2.2.2.jar GsonBuildCl.java CourseCl.java CourseDBaseCl.java CourseViewerCl.java ProfCl.java ProfCoursRelCl.java StudentCl.java */

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder; 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.File;
import java.io.Reader; 
import java.io.Writer; 
import java.util.Scanner;
import java.util.List; 

public class GsonBuildCl{

/* Предложенная модель представляет собой пример базы данных на которой изображены некоторые сущности и реляционные отношения между ними. Кроме классов сущностей в данной проге были описаны классы отношений и класс CourseDBaseCl хранящий объектную инфу в виде набора ArrayList созданных для каждого класса. Этот же класс отправляется на сериализацию в JSON и читается обратно. Для работы с прогой было создано простое екранное меню позволяющее опробовать различные CRUD методы. Ф-ии класса становятся доступны в меню при выборе конкретного члена списка. Также нужно отметить что это тестовая модель, поэтому ошибки ввода на совести тестера. Никаких проверок обязательных для коммерческих продуктов не осуществлялось. (Отдельно хочу отметить что у меня при вводе чисел с пл.запятой нужно задавать именно ЗАПЯТУЮ иначе выбивает ошибку ввода. Видимо зависит от настроек.)  */

   static CourseDBaseCl db; 

   public static void main(String[] args) {

       String filename=getFilename(); 
       try { 
           db = new CourseDBaseCl();
         try{
             // Если файл - filename существ. из него будет прочитана БД
           if(fileExists(filename))            
               db=(CourseDBaseCl)readFromJson(db.getClass(),filename);
        // Простое меню дем.работу с БД
           callMenu();   
        //  writeToJson if work is already completed; 
           writeToJson(db, filename); 
          }catch(IOException e){
               e.printStackTrace(); 
          } 
       } catch (Exception ex) { 
            ex.printStackTrace(); 
       }           
   }

    
   static String getFilename(){
      return "Test_DB.json";
   }

  
    public static void writeToJson(Object obj, String fileName) throws IOException{ 
         
        String gstring; 
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
        Writer os = new FileWriter(fileName); 
         
        gstring = gson.toJson(obj);
        os.write(gstring); 
        os.close();          
    } 
     
    public static Object readFromJson(Class cls, String fileName) throws IOException{ 
         
       Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
       Reader is = new FileReader(fileName); 
       BufferedReader br = new BufferedReader(is);        
       String str; 
       String jsonString = "";          
       while((str = br.readLine()) != null){ 
           jsonString += str; 
        }          
       Object obj = new Object(); 
       obj = gson.fromJson(jsonString, cls);         
       return obj; 
   } 

   static boolean fileExists(String filename){
     File f = null;
     try{ 
        f = new File(filename);
        return f.exists();
     }catch(Exception e){
        return false;
     } 
   }

   static void callMenu(){
     Scanner sc = new Scanner(System.in);
     int i_choice;
     do  {
       System.out.println("\n Work with the Course list - 1 ");
       System.out.println(" Work with the Student list- 2 ");
       System.out.println(" Work with the CourseViewer list- 3 ");
       System.out.println(" Work with the Prof list  - 4 ");
       System.out.println(" Work with the ProfRelCourse list - 5 ");
       System.out.println(" To finish the work  — 6 ");
       System.out.println(" Input number of you choice:");    
       i_choice=sc.nextInt();
       if(i_choice>0 && i_choice<6)
          callMenuList(i_choice,sc);
     }while(i_choice!=6);
   }
   static void callMenuList(int ich,Scanner sc){
     int i_choice,n;
     do  {
       n=-1;
      System.out.println("\n Show all elements in the list - 1 ");
      System.out.println(" Show N-th element in the list - 2 ");
      System.out.println(" To insert an element in the list — 3 ");
      System.out.println(" Remove N-th element from the list — 4 ");
      System.out.println(" Go back to the main menu  — 5 ");
      System.out.println(" Input number of you choice:");    
      i_choice=sc.nextInt(); 
      if(i_choice>0 && i_choice<5){
         if(i_choice==2||i_choice==4){
             System.out.println("\n Input number of element in the list:");  
             n=sc.nextInt()-1; 
         }
         callDB(i_choice,ich,n,sc);
      }
     }while(i_choice!=5);
   }
   static void callDB(int i_ch,int iL,int n,Scanner sc){
      
       if(i_ch==3)  // add
          newInDB(iL,sc);
       else
         db.readList(iL,n);
       if(i_ch==4){   // delete
          String s=""; 
          System.out.println("\n Are you sure to delete this record from list?: y/n");
          do s=sc.nextLine(); while(s.length()<1);  
          if(s.indexOf("y")>=0)
             db.delInList(iL,n); 
       }else if(i_ch==2)
           callClassMethods(iL,n,sc);
   }
   static void newInDB(int iL,Scanner sc){
      int id,id1;
      String name;
      String adres; 
      String telefon; 
      String email;  
      float cost; 
      switch (iL) {
         case 1:
             System.out.println("\n Input course_id (number of Course):");  
             id=sc.nextInt(); 
             System.out.println(" Input name of Course:");  
              do name=sc.nextLine(); while(name.length()<3);      
             System.out.println(" Input cost of Course:"); 
             cost=(float)sc.nextDouble();
             db.addCourse(id,name,cost);
         break; 
         case 2:
             System.out.println("\n Input studbook_id (number of stud.book):");  
             id=sc.nextInt(); 
             System.out.println(" Input name of stud:");  
             do name=sc.nextLine(); while(name.length()<3);         
             System.out.println(" Input telefon of stud:"); 
             do telefon=sc.nextLine(); while(telefon.length()<3);    
             System.out.println(" Input email of stud:"); 
             do email=sc.nextLine(); while(email.length()<3);  
             db.addStudent(id,name,telefon,email);
         break; 
         case 3:
             System.out.println("\n Input number of Course:");  
             id=sc.nextInt(); 
             System.out.println(" Input number of stud.book:");  
             id1=sc.nextInt(); 
             db.addInCourseV(id1,id);
         break; 
         case 4:
             System.out.println("\n Input name of Prof:");  
             do name=sc.nextLine(); while(name.length()<3);       
             System.out.println(" Input telefon of Prof:"); 
             do telefon=sc.nextLine(); while(telefon.length()<3);   
             System.out.println(" Input address of Prof:"); 
             do email=sc.nextLine(); while(email.length()<3);  
             System.out.println(" Input salary of Prof:"); 
             cost=(float)sc.nextDouble();
             db.addProf(name,email,telefon, cost);
         break; 
         case 5:
             System.out.println("\n Input course_id(number of Course):");  
             id=sc.nextInt(); 
             System.out.println(" Input prof_id:");  
             id1=sc.nextInt(); 
             db.addProfRel(id1,id);
         break; 
      }
   }
   static void callClassMethods(int iL,int n,Scanner sc){
      int chois,id,id1,stat;
      switch (iL) {
         case 1:
           System.out.println("\n You can logStudentInThis Cours - 1");
           System.out.println(" You can removeStudentFromThis Cours - 2");
           System.out.println(" You can do nothing - 3");
           chois=sc.nextInt(); 
           if(chois==1||chois==2){
             System.out.println("\n Input number of stud.book or 0 to break:");  
             id1=sc.nextInt(); 
             if(id1>0&&chois==1){
                 id=db.courses.get(n).course_id;         
                 db.addInCourseV(id1,id);
             }else if(id1>0&&chois==2){
                System.out.println("\n Status from removeStudent:\nCours is ENDED - 1");
                System.out.println(" Cours is INTERRUPTED - 2");
                stat=sc.nextInt(); 
                if(stat==1||stat==2)
                   db.courses.get(n).removeStudentFromThis(id1,stat); 
             }
           } 
         break; 
         case 2:
           System.out.println("\n You can call Courses loged by this Student - 1");
           System.out.println(" You can call Courses cann be loged by this Student- 2");
           System.out.println(" You also can do nothing - 3");
           chois=sc.nextInt(); 
           if(chois==1||chois==2){
              List<Integer> course_ids;
              if(chois==1)
                 course_ids=db.students.get(n).getLoginCours();
              else
                 course_ids=db.students.get(n).canLogIntoCours();
              for(int i=0;i<course_ids.size();i++)
                 for(int j=0;j<db.courses.size();j++)
                    if(db.courses.get(j).course_id==course_ids.get(i)){
                       System.out.println(db.courses.get(j).toString());
                       break;
                    } 
           }
         break; 
         case 3:
           System.out.println("\nYou can add new Score - 1");
           System.out.println("You can get Average or Final Score- 2");
           System.out.println("You also can do nothing - 3");
           chois=sc.nextInt(); 
           if(chois==1){
              System.out.println("\n Input new Score (2..5):"); 
              chois=sc.nextInt(); 
              db.cours_v.get(n).addScore(chois);
           }else if(chois==2){
             int f_s=db.cours_v.get(n).getFinalScore();
             System.out.println("\nAverage Score="+String.valueOf(db.cours_v.get(n).getAverageScore())+" Final Score="+String.valueOf(f_s));
           }
         break;  
      }
   }

}

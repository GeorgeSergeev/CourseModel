/* Класс содержит таблицы сущностей и реляционные таблицы БД в виде набора списков,а также CRUD методы для работы с ними */

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
 
public class CourseDBaseCl implements Serializable { 

   List<StudentCl> students=new ArrayList<>();
   List<CourseCl> courses=new ArrayList<>();
   List<CourseViewerCl> cours_v=new ArrayList<>();
   List<ProfCl> proffs=new ArrayList<>();
   List<ProfCoursRelCl> prof_rel=new ArrayList<>();
   
  
   public void addStudent(int std_id,String name,String telefon,String email) { 
      StudentCl st=new StudentCl(std_id,name,telefon,email);
      students.add(st);
   }
   public void delStudent(int std_id){
      for(int i=0;i<students.size();i++)
         if(students.get(i).studbook_id ==std_id){
            students.remove(i);  
            break;
         }
      for(int i=0;i<cours_v.size();i++)
         if(cours_v.get(i).studbook_id ==std_id)
            cours_v.remove(i);   
   }
  
   public void addCourse(int course_id,String name, float cost){
      CourseCl crs=new CourseCl(course_id,name,cost);
      courses.add(crs);
   }
   public void delCourse(int crse_id){
      for(int i=0;i<courses.size();i++)
         if(courses.get(i).course_id==crse_id){
            courses.remove(i);
            break;
         }  
      for(int i=0;i<cours_v.size();i++)
         if(cours_v.get(i).course_id==crse_id)
            cours_v.remove(i);     
   } 
   public void addInCourseV(int std_id,int course_id){
      CourseViewerCl crs_v=new CourseViewerCl(course_id,std_id);
      cours_v.add(crs_v);
   }
   public void delInCourseV(int std_id,int crse_id){
      for(int i=0;i<cours_v.size();i++)
         if(cours_v.get(i).studbook_id ==std_id&&cours_v.get(i).course_id==crse_id){
            cours_v.remove(i);
            break;
         }            
   }
  
   public void addProf(String name, String address, String phone, float salary){
     // if inputs is incorrect throw an exception 
        if ((name == null) || (address == null) || (phone == null) || (salary < 0) || 
               "".equals(name) || "".equals(address) || "".equals(phone)) { 
           throw new RuntimeException("Incorrect professor inputs..."); 
        } 
     int prof_id=(proffs.size()==0)?1:proffs.get(proffs.size()-1).prof_id+1;
     ProfCl prf=new ProfCl(prof_id,name,address,phone,salary);
     proffs.add(prf); 
   }
   public void delProf(int prof_id){
      for(int i=0;i<proffs.size();i++)
         if(proffs.get(i).prof_id==prof_id){
            proffs.remove(i);
            break;
         }  
      for(int i=0;i<prof_rel.size();i++)
         if(prof_rel.get(i).prof_id==prof_id)
            prof_rel.remove(i);     
   }
   public void addProfRel(int prof_id,int course_id){
      ProfCoursRelCl prel=new ProfCoursRelCl(prof_id,course_id);
      prof_rel.add(prel); 
   }
   public void delProfRel(int prof_id,int course_id){
      for(int i=0;i<prof_rel.size();i++)
         if(prof_rel.get(i).prof_id==prof_id||prof_rel.get(i).course_id==course_id){
            prof_rel.remove(i);  
            break;
         }
   }

   public void readList(int iL,int n){
       switch (iL) {
         case 1:
            readList(courses,n);
         break;
         case 2:
            readList(students,n);
         break;
         case 3:
            readList(cours_v,n);
         break;
         case 4:
            readList(proffs,n);
         break;
         case 5:
            readList(prof_rel,n);
         break;   
      }
   }
   void readList(List lst,int n){
      if(n<0)  // n=-1 - read full list
         for(int i=0;i<lst.size();i++)
            System.out.println(String.valueOf(i+1)+" "+lst.get(i).toString());
      else  
          System.out.println(String.valueOf(n+1)+" "+lst.get(n).toString());
   }
   
   public void delInList(int iL,int n){
     switch (iL) {
         case 1:
            delCourse(courses.get(n).course_id);
         break;
         case 2:
            delStudent(students.get(n).studbook_id);
         break;
         case 3:
            delInCourseV(cours_v.get(n).studbook_id,cours_v.get(n).course_id);
         break;
         case 4:
            delProf(proffs.get(n).prof_id);
         break;
         case 5:
            delProfRel(prof_rel.get(n).prof_id,prof_rel.get(n).course_id);
         break;
      }
   }   
/*   
   String fn(int n){
    return file_name.substring(0,file_name.indexOf(".json")-1)+String.valueOf(n)+".json";
   } */
}  

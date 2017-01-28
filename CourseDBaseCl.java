/*      */
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.io.File;
import java.io.IOException; 
 
public class CourseDBaseCl implements Serializable { 

   List<StudentCl> students=new ArrayList<>();
   List<CourseCl> courses=new ArrayList<>();
   List<CourseViewerCl> cours_v=new ArrayList<>();
   List<ProfCl> proffs=new ArrayList<>();
   List<ProfCoursRelCl> prof_rel=new ArrayList<>();
   String file_name;

   
  
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
      if(n<0)  // n=-1 read full list
         for(int i=0;i<lst.size();i++)
            System.out.println("\n"+String.valueOf(i+1)+" "+lst.get(i).toString());
      else  
          System.out.println("\n"+String.valueOf(n+1)+" "+lst.get(n).toString());
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
   public void writeToJson(){
     try{
       GsonBuildCl.writeToJson(students, fn(1)); 
       GsonBuildCl.writeToJson(courses, fn(2)); 
       GsonBuildCl.writeToJson(cours_v, fn(3)); 
       GsonBuildCl.writeToJson(proffs, fn(4)); 
       GsonBuildCl.writeToJson(prof_rel, fn(5)); 
     }catch(IOException e){
       e.printStackTrace(); 
     }     
   }
   void readFromJson(){
      try{
        students=(ArrayList<StudentCl>)GsonBuildCl.readFromJson(students.getClass(), fn(1)); 
        courses=(ArrayList<CourseCl>)GsonBuildCl.readFromJson(courses.getClass(), fn(2)); 
        cours_v=(ArrayList<CourseViewerCl>)GsonBuildCl.readFromJson(cours_v.getClass(), fn(3)); 
        proffs=(ArrayList<ProfCl>)GsonBuildCl.readFromJson(proffs.getClass(), fn(4));
        prof_rel=(ArrayList<ProfCoursRelCl>)GsonBuildCl.readFromJson(prof_rel.getClass(), fn(5));
     }catch(IOException e){
       e.printStackTrace(); 
     }  
   }
   
   String fn(int n){
    return file_name.substring(0,file_name.indexOf(".json")-1)+String.valueOf(n)+".json";
   }
}  

/*              */
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
 
public class StudentCl implements Serializable { 

   int studbook_id;   // zachetka_numb
   String name; 
   String adres; 
   String telefon; 
   String email; 
   float aver_grade; //sum off all scores on ACTIVE,ENDED and INTERRUPTED courses/its num
 
   public StudentCl( int studbook_id) { 
        this.studbook_id = studbook_id; 
        
   } 
   public StudentCl( int studbook_id,String name,String telefon,String email) { 
        
        this.studbook_id = studbook_id; 
        this.telefon = telefon;        
        this.name = name; 
        this.email = email; 
   } 
   
   public int getStudbook_id() { 
       return studbook_id; 
   } 
   public void setStudbook_id(int studbook_id) { 
       this.studbook_id = studbook_id; 
   } 
  // получит список залогиненних курсов(ACTIVE, ENDED, INTERRUPTED)
   public List<Integer>  getLoginCours() { 
      List<Integer> course_ids= new ArrayList<>();
      CourseDBaseCl db=GsonBuildCl.db;
      for(int i=0;i<db.cours_v.size();i++){
        if(db.cours_v.get(i).studbook_id == this.studbook_id)
               course_ids.add(db.cours_v.get(i).course_id); 
      }
      return course_ids;
    } 
   // получит список курсов куда можна залогинится :)
   public List<Integer> canLogIntoCours() { 
      List<Integer> new_course_ids= new ArrayList<>();
      List<Integer> log_course_ids = getLoginCours(); 
      CourseDBaseCl db=GsonBuildCl.db;
      for(int j,i=0;i<db.courses.size();i++){
         for(j=0;j<log_course_ids.size();j++)
            if(db.courses.get(i).getCourse_id() == log_course_ids.get(j))
                break;
         if(j==log_course_ids.size()) //  course_id not in log_course_ids
               new_course_ids.add(db.courses.get(i).getCourse_id()); 
      }
      return new_course_ids;
    } 
 
    @Override 
    public String toString() { 
     return  "Student {name = " + name + "; phone = " + telefon + "; email = " + email + "; studbook_id = " + studbook_id + "; aver_grade = " + aver_grade; 
    } 
}

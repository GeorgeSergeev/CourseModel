/*    */
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
 
public class CourseCl implements Serializable { 

   public final static int DEL_STUD=3; 

   int course_id; 
   String name; 
   float cost; 
 
   public CourseCl( int course_id,String name, float cost) { 
       
        this.course_id = course_id; 
        this.name = name; 
        this.cost = cost; 
   } 

  
   public int getCourse_id() { 
       return course_id; 
   } 
   public void setCourse_id(int course_id) { 
       this.course_id = course_id; 
   }

   public void logStudentInThis(int std_id) { 
      CourseDBaseCl db=GsonBuildCl.db;
      db.addInCourseV(std_id,course_id);
   } 
 
   public void removeStudentFromThis(int std_id,int status) { 
      CourseDBaseCl db=GsonBuildCl.db;
      if (status==CourseViewerCl.ENDED||status==CourseViewerCl.INTERRUPTED) 
         for(int i=0;i<db.cours_v.size();i++)
            if(db.cours_v.get(i).studbook_id ==std_id&&db.cours_v.get(i).course_id==course_id){
               db.cours_v.get(i).status=status; 
               break;
            }
      else if(status==DEL_STUD) 
         db.delStudent(std_id);
      else
         System.out.println("Value of status is not available!");  
   } 
 

 
    @Override 
    public String toString() { 
        return  "CourseCl {course_id = " + course_id + ";name = " + name + 
               "; cost = " + cost + "}"; 
    } 
}


/*  Класс отношений сущ. проффа и курса. */
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
 
public class ProfCoursRelCl implements Serializable {  // ProfCl
   int prof_id;
   int course_id;

   public ProfCoursRelCl( int prof_id,int course_id){
        
        this.prof_id = prof_id;
        this.course_id = course_id;
   }
   @Override 
   public String toString() { 
        return  "ProfCoursRelCl {course_id="+course_id+";prof_id="+ prof_id +  "}"; 
    } 
}

/* Класс отношений сущ. студента и курса. Также содержит текущие отметки и финальную в списке scores    */
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
 
public class CourseViewerCl implements Serializable {  // CourseViewerCl

    final static int MIN_SCR=2,MAX_SCR=5, NUM_FIN_SCR=10; 
    public final static int ACTIVE=0, ENDED=1, INTERRUPTED=2; 

    int status=ACTIVE; 
    int course_id; 
    int studbook_id; 
    
    List<Integer> scores = new ArrayList<>(); 
    
    public CourseViewerCl(int course_id,int studbook_id) { 
        this.course_id = course_id; 
        this.studbook_id = studbook_id; 
    } 
 
    public void addScore(int score) { 
       if ((scores.size() <= NUM_FIN_SCR) && (score >= MIN_SCR && score <= MAX_SCR)) { 
           scores.add(score); 
           setAverGrade();
       } 
    } 
    
    public float getAverageScore() { 
        
      int sum = 0;   // sum of scores 

      for (int i = 0; i < scores.size(); i++) 
            if (i != NUM_FIN_SCR) 
                sum += scores.get(i); 
             
 
        if (scores.size() == 0)    // if we have no scores 
            return (float)0.0; 
        else                  // if we have some scores 
            return  (float)(sum * 1.0/scores.size()); 
    } 
 
    public int getFinalScore() { 
        
        if (scores.size() < NUM_FIN_SCR) {   // if we have no special score 
            return 0; 
        } else {                    // if we have special score 
            return  scores.get(NUM_FIN_SCR); 
        } 
    } 
    void setAverGrade(){  // in class Stud
      float sum=(float)0.0;
      int count=0;
      CourseDBaseCl db=GsonBuildCl.db;
      for(int i=0;i<db.cours_v.size();i++)
        if(db.cours_v.get(i).studbook_id == this.studbook_id){
              sum += db.cours_v.get(i).getAverageScore(); 
              count++;
        } 
      for(int i=0;i<db.students.size();i++)
        if(db.students.get(i).studbook_id == this.studbook_id){  
             db.students.get(i).aver_grade=sum/count;
             break;
        }
    }
 
    @Override 
    public String toString() { 
        return  "CourseViewerCl {course_id="+course_id+";studbook_id="+ studbook_id+";scores = " +  scores.toString() +  "}"; 
    } 
}

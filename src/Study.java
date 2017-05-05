

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TheEndl on 01.05.2017.
 */



public class Study {

    ArrayList<Integer> evaluations;
    float middle;
    Study (float middle, ArrayList<Integer> evaluations)
    {
       this.middle = middle;
       this.evaluations = evaluations;

    }

    public void initMiddleGrade(Student student)
    {
        float sum = 0;
        if (this.evaluations.size() > 0 )
        {
            for (int i = 0; i < this.evaluations.size(); i++)
            {
                sum = sum + this.evaluations.get(i);
            }
            middle = sum / this.evaluations.size();

                student.setStudyLevel(middle);

        }
        else System.out.println("Студент" + student + " еще не получал оценок");
    }

   // Set new evaluate for student
    public void setMarkForStudent(int evaluation)
    {
        this.evaluations.add(evaluation);
    }


}

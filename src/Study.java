

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TheEndl on 01.05.2017.
 */



public class Study {

    public float getMiddleGrade(Student student)
    {
        float sum = 0;
        float middle = 0;
        for (int i = 0; i < student.evaluations.size(); i++)
        {
            sum = sum + student.evaluations.get(i);
        }
        middle = sum / student.evaluations.size();
        if (middle > 0)
            student.setStudyLevel(middle);
        else System.out.println("Студент" + student + " еще не получал оценок");
        return middle;
    }

   // Set new evaluate for student
    public void setMarkForStudent(Student student, int evaluation)
    {
        student.evaluations.add(evaluation);
    }


}

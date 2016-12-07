import java.util.List;

/**
 * Created by Chuprov on 06.12.2016.
 */
public class TaskUtils {
    public static boolean canUpdateWithObject(Object object, List<?> objects) {
        return (object != null) && !objects.contains(object);
    }

    public static boolean isProfessorValid(String name, String address, String phone, float salary) {
        return (name == null) || (address == null) || (phone == null) || (salary < 0);
    }

    public static boolean isStudentValid(String name, String address, String phone, String email) {
        return (name == null) || (address == null) || (phone == null) || (email == null);
    }

    public static boolean isCourseValid(String name, int courseId, float costs) {
        return (name == null) || (courseId < 1) || (costs < 0);
    }

    public static boolean isValidMark(int mark) {
        return mark > 1 && mark < 6;
    }
}

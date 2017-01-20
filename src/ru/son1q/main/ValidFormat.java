package ru.son1q.main;

import java.util.List;

public abstract class ValidFormat {

    public static boolean isStudentValid(String name, String address, String numberPhone, String email) {
        return (name == null) || (address == null) || (numberPhone == null) || (email == null);
    }

    public static boolean isCourseValid(String name, float price) {
        return (name == null) || (price <= 0); // price <= 0 - курсы могут быть бесплатными.
    }

    public static boolean isValidEvaluation(int evaluation) {
        return evaluation > 1 && evaluation < 6;
    }
    
    public static boolean isProfessorValid(String name, String address, String phoneNumber, float salary) {
        return (name == null) || (address == null) || (phoneNumber == null) || (salary < 0);
    }
    
	public static boolean checkContaints(Object object, List<?> objects) {
        return (object != null) && !objects.contains(object);
    }
}

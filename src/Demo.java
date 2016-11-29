public class Demo {
    public static void main(String[] args) {
        Course math = new Course("Математика", 1, 10f);
        Course chemestry = new Course("Химия", 2, 20f);
        Professor professor = new Professor("Преображенский", "Москва", "+79152674584", 100f);
        Student petrov = new Student("Петров", "Москва", "+79780512678", "petrov@yandex.ru");
        Student ivanov = new Student("Иванов", "Санкт-Петербург", "+79780512677", "ivanov@yandex.ru");
        petrov.addStudent(math);
        petrov.addStudent(chemestry);
        ivanov.addStudent(math);
        professor.addCourse(math);
        ivanov.getPassingCourse(math).addMark(3);
        ivanov.getPassingCourse(math).addMark(4);
        ivanov.getPassingCourse(math).addMark(2);
        ivanov.getPassingCourse(math).addMark(5);
        ivanov.getPassingCourse(math).addMark(3);
        petrov.getPassingCourse(math).addMark(3);
        petrov.getPassingCourse(math).addMark(5);
        petrov.getPassingCourse(math).addMark(2);
        petrov.getPassingCourse(math).addMark(3);
        Json json= new Json();
        json.writeJson("ivanov.json",ivanov);
        System.out.println(json.readJson("ivanov.json"));




    }
}





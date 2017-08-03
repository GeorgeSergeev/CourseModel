package Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Professor;

public class CollectionProfessorList{
    private static ObservableList<Professor> professorList = FXCollections.observableArrayList();

    public static void add(Professor professor) throws Exception{
        for (Professor professor1:
                professorList) {
            if (professor1.getName().equals(professor.getName())){
                //дописать
                throw new Exception();
            }
        }

        professorList.add(professor);
    }

    public static void delete(Professor professor){
        professorList.remove(professor);
    }

    public static void testDate(){
        try {
            add(new Professor("Бондарев В.н.", "Севастополь", "+79785557799", 100000f));
            add(new Professor("Забаштанский А.К.", "Севастополь", "+79785557799", 100000f));
            add(new Professor("Смирнова Н.Б.", "Севастополь", "+79785557799", 100000f));
            add(new Professor("Якименко О.А.", "Севастополь", "+79785557799", 100000f));
            add(new Professor("Чушка Л.Л.", "Севастополь", "+79785557799", 100000f));
        }
        catch (Exception e){}
    }

    public static ObservableList<Professor> getProfessorList(){
        return  professorList;
    }

    public static Professor getProfessorByName(String name){
        for (Professor professor :
                professorList) {
            if (professor.getName().equals(name)){
                return professor;
            }
        }
        return null;
    }
}

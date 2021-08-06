package javafxComponents.Controllers;

import Collections.CollectionCourseList;
import Collections.CollectionCoursePassingList;
import Collections.CollectionStudentList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Course;
import model.CoursePassing;
import model.Student;

public class AverageRatingController {

    @FXML
    ComboBox studentComboBox;
    @FXML
    ComboBox courseComboBox;


    @FXML
    private void initialize(){
        addListener();
        ObservableList<String> options = FXCollections.observableArrayList("не выбран");
        ObservableList<String> options1 = FXCollections.observableArrayList("не выбран");
        ObservableList<Student> studentsWithUnfinishedCourses = CollectionStudentList.getStudentsWithUnfinishedCourses();
        for (Student student:
                studentsWithUnfinishedCourses) {
            options.add(student.getNum() + ","+student.getName());
        }
        studentComboBox.setItems(options);
        studentComboBox.setValue("не выбран");
        courseComboBox.setItems(options1);
        courseComboBox.setValue("не выбран");
    }

    public void actionAdd(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("Средний балл")){
            try{
                Student student = CollectionStudentList.getStudentByNumAndName(studentComboBox.getValue().toString());
                Course course = CollectionCourseList.getCourseByNumAndName(courseComboBox.getValue().toString());
                CoursePassing coursePassing = CollectionCoursePassingList.getCoursePassingByStudentAndCourse(student, course);
                coursePassing.getAverageRating();
            }
            catch (Exception e){
            }
        }
    }

    @FXML
    private void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    private void addListener(){

        studentComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                try{
                    ObservableList<Course> unfinishedCourses = CollectionCourseList.getUnfinishedCoursesWithMarks(t1);
                    ObservableList<String> options = FXCollections.observableArrayList("не выбран");
                    for (Course course:
                            unfinishedCourses) {
                        options.add(course.getNum() + "," + course.getName());
                    }
                    courseComboBox.setItems(options);
                    courseComboBox.setValue("не выбран");
                }
                catch (Exception e){}
            }
        });
    }
}


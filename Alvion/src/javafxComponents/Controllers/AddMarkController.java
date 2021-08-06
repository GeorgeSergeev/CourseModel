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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Course;
import model.CoursePassing;
import model.Student;

public class AddMarkController {

    @FXML
    ComboBox studentComboBox;
    @FXML
    ComboBox courseComboBox;
    @FXML
    TextField newMark;
    @FXML
    Label labelStudentAndCourse;
    @FXML
    Label labelMarks;

    @FXML
    private void initialize(){
        labelStudentAndCourse.setText("");
        labelMarks.setText("");
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
        if (button.getText().equals("Добавить оценку")){
            try{
                Student student = CollectionStudentList.getStudentByNumAndName(studentComboBox.getValue().toString());
                Course course = CollectionCourseList.getCourseByNumAndName(courseComboBox.getValue().toString());
                CoursePassing coursePassing = CollectionCoursePassingList.getCoursePassingByStudentAndCourse(student, course);
                coursePassing.getRating().add(Integer.parseInt(newMark.getText()));
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
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
                    ObservableList<Course> unfinishedCourses = CollectionCourseList.getUnfinishedCoursesByStudentsNumAndName(t1);
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

        courseComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                Student student =CollectionStudentList.getStudentByNumAndName(studentComboBox.getValue().toString());
                Course course = CollectionCourseList.getCourseByNumAndName(t1);
                if (student!= null && course!=null){
                    CoursePassing coursePassing = CollectionCoursePassingList.
                            getCoursePassingByStudentAndCourse(student,course);
                    if (coursePassing.getRating().size()==0){
                        labelStudentAndCourse.setText("Студент " + student.getName()
                                                + " на курсе " + course.getName() + " пока не получал оценок!");
                        labelMarks.setText("");
                    }
                    else{
                        labelStudentAndCourse.setText("Студент "+student.getName() + " на курсе "+
                                                        course.getName() + " имеет следующие оценки:");
                        String marks ="";
                        for (int mark:
                             coursePassing.getRating()) {
                            marks = marks + " " + mark;
                        }
                        labelMarks.setText(marks);
                    }
                }
            }
        });




        newMark.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length()>3){
                    newMark.setText(newMark.getText().substring(0,3));
                }
            }
        });

    }
}

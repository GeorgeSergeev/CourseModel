package javafxComponents.Controllers;

import Collections.CollectionCourseList;
import Collections.CollectionProfessorList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Course;
import model.Professor;

import java.util.EmptyStackException;

public class AddCourseController {

    @FXML
    private TextField numCourse;

    @FXML
    private TextField nameCourse;

    @FXML
    private TextField costCourse;

    @FXML
    private ComboBox professorComboBox;

    @FXML
    private Button defaultButton;

    private Course course;

    @FXML
    private void initialize(){
        addTextLimiter(numCourse,3);
        addTextLimiter(nameCourse,25);
        addTextLimiter(costCourse,7);

        ObservableList<String> options = FXCollections.observableArrayList("не требуется");
        for (Professor professor:
             CollectionProfessorList.getProfessorList()) {
            options.add(professor.getName());
        }
        professorComboBox.setItems(options);
        professorComboBox.setValue("не требуется");

    }

    public void actionAdd(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("Добавить")){
            try{

                if (nameCourse.getText().equals("")||
                        numCourse.getText().equals("")||
                        costCourse.getText().equals("")){
                    throw new EmptyStackException();
                }

                if (Integer.parseInt(numCourse.getText())<=0||
                        Float.parseFloat(costCourse.getText())<=0){
                    throw new Exception();
                }

                Course course = new Course(
                        nameCourse.getText(),
                        Integer.parseInt(numCourse.getText()),
                        Float.parseFloat(costCourse.getText()),
                        CollectionProfessorList.getProfessorByName(professorComboBox.getValue().toString())
                );
                CollectionCourseList.add(course);
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
            catch (EmptyStackException e){
                DialogManager.showErrorDialog("Ошибка при добавлении",
                        "Все поля должны быть заполнены!");
            }
            catch (Exception e){
                DialogManager.showErrorDialog("Ошибка при добавлении",
                        "Проверьте корректность введённых данных!");
            }
        }
        else{
            try{
                saveChanges();
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

    void setCorseValues(Course course){
            this.course = course;
            numCourse.setText(String.valueOf(course.getNum()));
            nameCourse.setText(course.getName());
            costCourse.setText(String.valueOf(course.getCost()));
            defaultButton.setText("Редактировать");
        try{
            professorComboBox.setValue(course.getProfessor().getName());
        }
        catch (NullPointerException e){
            professorComboBox.setValue("не требуется");
        }
    }

    private void saveChanges() throws Exception {
        try {

            if (nameCourse.getText().equals("") ||
                    numCourse.getText().equals("") ||
                    costCourse.getText().equals("")) {
                throw new EmptyStackException();
            }

            if (Integer.parseInt(numCourse.getText())<=0||
                    Float.parseFloat(costCourse.getText())<=0){
                throw new Exception();
            }

            for (Course findCourse :
                    CollectionCourseList.getCourseList()) {
                if (findCourse.getNum() == Integer.parseInt(numCourse.getText())
                        && !findCourse.equals(course)) {
                    throw new Exception();
                }
            }

            course.setName(nameCourse.getText());
            course.setNum(Integer.parseInt(numCourse.getText()));
            course.setCost(Float.parseFloat(costCourse.getText()));
            course.setProfessor(CollectionProfessorList.getProfessorByName(professorComboBox.getValue().toString()));
        }
        catch (EmptyStackException e){
            DialogManager.showErrorDialog("Ошибка при добавлении",
                    "Все поля должны быть заполнены!");
            throw new Exception();
        }
        catch (Exception e){
            DialogManager.showErrorDialog("Ошибка при добавлении",
                    "Проверьте корректность введённых данных!");
            throw new Exception();
        }
    }
    private void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}

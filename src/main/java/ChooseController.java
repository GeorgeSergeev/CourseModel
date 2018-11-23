import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import model.Course;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseController implements Initializable {

    protected Controller controller;

    private Course selectedCourse;

    @FXML
    ChoiceBox<Course> courseChoosen;

    @FXML
    Button createBtn;

    @FXML
    VBox globParent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createBtn.setDisable(true);
        courseChoosen.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            courseChoosen.getSelectionModel().select((int)newValue);
            if (courseChoosen.getSelectionModel().getSelectedItem() != null) {
                System.out.println(courseChoosen.getSelectionModel().getSelectedItem().getName());
                selectedCourse = courseChoosen.getSelectionModel().getSelectedItem();
                createBtn.setDisable(false);
            } else {
                createBtn.setDisable(true);
            }
        });
    }

    public void fillList() {
        courseChoosen.getItems().clear();
        for (Course course :controller.getCourses()) {
            courseChoosen.getItems().add(course);
        }
    }

    public void add(ActionEvent actionEvent) {
        controller.setSelectedCourse(selectedCourse);
        close(actionEvent);
    }

    public void close(ActionEvent actionEvent) {
        globParent.getScene().getWindow().hide();
    }

}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Course;
import model.Professor;
import model.Student;
import util.Math;

import java.net.URL;
import java.util.ResourceBundle;

public class NewObjectController implements Initializable {

    protected Controller controller;

    protected int selectedObject; // 1 - student, 2 - professor, 3 - course

    private boolean nameTyped = false;
    private boolean addressTyped = false;
    private boolean phoneTyped = false;
    private boolean emailTyped = false;
    private boolean numberTyped = false;

    @FXML
    TextField objectName;

    @FXML
    TextField objectAddress;

    @FXML
    TextField objectPhone;

    @FXML
    TextField objectEmail;

    @FXML
    TextField someNumberForObject;

    @FXML
    Button createBtn;

    @FXML
    VBox globParent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createBtn.setDisable(true);
        objectName.setOnKeyReleased(value -> {
            nameTyped = checkTextField(objectName);
            setButtonActive();
        });
        objectAddress.setOnKeyReleased(value -> {
            addressTyped = checkTextField(objectAddress);
            setButtonActive();
        });
        objectEmail.setOnKeyReleased(value -> {
            emailTyped = checkTextField(objectEmail);
            setButtonActive();
        });
        objectPhone.setOnKeyReleased(value -> {
            phoneTyped = checkTextField(objectPhone);
            setButtonActive();
        });
        someNumberForObject.setOnKeyReleased(value -> {
            numberTyped = checkTextField(someNumberForObject) && Math.isInteger(someNumberForObject.getText());
            setButtonActive();
        });
    }

    protected void configureView(int selectedObject) {
        this.selectedObject = selectedObject;
        switch (selectedObject) {
            case 1:
                objectName.setPromptText("Student name");
                objectAddress.setPromptText("Student Address");
                objectPhone.setPromptText("Student Phone");
                objectEmail.setPromptText("Student Email");
                someNumberForObject.setPromptText("Grade book number");
                break;
            case 2:
                objectName.setPromptText("Professor name");
                objectAddress.setPromptText("Professor Address");
                objectPhone.setPromptText("Professor Phone");
                someNumberForObject.setPromptText("Professor salary");
                objectEmail.setVisible(false);
                objectEmail.setManaged(false);
                break;
            case 3:
                objectName.setPromptText("Course name");
                someNumberForObject.setPromptText("Course cost");
                objectAddress.setVisible(false);
                objectAddress.setManaged(false);
                objectPhone.setVisible(false);
                objectPhone.setManaged(false);
                objectEmail.setVisible(false);
                objectEmail.setManaged(false);
                break;
        }
    }

    private boolean checkTextField(TextField textField) {
        return textField.getText().length() > 0;
    }

    private void setButtonActive() {
        if ((selectedObject == 1 && nameTyped && addressTyped && phoneTyped && emailTyped && numberTyped) ||
                (selectedObject == 2 && nameTyped && addressTyped && phoneTyped && numberTyped) ||
                (selectedObject == 3 && nameTyped && numberTyped)) {
            createBtn.setDisable(false);
        } else {
            createBtn.setDisable(true);
        }
    }

    public void close(ActionEvent actionEvent) {
        globParent.getScene().getWindow().hide();
    }

    public void create(ActionEvent actionEvent) {
        switch (selectedObject) {
            case 1:
                controller.getStudentService()
                        .addStudent(
                                new Student(
                                        objectName.getText(),
                                        objectAddress.getText(),
                                        objectPhone.getText(),
                                        objectEmail.getText(),
                                        Integer.parseInt(someNumberForObject.getText())));
                controller.refreshStudentsList();
                controller.showAlert("Student added!");
                break;
            case 2:
                controller.getProfessorService()
                        .addProfessor(
                                new Professor(
                                        objectName.getText(),
                                        objectAddress.getText(),
                                        objectPhone.getText(),
                                        Float.parseFloat(someNumberForObject.getText()))
                        );
                controller.refreshProfessorsList();
                controller.showAlert("Professor added!");
                break;
            case 3:
                controller.getCourseService()
                        .addCourse(
                                new Course(
                                        objectName.getText(),
                                        Float.parseFloat(someNumberForObject.getText()))
                        );
                controller.refreshCourseList();
                controller.showAlert("Course added!");
                break;
        }
        close(actionEvent);
    }
}

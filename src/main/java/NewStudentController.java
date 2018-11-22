import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Student;
import util.Math;

import java.net.URL;
import java.util.ResourceBundle;

public class NewStudentController implements Initializable {

    protected Controller controller;

    private boolean nameTyped = false;
    private boolean addressTyped = false;
    private boolean phoneTyped = false;
    private boolean emailTyped = false;
    private boolean gbnumTyped = false;

    @FXML
    TextField studentName;

    @FXML
    TextField studentAddress;

    @FXML
    TextField studentPhone;

    @FXML
    TextField studentEmail;

    @FXML
    TextField studentGBNum;

    @FXML
    Button createBtn;

    @FXML
    VBox globParent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createBtn.setDisable(true);
        studentName.setOnKeyReleased(value -> {
            nameTyped = checkTextField(studentName);
            setButtonActive();
        });
        studentAddress.setOnKeyReleased(value -> {
            addressTyped = checkTextField(studentAddress);
            setButtonActive();
        });
        studentEmail.setOnKeyReleased(value -> {
            emailTyped = checkTextField(studentEmail);
            setButtonActive();
        });
        studentPhone.setOnKeyReleased(value -> {
            phoneTyped = checkTextField(studentPhone);
            setButtonActive();
        });
        studentGBNum.setOnKeyReleased(value -> {
            gbnumTyped = checkTextField(studentGBNum) && Math.isInteger(studentGBNum.getText());
            setButtonActive();
        });
    }

    private boolean checkTextField(TextField textField) {
        return textField.getText().length() > 0;
    }

    private void setButtonActive() {
        if (nameTyped && addressTyped && phoneTyped && emailTyped && gbnumTyped) {
            createBtn.setDisable(false);
        } else {
            createBtn.setDisable(true);
        }
    }

    public void close(ActionEvent actionEvent) {
        globParent.getScene().getWindow().hide();
    }

    public void create(ActionEvent actionEvent) {
        controller.getStudentService()
                .addStudent(
                        new Student(studentName.getText(),
                                studentAddress.getText(),
                                studentPhone.getText(),
                                studentEmail.getText(),
                                Integer.parseInt(studentGBNum.getText())));
        controller.refreshStudentsList();
        controller.showAlert("Student added!");
        close(actionEvent);
    }
}

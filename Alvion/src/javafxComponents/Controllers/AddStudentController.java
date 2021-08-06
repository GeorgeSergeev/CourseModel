package javafxComponents.Controllers;

import Collections.CollectionStudentList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Student;

import java.util.EmptyStackException;

public class AddStudentController {

    @FXML
    private TextField nameStudent;

    @FXML
    private TextField adresStudent;

    @FXML
    private TextField phoneStudent;

    @FXML
    private TextField emailStudent;

    @FXML
    private TextField numStudent;

    @FXML
    private Button defaultButton;


    private Student student;

    @FXML
    private void initialize(){
        addTextLimiter(nameStudent,20);
        addTextLimiter(adresStudent,25);
        addTextLimiter(emailStudent,25);
        addTextLimiter(phoneStudent,12);
        addTextLimiter(numStudent,10);
    }

    public void actionAdd(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("Добавить")){
            try{
                if (nameStudent.getText().equals("")||
                        adresStudent.getText().equals("")||
                        phoneStudent.getText().equals("")||
                        emailStudent.getText().equals("")||
                        numStudent.getText().equals("")){
                    throw new EmptyStackException();
                }

                if (Integer.parseInt(numStudent.getText())<=0){
                    throw new Exception();
                }

                Student student = new Student(
                        nameStudent.getText(),
                        adresStudent.getText(),
                        phoneStudent.getText(),
                        emailStudent.getText(),
                        Integer.parseInt(numStudent.getText())
                );
                CollectionStudentList.add(student);
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
            catch (EmptyStackException e){
                DialogManager.showErrorDialog("Ошибка при создании",
                        "Все поля должны быть заполнены!");
            }
            catch (Exception e){
                DialogManager.showErrorDialog("Ошибка при создании",
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
            catch (EmptyStackException e){
                DialogManager.showErrorDialog("Ошибка при редактировании",
                        "Все поля должны быть заполнены!!");
            }
            catch (Exception e){
                DialogManager.showErrorDialog("Ошибка при редактировании",
                        "Проверьте корректность введённых данных!");
            }
        }
    }

    @FXML
    private void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    void setStudentValues(Student student){
        this.student = student;
        nameStudent.setText(student.getName());
        adresStudent.setText(student.getAdress());
        phoneStudent.setText(student.getPhone());
        emailStudent.setText(student.getEmail());
        numStudent.setText(String.valueOf(student.getNum()));
        defaultButton.setText("Редактировать");
    }

    private void saveChanges() throws Exception{

        if (nameStudent.getText().equals("")||
                adresStudent.getText().equals("")||
                phoneStudent.getText().equals("")||
                emailStudent.getText().equals("")||
                numStudent.getText().equals("")){
            throw new EmptyStackException();
        }

        if (Integer.parseInt(numStudent.getText())<=0){
            throw new Exception();
        }

        for (Student findStudent:
             CollectionStudentList.getStudentList()) {
            if (findStudent.getNum() == Integer.parseInt(numStudent.getText())
                    && !findStudent.equals(student)){
                    throw new Exception();
                }
            }

        student.setName(nameStudent.getText());
        student.setEmail(emailStudent.getText());
        student.setAdress(adresStudent.getText());
        student.setPhone(phoneStudent.getText());
        student.setNum(Integer.parseInt(numStudent.getText()));
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

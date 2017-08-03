package javafxComponents.Controllers;

import Collections.CollectionProfessorList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Professor;

import java.util.EmptyStackException;

public class AddProfessorController {

    @FXML
    private TextField nameProfessor;

    @FXML
    private TextField adressProfessor;

    @FXML
    private TextField phoneProfessor;

    @FXML
    private TextField paymentProfessor;

    @FXML
    private Button defaultButton;

    private Professor professor;

    @FXML
    private void initialize(){
        addTextLimiter(nameProfessor,20);
        addTextLimiter(adressProfessor,25);
        addTextLimiter(phoneProfessor,12);
        addTextLimiter(paymentProfessor,10);
    }

    public void actionAdd(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("Добавить")){
            try{
                if (nameProfessor.getText().equals("")||
                        adressProfessor.getText().equals("")||
                        phoneProfessor.getText().equals("")||
                        paymentProfessor.getText().equals("")){
                    throw new EmptyStackException();
                }

                if (Float.parseFloat(paymentProfessor.getText())<=0){
                    throw new NumberFormatException();
                }

                Professor professor = new Professor(
                        nameProfessor.getText(),
                        adressProfessor.getText(),
                        phoneProfessor.getText(),
                        Float.parseFloat(paymentProfessor.getText())
                        );
                CollectionProfessorList.add(professor);
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
            catch (EmptyStackException e){
                DialogManager.showErrorDialog("Ошибка при добавлении",
                        "Все поля должны быть заполнены!");
            }
            catch (NumberFormatException e){
                DialogManager.showErrorDialog("Ошибка при добавлении",
                        "Проверьте корректность введённой оплаты!");
            }
            catch (Exception e){
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    void setProfessorValues(Professor professor){
        this.professor = professor;
        nameProfessor.setText(professor.getName());
        adressProfessor.setText(professor.getAdress());
        phoneProfessor.setText(professor.getPhone());
        paymentProfessor.setText(String.valueOf(professor.getPayment()));
        defaultButton.setText("Редактировать");
    }

    private void saveChanges() throws Exception{
        try {
            if (nameProfessor.getText().equals("")||
                adressProfessor.getText().equals("")||
                phoneProfessor.getText().equals("")||
                paymentProfessor.getText().equals("")){
            throw new EmptyStackException();
        }

            if (Float.parseFloat(paymentProfessor.getText())<=0){
                throw new NumberFormatException();
            }
            professor.setName(nameProfessor.getText());
            professor.setAdress(adressProfessor.getText());
            professor.setPhone(phoneProfessor.getText());
            professor.setPayment(Float.parseFloat(paymentProfessor.getText()));}
        catch (EmptyStackException e){
            DialogManager.showErrorDialog("Ошибка при редактировании",
                    "Все поля должны быть заполнены!");
            throw new Exception();
        }
        catch (NumberFormatException e){
            DialogManager.showErrorDialog("Ошибка при редактировании",
                    "Проверьте корректность введённой оплаты!");
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

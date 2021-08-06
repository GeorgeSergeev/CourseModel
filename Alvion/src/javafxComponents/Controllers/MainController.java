package javafxComponents.Controllers;
import JSON.JsonWorker;
import Collections.CollectionCourseList;
import Collections.CollectionProfessorList;
import Collections.CollectionStudentList;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Course;
import model.Professor;
import model.Student;

public class MainController {

    @FXML
    private TableView studentsTableView;
    @FXML
    private TableColumn<Student, String> columnStudentName;
    @FXML
    private TableColumn<Student, String> columnStudentAdress;
    @FXML
    private TableColumn<Student, String> columnStudentPhone;
    @FXML
    private TableColumn<Student, String> columnStudentEmail;
    @FXML
    private TableColumn<Student, Integer> columnStudentNum;
    @FXML
    private TableColumn<Student, Float> columnStudentAverageRating;
    @FXML
    private Label labelCountStudents;

    @FXML
    private TableView professorsTableView;
    @FXML
    private TableColumn <Professor, String> columnProfessorName;
    @FXML
    private TableColumn <Professor, String> columnProfessorAdress;
    @FXML
    private TableColumn <Professor, String> columnProfessorPhone;
    @FXML
    private TableColumn <Professor, Float> columnProfessorPayment;
    @FXML
    private Label labelCountProfessors;

    @FXML
    private TableView coursesTableView;
    @FXML
    private TableColumn<Course, Integer> columnCourseNum;
    @FXML
    private TableColumn<Course, String> columnCourseName;
    @FXML
    private TableColumn<Course, Float> columnCourseCost;
    @FXML
    private TableColumn<Course, Integer> columnCourseStudentCount;
    @FXML
    private Label labelCountCourses;


    @FXML
    private void initialize() {
        initStudentTable();
        initProfessorTable();
        initCourseTable();
    }

    private void updateLabelCountStudents() {
        labelCountStudents.setText("Кол-во студентов: " + studentsTableView.getItems().size());
    }

    private void updateLabelCountProfessors(){
        labelCountProfessors.setText("Кол-во профессоров: " + professorsTableView.getItems().size());
    }

    private void updateLabelCountCourses(){
        labelCountCourses.setText("Кол-во курсов: "+ CollectionCourseList.getCourseList().size());
    }


    private void initStudentTable(){
        columnStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        columnStudentAdress.setCellValueFactory(new PropertyValueFactory<Student, String>("adress"));
        columnStudentPhone.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        columnStudentEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        columnStudentNum.setCellValueFactory(new PropertyValueFactory<Student, Integer>("num"));
        columnStudentAverageRating.setCellValueFactory(new PropertyValueFactory<Student, Float>("averageRating"));
        studentsTableView.setItems(CollectionStudentList.getStudentList());
        updateLabelCountStudents();
        CollectionStudentList.getStudentList().addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(Change<? extends Student> c) {
                updateLabelCountStudents();
            }
        });
    }

    private void initProfessorTable(){
        columnProfessorName.setCellValueFactory(new PropertyValueFactory<Professor, String>("name"));
        columnProfessorAdress.setCellValueFactory(new PropertyValueFactory<Professor, String>("adress"));
        columnProfessorPhone.setCellValueFactory(new PropertyValueFactory<Professor, String>("phone"));
        columnProfessorPayment.setCellValueFactory(new PropertyValueFactory<Professor, Float>("payment"));
        professorsTableView.setItems(CollectionProfessorList.getProfessorList());
        updateLabelCountProfessors();
        CollectionProfessorList.getProfessorList().addListener(new ListChangeListener<Professor>() {
            @Override
            public void onChanged(Change<? extends Professor> c) {
                updateLabelCountProfessors();
            }
        });
    }

    private void initCourseTable(){
        columnCourseNum.setCellValueFactory(new PropertyValueFactory<Course, Integer>("num"));
        columnCourseName.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        columnCourseCost.setCellValueFactory(new PropertyValueFactory<Course, Float>("cost"));
        columnCourseStudentCount.setCellValueFactory(new PropertyValueFactory<Course, Integer>("studentCount"));
        coursesTableView.setItems(CollectionCourseList.getCourseList());
        updateLabelCountCourses();
        CollectionCourseList.getCourseList().addListener(new ListChangeListener<Course>() {
            @Override
            public void onChanged(Change<? extends Course> c) {
                updateLabelCountCourses();
            }
        });
    }

    @FXML
    private void actionStudentButtonPressed(ActionEvent actionEvent) {

        Button clickedButton = (Button) actionEvent.getSource();
        Student selectedStudent = (Student) studentsTableView.getSelectionModel().getSelectedItem();

        switch (clickedButton.getText()) {
            case "Добавить": {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/addStudent.fxml"));
                    stage.setTitle("Добавление студента");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Редактировать": {
                try {
                    if (selectedStudent == null){
                        throw new Exception();
                    }
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/addStudent.fxml"));
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    AddStudentController asc = loader.getController();
                    asc.setStudentValues(selectedStudent);
                    stage.setTitle("Редактирование студента");
                    stage.showAndWait();
                    studentsTableView.refresh();
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка редактирования",
                            "Для редактирования студента необходимо выбрать запись из таблицы!");
                }
                break;
            }
            case "Удалить": {
                try {
                    if (selectedStudent == null){
                        throw new Exception();
                    }
                    CollectionStudentList.delete(selectedStudent);
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка удаления",
                            "Для удаления студента необходимо выбрать запись их таблицы!");
                }
                break;
            }
            case "Может заниматься":{
                try {
                    if (selectedStudent == null){
                        throw new Exception();
                    }
                    selectedStudent.canEnroll();
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка",
                            "Для вывода доступных курсов необходимо выбрать запись их таблицы!");
                }
                break;
            }
            case "Список прослушанных":{
                try {
                    if (selectedStudent == null){
                        throw new Exception();
                    }
                    selectedStudent.completedCourses();
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка",
                            "Для вывода прослушанных курсов необходимо выбрать запись их таблицы!");
                }
                break;
            }
        }
    }

    public void actionProfessorButtonPressed(ActionEvent actionEvent) {

        Button clickedButton = (Button) actionEvent.getSource();
        Professor selectedProfessor = (Professor) professorsTableView.getSelectionModel().getSelectedItem();

        switch (clickedButton.getText()) {
            case "Добавить": {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/addProfessor.fxml"));
                    stage.setTitle("Добавление профессора");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Редактировать": {
                try {
                    if (selectedProfessor == null){
                        throw new Exception();
                    }
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/addProfessor.fxml"));
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    AddProfessorController apc = loader.getController();
                    apc.setProfessorValues(selectedProfessor);
                    stage.setTitle("Редактирование студента");
                    stage.showAndWait();
                    professorsTableView.refresh();
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка редактирования",
                            "Для редактирования профессора необходимо выбрать запись из таблицы!");
                }
                break;
            }
            case "Удалить": {
                try {
                    if (selectedProfessor == null){
                        throw new Exception();
                    }
                    CollectionProfessorList.delete((Professor) professorsTableView.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка удаления",
                            "Для удаления профессора необходимо выбрать запись из таблицы!");
                }
            }
        }
    }

    public void actionCourseButtonPressed(ActionEvent actionEvent) {

        Button clickedButton = (Button) actionEvent.getSource();
        Course selectedCourse = (Course) coursesTableView.getSelectionModel().getSelectedItem();

        switch (clickedButton.getText()) {
            case "Добавить": {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/addCourse.fxml"));
                    stage.setTitle("Добавление курса");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Редактировать": {
                try {
                    if (selectedCourse == null){
                        throw new Exception();
                    }
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/addCourse.fxml"));
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    AddCourseController acc = loader.getController();
                    acc.setCorseValues(selectedCourse);
                    stage.setTitle("Редактирование курса");
                    stage.showAndWait();
                    coursesTableView.refresh();
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка редактирования",
                            "Для редактирования курса необходимо выбрать запись из таблицы!");
                }
                break;
            }
            case "Удалить": {
                try {
                    if (selectedCourse == null){
                        throw new Exception();
                    }
                    CollectionCourseList.delete((Course) coursesTableView.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    DialogManager.showErrorDialog("Ошибка удаления",
                            "Для удаления курса необходимо выбрать запись из таблицы!");
                }
            }
        }
    }

    public void ationCoursePassingButtonPressed(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        switch (clickedButton.getText()) {
            case "Записать студента": {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/enrollCourse.fxml"));
                    stage.setTitle("Запись студента на курс");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    coursesTableView.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Удалить студента":{
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/deleteCoursePassing.fxml"));
                    stage.setTitle("Удаление студента с курса");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    coursesTableView.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void actionMarkButtonPressed(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        switch (clickedButton.getText()) {
            case "Добавить оценку": {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/addMark.fxml"));
                    stage.setTitle("Добавление оценки:");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Получить финальный балл":{
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/finishCourse.fxml"));
                    stage.setTitle("Завершение курса");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    studentsTableView.refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Получить средний балл":{
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Scenes/averageRating.fxml"));
                    stage.setTitle("Средниий балл");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void actionJsonPressed(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        switch (clickedButton.getText()) {
            case "Сериализовать": {
                JsonWorker.toJson();
                break;
            }
            case "Десериализовать":{
                JsonWorker.fromJson();
                break;
            }
        }
    }
}
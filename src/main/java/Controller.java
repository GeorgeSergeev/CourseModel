
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import model.Course;
import model.Professor;
import model.Student;
import service.CourseService;
import service.ProfessorService;
import service.StudentService;
import util.Services;
import util.SessionInstance;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private SessionInstance sessionInstance;
    private List<Student> students;
    private List<Professor> professors;
    private List<Course> courses;

    @Getter
    private StudentService studentService;

    @Getter
    private ProfessorService professorService;

    @Getter
    private CourseService courseService;

    private Services services;

    private Student selectedStudent;
    private Professor selectedProfessor;
    private Course selectedCourse;

    @FXML
    ListView<String> listStudents;

    @FXML
    ListView<String> listProfessors;

    @FXML
    ListView<String> listGroups;

    @FXML
    Button btnStudAdd;

    @FXML
    Button btnStudDel;

    @FXML
    Button btnStudAddToCourse;

    @FXML
    Button btnStudDelFromCourse;

    @FXML
    Button btnProfAdd;

    @FXML
    Button btnProfDel;

    @FXML
    Button btnProfAddToCourse;

    @FXML
    Button btnCourseAdd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            services = Services.getInstance();
            studentService = services.getStudentService();
            professorService = services.getProfessorService();
            courseService = services.getCourseService();
            sessionInstance = SessionInstance.getInstance();
            sessionInstance.getSession(); // Открываем подключение к БД (начинаем сессию)

            initStudentsListView();
            initProfessorsListView();
            initCoursesListView();

        }).start();

        btnStudDel.setDisable(true);
        btnProfAddToCourse.setDisable(true);
        btnProfDel.setDisable(true);
        btnStudAddToCourse.setDisable(true);
        btnStudDelFromCourse.setDisable(true);
    }

    private void initCoursesListView() {
        refreshCourseList();
        listGroups.setOnMouseClicked(value -> runFX(() -> {
            selectedCourse = courseService.getByName(listGroups.getSelectionModel().getSelectedItem());
            btnStudDel.setDisable(true);
            btnStudAddToCourse.setDisable(true);
            btnStudDelFromCourse.setDisable(true);
            btnProfDel.setDisable(true);
            btnProfAddToCourse.setDisable(true);
        }));
    }

    private void initProfessorsListView() {
        refreshProfessorsList();
        listProfessors.setOnMouseClicked(value -> runFX(() -> {
            selectedProfessor = professorService.getByName(listProfessors.getSelectionModel().getSelectedItem());
            btnStudDel.setDisable(true);
            btnStudAddToCourse.setDisable(true);
            btnStudDelFromCourse.setDisable(true);
            btnProfDel.setDisable(false);
            btnProfAddToCourse.setDisable(false);
        }));
    }

    private void initStudentsListView() {
        refreshStudentsList();
        listStudents.setOnMouseClicked(value -> runFX(() -> {
            selectedStudent = studentService.getByName(listStudents.getSelectionModel().getSelectedItem());
            btnStudDel.setDisable(false);
            btnStudAddToCourse.setDisable(false);
            btnStudDelFromCourse.setDisable(false);
            btnProfDel.setDisable(true);
            btnProfAddToCourse.setDisable(true);
        }));
    }

    public void addStudent(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewStudent.fxml"));
            Parent root = loader.load();
            NewStudentController newStudentController = loader.getController();
            newStudentController.controller = this;
            stage.setTitle("Create student");
            stage.setScene(new Scene(root, 400, 300));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delStudent(ActionEvent actionEvent) {
        studentService.removeStudent(selectedStudent);
        refreshStudentsList();
    }

    protected void refreshCourseList() {
        courses = courseService.getAll();
        listGroups.getItems().clear();
        for (int i = 0; i < courses.size(); i++) {
            listGroups.getItems().add(courses.get(i).getName());
        }
    }

    protected void refreshProfessorsList() {
        professors = professorService.getAll();
        listProfessors.getItems().clear();
        for (int i = 0; i < professors.size(); i++) {
            listProfessors.getItems().add(professors.get(i).getName());
        }
    }

    protected void refreshStudentsList() {
        students = studentService.getAll();
        listStudents.getItems().clear();
        for (int i = 0; i < students.size(); i++) {
            listStudents.getItems().add(students.get(i).getName());
        }
    }

    private void runFX(Runnable r) {
        if (Platform.isFxApplicationThread()) {
            r.run();
        } else {
            Platform.runLater(r);
        }
    }

    public void showAlert(String text) {
        runFX(() -> new Alert(Alert.AlertType.WARNING, text, ButtonType.OK).showAndWait());
    }
}

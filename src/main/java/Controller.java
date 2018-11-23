
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
import lombok.Setter;
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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private StringBuilder infoString = new StringBuilder();
    private SessionInstance sessionInstance;
    private List<Student> students;
    private List<Course> selectedStudentCourses = new ArrayList<>();
    private List<Professor> professors;

    @Getter
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

    @Setter
    private Course selectedCourse;

    @FXML
    ListView<Student> listStudents;

    @FXML
    ListView<Professor> listProfessors;

    @FXML
    ListView<Course> listGroups;

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
            if (value.getClickCount() == 2) {
                buildInfoAboutSelectedObject(3);
            }
            selectedCourse = listGroups.getSelectionModel().getSelectedItem();
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
            if (value.getClickCount() == 2) {
                buildInfoAboutSelectedObject(2);
            }
            selectedProfessor = listProfessors.getSelectionModel().getSelectedItem();
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
            if (value.getClickCount() == 2) {
                buildInfoAboutSelectedObject(1);
            }
            selectedStudent = listStudents.getSelectionModel().getSelectedItem();
            btnStudDel.setDisable(false);
            btnStudAddToCourse.setDisable(false);
            btnStudDelFromCourse.setDisable(false);
            btnProfDel.setDisable(true);
            btnProfAddToCourse.setDisable(true);
        }));
    }

    public void addStudent(ActionEvent actionEvent) {
        callAddingObjectWindow(1, "Create student");
    }

    public void delStudent(ActionEvent actionEvent) {
        studentService.removeStudent(selectedStudent);
        refreshStudentsList();
    }

    public void addProfessor(ActionEvent actionEvent) {
        callAddingObjectWindow(2, "Create professor");
    }

    public void delProfessor(ActionEvent actionEvent) {
        professorService.removeProfessor(selectedProfessor);
        refreshProfessorsList();
    }

    public void addCourse(ActionEvent actionEvent) {
        callAddingObjectWindow(3, "Create course");
    }

    private void callAddingObjectWindow(int objectId, String windowTitle) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewObject.fxml"));
            Parent root = loader.load();
            NewObjectController newObjectController = loader.getController();
            newObjectController.controller = this;
            newObjectController.configureView(objectId);
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root, 400, 300));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudentToCourse(ActionEvent actionEvent) {
        chooseCourseAndExecute(() -> {
            courseService.addStudentToCourse(selectedCourse, selectedStudent);
            showAlert("Student added to course!");
        });
    }

    public void setProfessorToCourse(ActionEvent actionEvent) {
        chooseCourseAndExecute(() -> {
            courseService.setProfessorForCourse(selectedCourse, selectedProfessor);
            showAlert("Professor hired to course!");
        });
    }

    private void chooseCourseAndExecute(Runnable r) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChooseCourse.fxml"));
            Parent root = loader.load();
            ChooseController chooseController = loader.getController();
            chooseController.controller = this;
            chooseController.fillList();
            stage.setTitle("Choose course");
            stage.setScene(new Scene(root, 400, 200));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            r.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void refreshCourseList() {
        courses = courseService.getAll();
        listGroups.getItems().clear();
        for (int i = 0; i < courses.size(); i++) {
            listGroups.getItems().add(courses.get(i));
        }
    }

    protected void refreshProfessorsList() {
        professors = professorService.getAll();
        listProfessors.getItems().clear();
        for (int i = 0; i < professors.size(); i++) {
            listProfessors.getItems().add(professors.get(i));
        }
    }

    protected void refreshStudentsList() {
        students = studentService.getAll();
        listStudents.getItems().clear();
        for (int i = 0; i < students.size(); i++) {
            listStudents.getItems().add(students.get(i));
        }
    }

    private void buildInfoAboutSelectedObject(int objectId) {
        infoString.setLength(0);
        switch (objectId) {
            case 1: // Student
                infoString.append("Name: ").append(selectedStudent.getName()).append("\n")
                        .append("Address: ").append(selectedStudent.getAddress()).append("\n")
                        .append("Phone: ").append(selectedStudent.getPhone()).append("\n")
                        .append("Email: ").append(selectedStudent.getEmail()).append("\n")
                        .append("Graduate Book Num:").append(selectedStudent.getGradeBookNum());
                float averageScore = selectedStudent.calculateAverageScore();
                if (!Float.isNaN(averageScore)) {
                    infoString.append("\n").append("Average score: ").append(averageScore);
                }
                selectedStudentCourses = selectedStudent.graduatedCourses();
                buildInfoAboutStudentCourses("Graduated courses:");
                selectedStudentCourses = selectedStudent.notGraduatedCourses();
                buildInfoAboutStudentCourses("Active courses:");
                break;
            case 2: // Professor
                infoString.append("Name: ").append(selectedProfessor.getName()).append("\n")
                        .append("Address: ").append(selectedProfessor.getAddress()).append("\n")
                        .append("Phone: ").append(selectedProfessor.getPhone()).append("\n")
                        .append("Salary:").append(selectedProfessor.getSalary());
                break;
            case 3: // Course
                infoString.append("Name: ").append(selectedCourse.getName()).append("\n")
                        .append("Cost:").append(selectedCourse.getCost());
                Professor professor = selectedCourse.getProfessor();
                if (professor != null) {
                    infoString.append("\n").append("Professor: ").append(professor.toString());
                }
                break;
        }

        showAlert(infoString.toString());
    }

    private void buildInfoAboutStudentCourses(String value) {
        float averageScore;
        if (selectedStudentCourses.size() > 0) {
            infoString.append("\n").append(value);
            for (int i = 0; i < selectedStudentCourses.size(); i++) {
                infoString.append("\n").append(i + 1).append(". ").append(" ").append(selectedStudentCourses.get(i).getName());
                averageScore = selectedStudent.averageScoreForCourse(selectedStudentCourses.get(i));
                if (!Float.isNaN(averageScore)) {
                    infoString.append(", avg.score: ").append(averageScore);
                }
            }
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
        runFX(() -> new Alert(Alert.AlertType.NONE, text, ButtonType.OK).showAndWait());
    }
}

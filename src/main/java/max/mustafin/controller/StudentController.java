package max.mustafin.controller;

import max.mustafin.model.*;
import max.mustafin.service.CourseService;
import max.mustafin.service.StudentService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/controller")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @RequestMapping(value = "/preCreateStudent", method = RequestMethod.GET)
    public String preCreateStudent(ModelMap model,  HttpServletRequest request, HttpServletResponse response) {
        List<Course> allCourses = courseService.getAll();
        request.setAttribute("allCourses",allCourses);
        return "create-student";
    }
    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    public String createStudent(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        // Getting parameters from request
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        Student student = new Student(name,phoneNumber,email);
        // Create new history object
        History history = new History();
        student.setHistory(history);
        // If course was chosen add it to student's course list
        if (request.getParameter("possible-result")!=null) {
            Course course = courseService.getByNumber(Integer.parseInt(request.getParameter("possible-result")));
            List<Course> courseList = student.getCourseList();
            courseList.add(course);
            CourseProgress courseProgress = new CourseProgress();
            student.setCourseProgress(courseProgress);
        }
        studentService.update(student);
        return preCreateStudent(model,request,response);
    }
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllStudents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        List<Student> allStudents = studentService.getAll();
        request.setAttribute("allStudents",allStudents);
        return "show-all";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") String id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Student student = studentService.getByScoreBook(Integer.parseInt(id));
        studentService.delete(student);
        List<Student> allStudents = studentService.getAll();
        request.setAttribute("allStudents",allStudents);
        return "show-all";
    }
    @RequestMapping(value = "preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdateStudent(@PathVariable("id") String id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Student student = studentService.getByScoreBook(Integer.parseInt(id));
        request.setAttribute("student", student);
        List<Course> studentCourseList = student.getCourseList();
        String courseName = studentCourseList.get(studentCourseList.size()-1).getName();
        request.setAttribute("course", courseName);
        List<Course> courseList = courseService.getAll();
        request.setAttribute("courseList",courseList);
        return "update";
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateStudent(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        int scoreBookNumber = Integer.parseInt(request.getParameter("scoreBookNumber"));
        Student student = studentService.getByScoreBook(scoreBookNumber);
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        student.setName(name);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);
        studentService.update(student);
        return preCreateStudent(model, request,response);
    }
    @RequestMapping(value = "changeCourse/{id}", method = RequestMethod.POST)
    public String changeCourse(@PathVariable("id") String id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Student student = studentService.getByScoreBook(Integer.parseInt(id));
        student.getCourseList().clear();
        // Add chosen course
        Course course = courseService.getByNumber(Integer.parseInt(request.getParameter("possible-result")));
        List<Course> courseList = student.getCourseList();
        courseList.add(course);
        // Reset or create new Course Progress
        CourseProgress courseProgress = student.getCourseProgress();
        if (courseProgress == null) courseProgress = new CourseProgress();
        else {
            courseProgress.setCourseAverageScore(0);
            courseProgress.setFinalScore(0);
            courseProgress.getScoreList().clear();
        }
        studentService.merge(student);
        return preUpdateStudent(id,model,request,response);
    }
    @RequestMapping(value = "passExam/{id}", method = RequestMethod.POST)
    public String passExam(@PathVariable("id") String id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Student student = studentService.getByScoreBook(Integer.parseInt(id));
        // Create and add exam's score
        Score score = createScore();
        Set<Score> scoreList = student.getCourseProgress().getScoreList();
        scoreList.add(score);
        // Count and set course average score
        float courseAvgScore = countCurrentAvgScore(scoreList);
        student.getCourseProgress().setCourseAverageScore(courseAvgScore);
        // If student pass 5 exams he finished course. Update student.
        if (scoreList.size()>4) {
            // Update student's course final average score
            student.getCourseProgress().setFinalScore(courseAvgScore);
            // Update number of finished courses
            int numFinishedCourses = student.getNumFinishedCourses()+1;
            student.setNumFinishedCourses(numFinishedCourses);
            // Update student's total score
            float totalScore = student.getTotalScore() + courseAvgScore;
            student.setTotalScore(totalScore);
            // Update student's total average score
            float avgScore = totalScore/numFinishedCourses;
            student.setAverageScore(avgScore);
            // Update history line
            updateHistoryLine(student,courseAvgScore);
        }
        studentService.update(student);
        return preUpdateStudent(id,model,request,response);
    }
    private float countCurrentAvgScore(Set<Score> set) {
        float sum = 0;
        for (Score s : set) {
            sum += s.getScore();
        }
        float size = set.size();
        return sum/size;
    }
    private Score createScore() {
        Random rand = new Random();
        int result = rand.nextInt(5) + 1;
        Score score = new Score();
        score.setScore(result);
        return score;
    }
    private void updateHistoryLine(Student student, float courseAvgScore) {
        History history = student.getHistory();
        String prevHistory = history.getHistoryLine();
        List<Course> studentCourseList = student.getCourseList();
        String courseName = studentCourseList.get(studentCourseList.size()-1).getName();
        String historyLine = prevHistory == null ? courseName+" "+courseAvgScore+"\n" : prevHistory+" "+courseName+" "+courseAvgScore+"\n";
        history.setHistoryLine(historyLine);
    }
}

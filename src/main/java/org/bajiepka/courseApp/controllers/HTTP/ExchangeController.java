package org.bajiepka.courseApp.controllers.HTTP;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bajiepka.courseApp.domain.*;
import org.bajiepka.courseApp.services.CourseService;
import org.bajiepka.courseApp.services.ExchangeFileService;
import org.bajiepka.courseApp.services.FileService;
import org.bajiepka.courseApp.services.StudentService;
import org.bajiepka.courseApp.wrappers.RootHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.*;

@Controller
@RequestMapping(value = "/exchange")
public class ExchangeController {

    @Value(value = "${json.exportfolder}")
    private String location;

    @Value(value = "${json.fileprefix}")
    private String prefix;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ExchangeFileService exchangeFileService;

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/upload")
    public String exportData(Model model){
        model.addAttribute("listOfFiles", exchangeFileService.all());
        return "upload";
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleFileUpload(@RequestParam(value = "file") MultipartFile file,
                                   RedirectAttributes redirectAttributes){

        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Файл для загрузки не выбран. Выберите файл в диалоге");
            return "redirect:/exchange/upload";
        }

        List files = new ArrayList();
        File uploadedFile = new File(location, prefix + "_" + file.getOriginalFilename());

        try (   FileOutputStream outputStream = new FileOutputStream(uploadedFile);
                BufferedOutputStream bufferedStream = new BufferedOutputStream(outputStream))
             {

                outputStream.write(file.getBytes());
                outputStream.flush();

                exchangeFileService.saveFile(new ExchangeFile(uploadedFile));

            } catch (IOException e) {
                e.printStackTrace();
            }
        return "redirect:/exchange/upload";
    }

    @GetMapping(value = "/global-export")
    public String previewAllAsJSON(@RequestParam boolean toFile, Model model){
        model.addAttribute("toFile",    toFile);
        model.addAttribute("json",      exchangeFileService.exportAll(toFile));
        return "preview-course";
    }

    @GetMapping(value = "/preview")
    public String previewAsJSON(@RequestParam Long id, Model model){
        model.addAttribute("exchangeFile", exchangeFileService.getExchangeFileById(id));
        model.addAttribute("json", exchangeFileService.viewExchangeFileAsJSON(id));
        model.addAttribute("toFile", false);
        return "preview-file";
    }

    @GetMapping(value = "/delete")
    public String dismissStudent(@RequestParam Long id){
        exchangeFileService.delete(id);
        return "redirect:/exchange/upload";
    }

    @GetMapping(value = "/import")
    public String importFile(@RequestParam Long id){

        String content = fileService.readFile(exchangeFileService.findById(id).getName());

        File importFile = new File(exchangeFileService.findById(id).getName());
        if (importFile.exists()) {

            try (FileInputStream stream = new FileInputStream(importFile)) {

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
                RootHolder rootHolder = mapper.readValue(stream, RootHolder.class);

                importData(rootHolder);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/exchange/upload";
    }

    private void importData(RootHolder rootHolder) {

        for (Student student : rootHolder.getStudents()){
            Student existingStudent = studentService.getStudentById(student.getId());
            if (existingStudent == null){
                studentService.saveStudent(student);
            } else {
                existingStudent.fill(student);
                studentService.saveStudent(existingStudent);
            }
        }

        for (Course course : rootHolder.getCourses()){
            Course existingCourse = courseService.getCourseById(course.getId());
            if (existingCourse == null){
                courseService.saveCourse(course);
            } else {
                existingCourse.fill(course);
                courseService.saveCourse(existingCourse);
            }
        }

        for (CourseProgress courseProgress : rootHolder.getCourseProgresses()){

        }

        for (Professor professor : rootHolder.getProfessors()){

        }

    }
}

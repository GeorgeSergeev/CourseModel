package ru.khrebtov.report;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Professor;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.StudyCourse;
import ru.khrebtov.repositories.CourseRepository;
import ru.khrebtov.repositories.ProfessorRepository;
import ru.khrebtov.repositories.StudyCourseRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class ReportService implements ReportServiceRest {

    @EJB
    private ProfessorRepository professorRepository;
    @EJB
    private CourseRepository courseRepository;
    @EJB
    private StudyCourseRepository studyCourseRepository;


    public ReportService() {
    }

    @Override
    public String createReport() {
        String filePath = null;
        try {
            XSSFWorkbook book = new XSSFWorkbook();
            File file = createFile();
            FileOutputStream fileOut = new FileOutputStream(file);
            XSSFSheet sheet = book.createSheet("professors");
            sheet.setColumnWidth(0, 12000);
            sheet.setColumnWidth(1, 12000);
            sheet.setColumnWidth(2, 12000);
            XSSFCellStyle cellStyle = book.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);

            XSSFRow title = sheet.createRow((short) 0);
            XSSFCell titleCell = title.createCell(0);
            titleCell.setCellType(CellType.STRING);
            titleCell.setCellValue("ФИО профессора");
            titleCell.setCellStyle(cellStyle);

            titleCell = title.createCell(1);
            titleCell.setCellType(CellType.STRING);
            titleCell.setCellValue("Суммарное количество студентов по всем курсам");
            titleCell.setCellStyle(cellStyle);

            titleCell = title.createCell(2);
            titleCell.setCellType(CellType.STRING);
            titleCell.setCellValue("Средння успеваемость студентов по всем курсам");
            titleCell.setCellStyle(cellStyle);

            List<ReportEntity> reportEntities = getReportEntities();
            for (int i = 0; i < reportEntities.size(); i++) {
                ReportEntity reportEntity = reportEntities.get(i);
                XSSFRow row = sheet.createRow((short) (i + 1));
                XSSFCell cell = row.createCell(0);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(reportEntity.getProfessorName());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(1);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(reportEntity.getNumberOfStudents());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(2);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(reportEntity.getAverageStudentsRating());
                cell.setCellStyle(cellStyle);
            }
            filePath = file.getAbsolutePath();
            book.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("report created: %s", filePath);
    }

    private File createFile() throws IOException {
        LocalDate ld = LocalDate.now();
        String fileSeparator = System.getProperty("file.separator");
        String userName = System.getProperty("user.name");
        String absoluteDirPath = String.format("C:%sUsers%s%s%sDownloads%sreports", fileSeparator,
                fileSeparator, userName, fileSeparator, fileSeparator);

        if (!Files.exists(Paths.get(absoluteDirPath))) {
            Files.createDirectory(Paths.get(absoluteDirPath));
        }

        return new File(absoluteDirPath + File.separator + String.format("report_%s.xlsx", ld));
    }

    private List<ReportEntity> getReportEntities() {
        List<ReportEntity> entities = new ArrayList<>();
        List<Professor> allProfessors = professorRepository.findAll();

        for (Professor p : allProfessors) {
            long numberOfStudents = 0L;
            Set<StudyCourse> studyCourses = new HashSet<>();
            for (Course c : professorRepository.getProfessorCourse(p.getId())) {
                Set<Student> students = courseRepository.getCourseStudents(c.getId());
                numberOfStudents += students.size();
                for (Student s : students) {
                    StudyCourse studyCourse = studyCourseRepository.findByCourseIdAndStudentId(c.getId(), s.getId());
                    studyCourse.setRating(studyCourseRepository.getRatings(studyCourse.getId()));
                    studyCourses.add(studyCourse);
                }
            }
            Float averageStudentsRating = getAverageRatingForAllCourses(studyCourses);
            ReportEntity reportEntity = new ReportEntity(p.getName(), numberOfStudents, averageStudentsRating);
            entities.add(reportEntity);
        }
        return entities;
    }

    private Float getAverageRatingForAllCourses(Set<StudyCourse> studyCourses) {
        float sumAverageRating = 0F;
        int countStudyCoursesWithRatings = 0;
        for (StudyCourse s : studyCourses) {
            Double averageRating = studyCourseRepository.getAverageRating(s.getId());
            if (averageRating != null) {
                countStudyCoursesWithRatings++;
                sumAverageRating += averageRating;
            }
        }
        return (float) Math.round(100 * (sumAverageRating / countStudyCoursesWithRatings)) / 100;
    }
}

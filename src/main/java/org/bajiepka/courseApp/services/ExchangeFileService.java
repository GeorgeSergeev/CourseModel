package org.bajiepka.courseApp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bajiepka.courseApp.converter.MyJsonConverter;
import org.bajiepka.courseApp.domain.ExchangeFile;
import org.bajiepka.courseApp.repositories.CourseRepository;
import org.bajiepka.courseApp.repositories.ExchangeFileRepository;
import org.bajiepka.courseApp.repositories.StudentRepository;
import org.bajiepka.courseApp.wrappers.RootHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
public class ExchangeFileService {

    @Autowired
    private ExchangeFileRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FileService fileService;

    public void saveFile(ExchangeFile exchangeFile){
        repository.save(exchangeFile);
    }

    public Iterable all() {
        return repository.findAll();
    }

    public ExchangeFile findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public String exportAll(boolean toFile) {

        RootHolder response = new RootHolder(
                studentRepository.findAll(),
                courseRepository.findAll(),
                Collections.emptyList(),
                Collections.emptyList());

        MyJsonConverter converter = new MyJsonConverter();
        converter.addForConversion(response);
        return converter.write(toFile);

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ExchangeFile getExchangeFileById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Object viewExchangeFileAsJSON(Long id) {

            String result = "...";

            try {

                ExchangeFile exchangeFile = getExchangeFileById(id);
                ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(mapper.writeValueAsString(exchangeFile) + "\n");
                stringBuilder.append(fileService.readFile(exchangeFile.getName()));
                return stringBuilder.toString();

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return result;
        }
}

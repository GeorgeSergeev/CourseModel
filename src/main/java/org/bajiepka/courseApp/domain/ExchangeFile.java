package org.bajiepka.courseApp.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

@Entity
@Data
public class ExchangeFile {

    private @Id @GeneratedValue Long id;
    @NotNull
    private String name;
    private Long size;

    public ExchangeFile() {
    }

    public ExchangeFile(File file) {
        name = file.getAbsolutePath();
        size = file.length();
    }

    public ExchangeFile(String name, Long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return name;
    }
}

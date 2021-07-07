package org.bajiepka.courseApp.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "progress")
public class CourseProgress {

    private @Id @GeneratedValue Long id;
    private @Version int version;

    public CourseProgress() {
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Course course;

    @ElementCollection
    @CollectionTable(name = "results", joinColumns = @JoinColumn(name = "progress_id"))
    @Column(name = "result")
    private List<Integer> results;

   private int finalMark;

}

package max.mustafin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course_progress")
public class CourseProgress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "c_avg_score")
    private float courseAverageScore;
    @Column(name = "final_score")
    private float finalScore;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Score> scoreList = new HashSet<>();

    public CourseProgress() {

    }

    public Set<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(Set<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCourseAverageScore() {
        return courseAverageScore;
    }

    public void setCourseAverageScore(float courseAverageScore) {
        this.courseAverageScore = courseAverageScore;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

}

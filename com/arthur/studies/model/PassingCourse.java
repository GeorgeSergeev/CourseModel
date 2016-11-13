package com.arthur.studies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PassingCourse implements Serializable {
    private List<Integer> scores = new ArrayList<>();

    private Course course;

    public PassingCourse(Course course) {
        // if inputs is incorrect
        if (course == null) {
            throw new RuntimeException("Incorrect passing course inputs...");
        }
        this.course = course;
    }

    public void addScore(int n) {
        if ((scores.size() < 11) && (n > 1 && n < 6)) {
            scores.add(n);
        }
    }

    public Integer getAverageScore() {
        // sum of scores
        int sum = 0;
        // count values are 0, 1, 2, ..., 10
        int count = 0;
        // sum evaluation
        for (int index = 0; index < scores.size(); index++) {
            if (index != 10) {
                sum += scores.get(index);
                count++;
            }
        }

        if (count == 0) {   // if we have no scores
            return null;
        } else {            // if we have some scores
            return Math.round(1.0f * sum / count);
        }
    }

    public Integer getFinalScore() {
        // the last 11-th score is exam score (special score)
        if (scores.size() < 11) {   // if we have no special score
            return null;
        } else {                    // if we have special score
            return Math.round((1.0f * getAverageScore() + scores.get(10)) / 2);
        }
    }

    public Course getCourse() {
        return this.course;
    }

    @Override
    public String toString() {
        return  "PassingCourse {scores = " +
                scores +
                "}";
    }
}

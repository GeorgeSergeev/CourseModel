package ru.khrebtov.entity;

import java.util.List;
import java.util.Objects;

public class StudyCourse {
    private List<Integer> ratings;

    public StudyCourse() {
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyCourse that = (StudyCourse) o;
        return Objects.equals(ratings, that.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratings);
    }

    @Override
    public String toString() {
        return "StudyCourse{" +
                "ratings=" + ratings +
                '}';
    }
}

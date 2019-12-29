package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.of;

public class Positions {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Positions(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
        this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

    public Positions(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "start date couldn't be null");
        Objects.requireNonNull(endDate, "end date couldn't be null");
        Objects.requireNonNull(title, "title of job couldn't be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Positions that = (Positions) o;
        return startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                title.equals(that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "organizationParameters{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

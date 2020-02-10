package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String description;

    public Position() {
    }

    public Position(int startYear,
                    Month startMonth,
                    String title,
                    String description) {
        this(of(startYear, startMonth), NOW, title, description);
    }

    public Position(int startYear,
                    Month startMonth,
                    int endYear,
                    Month endMonth,
                    String title,
                    String description) {
        this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "start date couldn't be null");
        Objects.requireNonNull(endDate, "end date couldn't be null");
        Objects.requireNonNull(title, "title of job couldn't be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description == null ? "" : description;
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
        Position that = (Position) o;
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
        return "Position(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
    }
}

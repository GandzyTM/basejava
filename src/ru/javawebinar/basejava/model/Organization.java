package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    //    private final LocalDate startDate;
//    private final LocalDate endDate;
    private final String title;
    private final String description;

    private List<LocalDate> startDateList;
    private List<LocalDate> endDateList;

    public Organization(String name, String url, LocalDate startDateList, LocalDate endDateList, String title, String description) {
//        Objects.requireNonNull(startDate, "start date couldn't be null");
//        Objects.requireNonNull(endDate, "end date couldn't be null");
        Objects.requireNonNull(title, "title of job couldn't be null");
        this.homePage = new Link(name, url);
//        this.startDate = startDate;
//        this.endDate = endDate;
        this.startDateList = Arrays.asList(startDateList);
        this.endDateList = Arrays.asList(endDateList);
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!Objects.equals(homePage, that.homePage)) return false;
//        if (!Objects.equals(startDate, that.startDate)) return false;
//        if (!Objects.equals(endDate, that.endDate)) return false;
        if (!Objects.equals(startDateList, that.startDateList)) return false;
        if (!Objects.equals(endDateList, that.endDateList)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDateList, endDateList, title, description);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

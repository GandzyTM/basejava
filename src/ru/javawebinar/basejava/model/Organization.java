package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.*;

public class Organization {
    private final Link homePage;
    private List<Position> positions = new ArrayList<>();

    public Organization(String name,
                        String url,
                        LocalDate startDate,
                        LocalDate endDate,
                        Position positions,
                        String title,
                        String description) {
        this.homePage = new Link(name, url);
        this.positions = Collections.singletonList(new Position(startDate, endDate, title, description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", organizationParameters=" + positions +
                '}';
    }
}


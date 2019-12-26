package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private List<Position> Position = new ArrayList<>();

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.homePage = new Link(name, url);
        this.Position = Collections.singletonList(new Position(startDate, endDate, title, description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) &&
                Position.equals(that.Position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, Position);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", organizationParameters=" + Position +
                '}';
    }
}


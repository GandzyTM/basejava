package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private List<Positions> positions = new ArrayList<>();

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.homePage = new Link(name, url);
        this.positions = Collections.singletonList(new Positions(startDate, endDate, title, description));
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


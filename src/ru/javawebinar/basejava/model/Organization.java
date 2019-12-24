package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private List<organizationVars> organizationVars = new ArrayList<>();

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.homePage = new Link(name, url);
        this.organizationVars = Collections.singletonList(new organizationVars(startDate, endDate, title, description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) &&
                organizationVars.equals(that.organizationVars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, organizationVars);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", organizationParameters=" + organizationVars +
                '}';
    }
}


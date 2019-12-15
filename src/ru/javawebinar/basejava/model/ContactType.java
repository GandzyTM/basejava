package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Cell Phone"),
    SKYPE("Skype"),
    EMAIL("E-mail"),
    LINKEDIN("LinkedIn profile"),
    GITHUB("GitHub profile"),
    STACKOVERFLOW("Stackoverflow profile"),
    HOMEPAGE("Home page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

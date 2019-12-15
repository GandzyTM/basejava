package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("new Resume");
        Map<ContactType, String> contacts = resume.getContacts();
        Map<SectionType, Section> sections = resume.getSections();
//        Link link = new Link("Домашняя страница", "http://gkislin.ru/");
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, String.valueOf(new Link("Profile LinkedIn", "https://www.linkedin.com/in/gkislin")));
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");

        sections.put(SectionType.OBJECTIVE, new ContentSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n"));
        sections.put(SectionType.PERSONAL, new ContentSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n"));

        StringBuilder text = new StringBuilder();
        text.append(ContactType.PHONE.getTitle())
                .append(": ")
                .append(contacts.get(ContactType.PHONE))
                .append("\n");
        text.append(ContactType.SKYPE.getTitle())
                .append(": ")
                .append(contacts.get(ContactType.SKYPE))
                .append("\n");
        text.append(ContactType.EMAIL.getTitle())
                .append(": ")
                .append(contacts.get(ContactType.EMAIL))
                .append("\n");
        text.append(ContactType.LINKEDIN.getTitle())
                .append(": ")
                .append(contacts.get(ContactType.LINKEDIN))
                .append("\n");
        text.append(ContactType.GITHUB.getTitle())
                .append(": ")
                .append(contacts.get(ContactType.GITHUB))
                .append("\n");
        text.append(ContactType.STACKOVERFLOW.getTitle())
                .append(": ")
                .append(contacts.get(ContactType.STACKOVERFLOW))
                .append("\n");

        text.append(SectionType.OBJECTIVE.getTitle())
                .append(": ")
                .append(sections.get(SectionType.OBJECTIVE))
                .append("\n");
        text.append(SectionType.PERSONAL.getTitle())
                .append(": ")
                .append(sections.get(SectionType.PERSONAL))
                .append("\n");
        System.out.println(text.toString());

    }
}

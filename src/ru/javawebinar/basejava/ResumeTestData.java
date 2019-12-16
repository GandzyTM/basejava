package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("new Resume");
        Map<ContactType, String> contacts = resume.getContacts();
        Map<SectionType, Section> sections = resume.getSections();
//        Link link = new Link("Домашняя страница", "http://gkislin.ru/");
        Organization organization = new Organization("Java Online Projects", "http://javaops.ru/",
                LocalDate.of(2013, 3, 1), LocalDate.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.\n");
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, String.valueOf(new Link("Profile LinkedIn", "https://www.linkedin.com/in/gkislin")));
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");

        sections.put(SectionType.OBJECTIVE, new ContentSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n"));
        sections.put(SectionType.PERSONAL, new ContentSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n"));

        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");
        sections.put(SectionType.ACHIEVEMENT, new ListTextSection(achievement));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce\n");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,\n");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB\n");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).\n");
        sections.put(SectionType.QUALIFICATIONS, new ListTextSection(qualifications));

        List<Organization> experience = new ArrayList<>();
        experience.add(new Organization("Java Online Projects",
                "http://javaops.ru/",
                LocalDate.of(2013, 3, 1),
                LocalDate.now(),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок.\n"));
        experience.add(new Organization("Wrike", "https://www.wrike.com/",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)\n",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n"));
        sections.put(SectionType.EXPERIENCE, new OrganizationSection(experience));

//        StringBuilder text = new StringBuilder();
//        text.append(SectionType.ACHIEVEMENT.getTitle())
//                .append(": ")
//                .append(sections.get(SectionType.ACHIEVEMENT))
//                .append("\n");
//        text.append(SectionType.EXPERIENCE.getTitle())
//                .append(": ")
//                .append(sections.get(SectionType.EXPERIENCE))
//                .append("\n");
//
//        System.out.println(text.toString());

    }
}

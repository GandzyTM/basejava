package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

public class ResumeTestData {
    public static void contentResume(Resume resume) {

        Map<ContactType, String> contacts = resume.getContacts();
        Map<SectionType, Section> sections = resume.getSections();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, String.valueOf(new Link("Profile LinkedIn", "https://www.linkedin.com/in/gkislin")));
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");

        sections.put(SectionType.OBJECTIVE,
                new ContentSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL,
                new ContentSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        sections.put(SectionType.ACHIEVEMENT,
                new ListTextSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\",\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
                )
        );

        sections.put(SectionType.QUALIFICATIONS,
                new ListTextSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle",
                        "MySQL, SQLite, MS SQL, HSQLDB",
                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                        "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                        "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)."
                )
        );

        sections.put(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects",
                                "http://javaops.ru/",
                                new Position(LocalDate.of(2013, 3, 1),
                                        DateUtil.NOW,
                                        "Автор проекта",
                                        "Создание, организация и проведение Java онлайн проектов и стажировок."
                                )
                        ),
                        new Organization("Wrike", "https://www.wrike.com/",
                                new Position(LocalDate.of(2014, 10, 1),
                                        LocalDate.of(2016, 1, 1),
                                        "Старший разработчик (backend)",
                                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))
                )
        );

        sections.put(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", "rty",
                                new Position(1996, Month.JANUARY, 2000, Month.DECEMBER,
                                        "aspirant", "rty"),
                                new Position(2001, Month.MARCH, 2005, Month.JANUARY,
                                        "student", null)),
                        new Organization("Organization12", "http://Organization12.ru")
                )
        );
    }
}
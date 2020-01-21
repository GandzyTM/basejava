package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\Users\\pashkov-kv\\Documents\\GitHub\\basejava\\storage");
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume R1 = new Resume(UUID_1, "resume uuid1");
    private static final String UUID_2 = "uuid2";
    private static final Resume R2 = new Resume(UUID_2, "resume uuid2");
    private static final String UUID_3 = "uuid3";
    private static final Resume R3 = new Resume(UUID_3, "resume uuid3");
    private static final String UUID_4 = "uuid4";
    private static final Resume R4 = new Resume(UUID_4, "resume uuid4");

    static {
        R1.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        R1.addContact(ContactType.PHONE, "+7(921) 855-0482");
        R1.addSection(SectionType.OBJECTIVE, new ContentSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n"));
        R1.addSection(SectionType.PERSONAL, new ContentSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n"));
        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");
        R1.addSection(SectionType.ACHIEVEMENT, new ListTextSection(achievement));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce\n");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,\n");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB\n");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).\n");
        R1.addSection(SectionType.QUALIFICATIONS, new ListTextSection(qualifications));

        List<Organization> experience = new ArrayList<>();
        experience.add(new Organization("Java Online Projects",
                        "http://javaops.ru/",
                        new Position(LocalDate.of(2014, 3, 1),
                                LocalDate.of(2015, 6, 1),
                                "fgf", "asdfg"),
                        new Position(LocalDate.of(2015, 7, 1),
                                LocalDate.of(2016, 6, 1),
                                "second", "second descp"),
                        new Position(LocalDate.of(2016, 7, 1),
                                LocalDate.of(2017, 6, 1),
                                "third", "third descp")
                )
        );
        experience.add(new Organization("Java Online Projects 2",
                        "http://javaops.ru/ 2",
                        new Position(LocalDate.of(2011, 3, 1),
                                LocalDate.of(2015, 6, 1),
                                "first", "first desc"),
                        new Position(LocalDate.of(2015, 7, 1),
                                LocalDate.of(2016, 6, 1),
                                "second", "second desc")
                )
        );
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(experience));

        List<Organization> education = new ArrayList<>();
        experience.add(new Organization("University of Java Online Projects",
                        "http://university.javaops.ru/",
                        new Position(LocalDate.of(2014, 3, 1),
                                LocalDate.of(2015, 6, 1),
                                "first", "first descp"),
                        new Position(LocalDate.of(2015, 7, 1),
                                LocalDate.of(2016, 6, 1),
                                "second", "second descp"),
                        new Position(LocalDate.of(2016, 7, 1),
                                LocalDate.of(2017, 6, 1),
                                "third", "third descp")
                )
        );
        experience.add(new Organization("University of Java Online Projects 2",
                        "http://university.javaops.ru/ 2",
                        new Position(LocalDate.of(2011, 3, 1),
                                LocalDate.of(1999, 6, 1),
                                "first", "first desc"),
                        new Position(LocalDate.of(2015, 7, 1),
                                LocalDate.of(2014, 6, 1),
                                "second", "second desc")
                )
        );
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(education));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}

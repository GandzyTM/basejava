package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dataOutputStream.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            }
            //TODO sections
            Map<SectionType, Section> sections = resume.getSections();
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dataOutputStream.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dataOutputStream.writeUTF(((ContentSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        for (String item : ((ListTextSection) section).getItems()) {
                            dataOutputStream.writeUTF(item);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        for (Organization item : ((OrganizationSection) section).getOrganizationList()) {
                            dataOutputStream.writeUTF(item.getHomePage().getName());
                            dataOutputStream.writeUTF(item.getHomePage().getUrl());
                            List<Position> positions = new ArrayList<>();
                            for (Position p : positions) {
                                dataOutputStream.writeUTF(String.valueOf(p.getStartDate())); //LocalDate
                                dataOutputStream.writeUTF(String.valueOf(p.getEndDate()));   //LocalDate
                                dataOutputStream.writeUTF(p.getTitle());
                                dataOutputStream.writeUTF(p.getDescription());
                            }
                        }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF());
                resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF());
                //TODO sections
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                        resume.addSection(SectionType.OBJECTIVE, new ContentSection(dataInputStream.readUTF()));
                    case PERSONAL:
                        resume.addSection(SectionType.PERSONAL, new ContentSection(dataInputStream.readUTF()));
                    case ACHIEVEMENT:
                        resume.addSection(SectionType.ACHIEVEMENT, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.ACHIEVEMENT, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.ACHIEVEMENT, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.ACHIEVEMENT, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.ACHIEVEMENT, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.ACHIEVEMENT, new ContentSection(dataInputStream.readUTF()));
                    case QUALIFICATIONS:
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection(dataInputStream.readUTF()));
                    case EXPERIENCE:

                        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                                new Organization(dataInputStream.readUTF(), dataInputStream.readUTF(),
                                        new Position(LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt()),
                                                LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt()),
                                                dataInputStream.readUTF(), dataInputStream.readUTF()))
                        ));
                    case EDUCATION:
                        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
                                new Organization(dataInputStream.readUTF(), dataInputStream.readUTF(),
                                        new Position(LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt()),
                                                LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt()),
                                                dataInputStream.readUTF(), dataInputStream.readUTF()))
                        ));
                }
            }
            return resume;
        }
    }
}

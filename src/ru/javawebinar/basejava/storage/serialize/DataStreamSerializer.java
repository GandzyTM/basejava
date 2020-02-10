package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dataOutputStream, contacts.entrySet(), contactTypeStringEntry -> {
                dataOutputStream.writeUTF(contactTypeStringEntry.getKey().name());
                dataOutputStream.writeUTF(contactTypeStringEntry.getValue());
            });
            Map<SectionType, Section> sections = resume.getSections();
            writeCollection(dataOutputStream, sections.entrySet(), sectionTypeSectionEntry -> {
                SectionType sectionType = sectionTypeSectionEntry.getKey();
                Section section = sectionTypeSectionEntry.getValue();
                dataOutputStream.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dataOutputStream.writeUTF(((ContentSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dataOutputStream, ((ListTextSection) section).getItems(), dataOutputStream::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dataOutputStream, ((OrganizationSection) section).getOrganizationList(), organization -> {
                            dataOutputStream.writeUTF(organization.getHomePage().getName());
                            dataOutputStream.writeUTF(organization.getHomePage().getUrl());
                            writeCollection(dataOutputStream, organization.getPositions(), position -> {
                                writeLocalDate(dataOutputStream, position.getStartDate());
                                writeLocalDate(dataOutputStream, position.getEndDate());
                                dataOutputStream.writeUTF(position.getTitle());
                                dataOutputStream.writeUTF(position.getDescription());
                            });
                        });
                }
            });
        }
    }

    private void writeLocalDate(DataOutputStream dataOutputStream, LocalDate localDate) throws IOException {
        dataOutputStream.writeInt(localDate.getYear());
        dataOutputStream.writeInt(localDate.getMonth().getValue());
        dataOutputStream.writeInt(localDate.getDayOfMonth());
    }

    private <T> void writeCollection(DataOutputStream dataOutputStream, Collection<T> collection, Writer<T> writer) throws IOException {
        dataOutputStream.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private interface Writer<T> {
        void write(T var1) throws IOException;
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
            }
            int sizeSections = dataInputStream.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(sectionType, new ContentSection(dataInputStream.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int listTextSectionSize = dataInputStream.readInt();
                        List<String> listTextSections = new ArrayList<>(listTextSectionSize);
                        for (int j = 0; j < listTextSectionSize; j++) {
                            listTextSections.add(dataInputStream.readUTF());
                        }
                        resume.addSection(sectionType, new ListTextSection(listTextSections));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int listOrganizationSectionSize = dataInputStream.readInt();
                        List<Organization> listOrganizationSection = new ArrayList<>(listOrganizationSectionSize);
                        for (int j = 0; j < listOrganizationSectionSize; j++) {
                            String name = dataInputStream.readUTF();
                            String url = dataInputStream.readUTF();
                            Link link = new Link(name, url);
                            int positionsSize = dataInputStream.readInt();
                            List<Position> positionsList = new ArrayList<>();
                            for (int k = 0; k < positionsSize; k++) {
                                positionsList.add(new Position(readLocalDate(dataInputStream),
                                        readLocalDate(dataInputStream),
                                        dataInputStream.readUTF(), dataInputStream.readUTF()
                                ));
                            }
                            listOrganizationSection.add(new Organization(link, positionsList));
                        }
                        resume.addSection(sectionType, new OrganizationSection(listOrganizationSection));
                        break;
                }
            }
            return resume;
        }
    }

    private LocalDate readLocalDate(DataInputStream dataInputStream) throws IOException {
        return LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
    }
}

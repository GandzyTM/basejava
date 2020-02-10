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

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readElements(dataInputStream, () -> resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF()));
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
                        resume.addSection(sectionType, new ListTextSection(readCollection(dataInputStream, dataInputStream::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.addSection(sectionType, new OrganizationSection(
                                readCollection(dataInputStream, () -> new Organization(
                                        new Link(dataInputStream.readUTF(), dataInputStream.readUTF()),
                                        readCollection(dataInputStream, () -> new Position(
                                                readLocalDate(dataInputStream), readLocalDate(dataInputStream), dataInputStream.readUTF(), dataInputStream.readUTF()
                                        ))
                                ))
                        ));
                        break;
                }
            }
            return resume;
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

    private LocalDate readLocalDate(DataInputStream dataInputStream) throws IOException {
        return LocalDate.of(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
    }

    private <T> List<T> readCollection(DataInputStream dataInputStream, Reader<T> reader) throws IOException {
        int size = dataInputStream.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private void readElements(DataInputStream dataInputStream, ElementReader elementReader) throws IOException {
        int size = dataInputStream.readInt();
        for (int i = 0; i < size; i++) {
            elementReader.read();
        }
    }

    private interface Reader<T> {
        T read() throws IOException;
    }

    private interface ElementReader {
        void read() throws IOException;
    }
}

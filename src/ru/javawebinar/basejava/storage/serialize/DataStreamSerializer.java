package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;

import java.io.*;
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
                dataOutputStream.writeUTF(String.valueOf(((ContentSection) section).getContent()));

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
            }
            //TODO sections
            return resume;
        }
    }
}

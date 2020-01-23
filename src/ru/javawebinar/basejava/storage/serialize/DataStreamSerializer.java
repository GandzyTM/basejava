package ru.javawebinar.basejava.storage.serialize;

import javafx.geometry.Pos;
import ru.javawebinar.basejava.model.*;

import java.io.*;
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
                    case ACHIEVEMENT:
                        dataOutputStream.writeUTF(((ContentSection) section).getContent());
                        break;
                    case QUALIFICATIONS:
                        List<String> achievement = new ArrayList<>();
                        for (String item : ((ListTextSection) achievement).getItems()) {
                            dataOutputStream.writeUTF(item);
                        }
                        break;
                    case EXPERIENCE:
                        List<Organization> experience = new ArrayList<>();
                        for (Organization item : ((OrganizationSection) experience).getOrganizationList()) {
                            dataOutputStream.writeUTF(item.toString());

                            List<Position> positions = new ArrayList<>();
                            for (Position p : positions) {

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
            }
            //TODO sections
            return resume;
        }
    }
}

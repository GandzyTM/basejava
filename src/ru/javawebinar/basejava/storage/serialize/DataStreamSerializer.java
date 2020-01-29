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
            dataOutputStream.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dataOutputStream.writeUTF(entry.getKey().name());
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dataOutputStream.writeUTF(((ContentSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dataOutputStream.writeInt(((ListTextSection) section).getItems().size());
                        for (String item : ((ListTextSection) section).getItems()) {
                            dataOutputStream.writeUTF(item);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dataOutputStream.writeInt(((OrganizationSection) section).getOrganizationList().size());
                        for (Organization item : ((OrganizationSection) section).getOrganizationList()) {
                            dataOutputStream.writeUTF(item.getHomePage().getName());
                            dataOutputStream.writeUTF(item.getHomePage().getUrl());
                            dataOutputStream.writeInt(item.getPositions().size());
                            for (Position position : item.getPositions()) {
                                dataOutputStream.writeInt(position.getStartDate().getYear());
                                dataOutputStream.writeInt(position.getStartDate().getMonth().getValue());
                                dataOutputStream.writeInt(position.getStartDate().getDayOfMonth());
                                dataOutputStream.writeInt(position.getEndDate().getYear());
                                dataOutputStream.writeInt(position.getEndDate().getMonth().getValue());
                                dataOutputStream.writeInt(position.getEndDate().getDayOfMonth());
                                dataOutputStream.writeUTF(position.getTitle());
                                dataOutputStream.writeUTF(position.getDescription());
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

            // TODO sections
            int sizeSections = dataInputStream.readInt();
            for (int i = 0; i < sizeSections; i++) {
                String sectionType = dataInputStream.readUTF();
                switch (sectionType) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        resume.addSection(SectionType.valueOf(sectionType), new ContentSection(dataInputStream.readUTF()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        int listTextSectionSize = dataInputStream.readInt();
                        List<String> listTextSections = new ArrayList<>(listTextSectionSize);
                        for (int j = 0; j < listTextSectionSize; j++) {
                            listTextSections.add(dataInputStream.readUTF());
                        }
                        resume.addSection(SectionType.valueOf(sectionType), new ListTextSection(listTextSections));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        int organizationListSize = dataInputStream.readInt();
                        for (int j = 0; j < organizationListSize; j++) {
                            String name = dataInputStream.readUTF();
                            String url = dataInputStream.readUTF();
                            int positionsSize = dataInputStream.readInt();
                            for (int k = 0; k < positionsSize; k++) {
                                int startDateYear = dataInputStream.readInt();
                                int startDateMonth = dataInputStream.readInt();
                                int startDateDay = dataInputStream.readInt();
                                int stopDateYear = dataInputStream.readInt();
                                int stopDateMonth = dataInputStream.readInt();
                                int stopDateDay = dataInputStream.readInt();
                                String title = dataInputStream.readUTF();
                                String description = dataInputStream.readUTF();
                                resume.addSection(SectionType.valueOf(sectionType), new OrganizationSection(
                                        new Organization(name, url,
                                                new Position(LocalDate.of(startDateYear, startDateMonth, startDateDay),
                                                        LocalDate.of(stopDateYear, stopDateMonth, stopDateDay),
                                                        title, description
                                                )
                                        )
                                ));
                            }
                        }
                        break;
                }
            }
            return resume;
        }
    }


}

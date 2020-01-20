package ru.javawebinar.basejava.storage.serialize;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements Serializer {
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class, OrganizationSection.class, Position.class,
                Link.class, ListTextSection.class, ContentSection.class
        );
    }

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);
        }
    }
}

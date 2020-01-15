package ru.javawebinar.basejava;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MainFindFilesNIO {
    public static void main(String[] args) throws IOException {
        Path parent = Paths.get("./src");
        Files.walkFileTree(parent, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                int count = dir.getNameCount() - parent.getNameCount() + 1;
                count += dir.getFileName().toString().length();

                String text = String.format("%" + count + "s", dir.getFileName());
                text = text.replaceAll("[\\s]", "-");
                System.out.println(text);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println("visitFile: " + file.getFileName());
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

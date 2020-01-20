package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFindFiles {
    public static void main(String[] args) {
        File file = new File("./src");
        showFilesDirectories(file, "");
    }

    public static void showFilesDirectories(File file, String offset) {
        File[] files = file.listFiles();
        for (File p : files) {
            if (p.isFile()) {
                System.out.println(offset + "File: " + p.getName());
            } else if (p.isDirectory()) {
                System.out.println(offset + "Directory: " + p.getName());
                showFilesDirectories(p, offset + "    ");
            }
        }
    }
}

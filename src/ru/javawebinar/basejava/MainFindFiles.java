package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFindFiles {
    public static void main(String[] args) {
        File file = new File("./src");
        showFilesDirectories(file);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
    }

    public static void showFilesDirectories(File file) {
        File[] files = file.listFiles();
        for (File p : files) {
            if (!p.isDirectory()) {
                System.out.println(" --> File: " + p.getName() + " in dir " + p.getPath());
            } else {
                System.out.println("Directory: " + p.getName() + " in dir " + p.getPath());
                showFilesDirectories(p);
            }
        }
    }
}

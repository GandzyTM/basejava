package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        field.get(resume);
        field.set(resume, "new_uuid");
        System.out.println(resume);
        // TODO invoke resume.toString via reflection
        Class<Resume> resumeClass = Resume.class;
        Method method = resumeClass.getDeclaredMethod("toString", null);
        method.invoke(resumeClass, null);
        System.out.println(resumeClass.toString());
        // TODO
        Method method1 = resume.getClass().getMethod("toString", null);
        method1.invoke(resume, null);
        System.out.println(resume.toString());
        // TODO end

    }
}

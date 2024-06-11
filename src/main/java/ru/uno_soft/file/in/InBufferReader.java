package ru.uno_soft.file.in;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class InBufferReader {
    public static List<String> readFromFile(File file) {
        try (var fileReader = new FileReader(file);
             var bufferedReader = new BufferedReader(fileReader)) {

            System.out.println("Чтение строк из файла...");
            return bufferedReader.lines().distinct().toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
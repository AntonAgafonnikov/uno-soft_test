package ru.uno_soft.file.in;

import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static ru.uno_soft.repository.ValueRepository.getValueGroupHashMap;
import static ru.uno_soft.service.ProcessLineService.processLine;

public class InBufferReader {
    public static void readFromFile(File file) {
        try (
                var fileReader = new FileReader(file);
                var bufferedReader = new BufferedReader(fileReader)
        ) {
            System.out.println("Чтение строк из файла...");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                var isCorrectValuesArray = line.replace(";", "").replace("\"", "");
                if (isCorrectValuesArray.isEmpty()) continue;
                processLine(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Чтение строк завершено.");
    }
}
//    public static void readFromFile(File file) {
//        try (
//                var fileReader = new FileReader(file);
//                var bufferedReader = new BufferedReader(fileReader)
//        ) {
//            System.out.println("Чтение строк из файла...");
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null) {
//                var isCorrectValuesArray = line.replace(";", "").replace("\"", "");
//                if (isCorrectValuesArray.isEmpty()) continue;
//
//                processLine(line);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        getValueGroupHashMap().clear();
//        System.out.println("Чтение строк завершено.");
//    }
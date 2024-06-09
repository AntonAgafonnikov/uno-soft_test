package ru.uno_soft;

import java.io.File;

import static ru.uno_soft.file.in.InBufferReader.readFromFile;
import static ru.uno_soft.service.SortGroupsService.sortGroups;

public class Main {
    //-Xmx1G
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //readFromFile(new File("src/main/resources/lng.txt"));
        readFromFile(new File("src/main/resources/lng-big.csv"));

        sortGroups();
        //writeToFile(new File("src/main/resources/result.txt"));

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startTime) / 1000 + "с");

    }
}
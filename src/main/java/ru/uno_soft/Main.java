package ru.uno_soft;

import java.io.File;
import java.util.List;

import static ru.uno_soft.file.in.InBufferReader.readFromFile;
import static ru.uno_soft.file.out.OutBufferWriter.writeToFile;
import static ru.uno_soft.service.ProcessLineService.processLine;
import static ru.uno_soft.service.SortGroupsService.sortGroups;

public class Main {
    private static final String PATH_OUTPUT_FILE = "result.txt";
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        if (args.length < 1) {
            System.err.println("Invalid arguments");
            return;
        }
        var fileInput = new File(args[0]);

        List<String> linesFromFile = readFromFile(fileInput);
        processLine(linesFromFile);
        sortGroups();
        writeToFile(new File(PATH_OUTPUT_FILE));

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - startTime) / 1000 + "с");
    }
}
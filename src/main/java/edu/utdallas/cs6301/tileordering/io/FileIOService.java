package edu.utdallas.cs6301.tileordering.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

public class FileIOService implements IOService {
    private final String input;

    private int stepCounter;

    public FileIOService(String inputFile) throws IOException {
        List<String> inputLines = Files.readAllLines(FileSystems.getDefault().getPath(inputFile));

        input = inputLines.get(0).trim();
    }

    @Override
    public String getInputString() {
        return input;
    }

    @Override
    public void write(String s) {
        System.out.printf("Step %d: %s%n", stepCounter++, s);
    }
}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.System;
import java.util.ArrayList;
import java.util.Scanner;

public class FileAnalyzer {

    private final ArrayList<String> tokens;
    private final File file;

    public FileAnalyzer(String fileName) {
        tokens = new ArrayList<>();
        this.file = new File(fileName);

        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            while (scanner.hasNext()) {
                String token = scanner.next().trim();
                if (!token.isBlank()) tokens.add(token);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public int wordCount() {
        return tokens.size();
    }

    public String findLargestWord() {
        String largestWord = tokens.getFirst();
        for (String token : tokens) {
            if (largestWord.length() < token.length())
                largestWord = token;
        }
        return largestWord;
    }

    public String findShortestWord() {
        String shortestWord = tokens.getFirst();
        for (String token : tokens) {
            if (shortestWord.length() > token.length())
                shortestWord = token;
        }
        return shortestWord;
    }

    public int averageWordLength() {
        int avg = 0;
        for (String token : tokens) avg += token.length();
        return avg;
    }

    public String getFileName() {
        return file.getName();
    }
}

package UsingCallable;

import java.util.Random;
import java.util.concurrent.Callable;

public class FileAnalyzerRunnable implements Callable<String> {

    private final FileAnalyzer fileAnalyzer;

    public FileAnalyzerRunnable(String file) {
        this.fileAnalyzer = new FileAnalyzer(file);
    }

    @Override
    public String call() {
        String largest = fileAnalyzer.findLargestWord();
        String shortest = fileAnalyzer.findShortestWord();

        // for simulating
        try {
            Thread.sleep(new Random().nextInt(1000, 5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return snedInfo(shortest, largest);
    }

    private String snedInfo(String shortest, String largest) {
        return "====================================================\n" +
                "= Worker: " + Thread.currentThread().getName() + ".\n" +
                "= In file: " + fileAnalyzer.getFileName() + ".\n" +
                "= Words Count: " + fileAnalyzer.wordCount() + ".\n" +
                "= Longest word and its length: " + largest + " (len: " + largest.length() + ").\n" +
                "= Shortest word and its length: " + shortest + " (len: " + shortest.length() + ").\n" +
                "= All words length average: " + fileAnalyzer.averageWordLength() + ".\n" +
                "====================================================\n";
    }
}

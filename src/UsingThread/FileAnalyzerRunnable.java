package UsingThread;

import java.util.Random;
import java.util.concurrent.Callable;

public class FileAnalyzerRunnable implements Runnable {

    private final FileAnalyzer fileAnalyzer;

    public FileAnalyzerRunnable(String file) {
        this.fileAnalyzer = new FileAnalyzer(file);
    }

    @Override
    public void run() {
        String largest = fileAnalyzer.findLargestWord();
        String shortest = fileAnalyzer.findShortestWord();

        // for simulating
        try {
            Thread.sleep(new Random().nextInt(1000, 5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        snedInfo(shortest, largest);
    }

    private void snedInfo(String shortest, String largest) {
        System.out.printf("%s", "====================================================\n" +
                "= Worker: " + Thread.currentThread().getName() + ".\n" +
                "= In file: " + fileAnalyzer.getFileName() + ".\n" +
                "= Words Count: " + fileAnalyzer.wordCount() + ".\n" +
                "= Longest word and its length: " + largest + " (len: " + largest.length() + ").\n" +
                "= Shortest word and its length: " + shortest + " (len: " + shortest.length() + ").\n" +
                "= All words length average: " + fileAnalyzer.averageWordLength() + ".\n" +
                "====================================================\n");
    }
}

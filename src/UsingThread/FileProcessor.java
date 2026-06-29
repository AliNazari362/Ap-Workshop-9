package UsingThread;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

public class FileProcessor {

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream =
                     Files.newDirectoryStream(Path.of("./assets"))) {
            for (Path path : directoryStream) {
                threads.add(new Thread(new FileAnalyzerRunnable(path.toString())));
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("Just a moment...");
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

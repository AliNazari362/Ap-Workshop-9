import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

public class FileProcessor {

    public static void main(String[] args) {

        HashMap<String, Future<String>> futureHashMap;
        ExecutorService executorService = Executors.newCachedThreadPool();
        futureHashMap = new HashMap<>();
        try (DirectoryStream<Path> directoryStream =
                     Files.newDirectoryStream(Path.of("./assets"))) {
            for (Path path : directoryStream) {
                Future<String> res = executorService.submit(new FileAnalyzerRunnable(path.toString()));
                futureHashMap.put(path.getFileName().toString(), res);
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.print("Write number of file (-1 for scape): ");
            number = scanner.nextInt();
            if (number == -1) {
                executorService.shutdown();
                executorService.close();
                break;
            };

            Future<String> future = futureHashMap.get("file_" + number + ".txt");
            if (future == null) {
                System.out.println("File not found. try again.");
                continue;
            }

            boolean showAlert = false;
            while (!future.isDone()) {
                if (!showAlert) {
                    System.out.println("Just a moment...");
                    showAlert = true;
                }
            }

            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        while (true);
    }
}

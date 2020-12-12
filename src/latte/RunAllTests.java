package latte;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RunAllTests {
    public static void main(String args[]) throws Exception {
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("pwd");
        try (Stream<Path> paths = Files.walk(Paths.get("../../good/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

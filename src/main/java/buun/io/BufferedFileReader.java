package buun.io;

import buun.exception.ExceptionCatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.function.Consumer;

public class BufferedFileReader {

    private final File file;

    public BufferedFileReader(File file) {
        this.file = file;
    }

    private BufferedReader createReader() {
        return new ExceptionCatcher<>(() -> new BufferedReader(new FileReader(file))).tryCatch();
    }

    public void readAll(Consumer<String> consumer) {
        new ExceptionCatcher<>(() -> {
            String line;
            BufferedReader reader = createReader();
            while ((line = reader.readLine()) != null) consumer.accept(line);
        }).tryCatch();
    }

}

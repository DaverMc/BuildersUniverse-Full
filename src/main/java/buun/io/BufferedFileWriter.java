package buun.io;

import buun.exception.ExceptionCatcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BufferedFileWriter {


    private final File file;
    private final boolean append;

    public BufferedFileWriter(File file, boolean append) {
        this.file = file;
        this.append = append;
    }

    private BufferedWriter createWriter() {
        return new ExceptionCatcher<>(() -> new BufferedWriter(new FileWriter(file, append))).tryCatch();
    }

    public void writeAll(List<String> lines) {
        new ExceptionCatcher<Object, IOException, BufferedWriter>(writer -> {
            lines.forEach(line -> writeLine(writer, line));
            writer.flush();
            return null;
        }).tryCatchRessource(this::createWriter);
    }

    private void writeLine(BufferedWriter writer, String line) {
        new ExceptionCatcher<>(() -> {
            writer.write(line);
            writer.newLine();
        }).tryCatch();
    }

}

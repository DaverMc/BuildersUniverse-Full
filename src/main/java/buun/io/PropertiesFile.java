package buun.io;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesFile {

    private final Map<String, String> values;
    private final File file;

    public PropertiesFile(File dir, String name) {
        this.file = FileUtils.createFile(dir, name + ".properties");
        this.values = new HashMap<>();
        loadValues();
    }

    public void loadValues() {
        BufferedFileReader reader = new BufferedFileReader(file);
        reader.readAll(line -> {
            if (!line.contains("= ")) return;
            String[] array = line.split("= ");
            values.put(array[0], line.replaceAll(array[0] + "= ", ""));
        });
    }

    public String getValue(String path) {
        return values.get(path);
    }

    public void rewrite(List<String> lines) {
        new BufferedFileWriter(this.file, false).writeAll(lines);
    }

}

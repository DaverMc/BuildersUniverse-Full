package buun.io;

import buun.exception.ExceptionCatcher;

import java.io.*;

public class FileUtils {

    private FileUtils(){}

    public static File createFile(File file) {
        if (file.exists()) return file;
        if (file.getName().contains(".")) {
            file.getParentFile().mkdirs();
            new ExceptionCatcher<>(file::createNewFile).tryCatch();
            return file;
        }
        file.mkdirs();
        return file;
    }

    public static File createFile(File dir, String name) {
        return createFile(new File(dir, name));
    }

    public static boolean deleteFile(File file) {
        if (!file.exists()) return false;
        if (file.isFile()) return file.delete();
        File[] contents = file.listFiles();
        if (contents == null || contents.length == 0) return file.delete();
        for (File content : contents) if (!deleteFile(content)) return false;
        return file.delete();
    }

    public static void copyContent(File source, File dest) {
        if (source.isDirectory()) {
            if (!dest.exists()) dest.mkdir();
            String[] files = source.list();
            if (files == null) return;
            for (String fileName : files) {
                File srcFile = new File(source, fileName);
                File destFile = new File(dest, fileName);
                copyContent(srcFile, destFile);
            }
        } else fileCopy(source, dest);
    }

    private static void fileCopy(File source, File dest) {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) out.write(buffer, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

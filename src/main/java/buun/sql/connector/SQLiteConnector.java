package buun.sql.connector;

import buun.exception.ExceptionCatcher;
import buun.io.FileUtils;

import java.io.File;
import java.sql.DriverManager;

public class SQLiteConnector extends DatabaseConnector{

    public SQLiteConnector(File dir, String name){
        File file = FileUtils.createFile(dir, name + ".db");
        setUrl("jdbc:sqlite:" + file.getAbsolutePath());
    }

    @Override
    public boolean connect() {
        if(isConnected()) return false;
        setConnection(new ExceptionCatcher<>(() -> DriverManager.getConnection(getUrl())).tryCatch());
        return isConnected();
    }
}

package buun.sql.connector;

import buun.exception.ExceptionCatcher;

import java.sql.DriverManager;

public class MySQLConnector extends DatabaseConnector{

    @Override
    public boolean connect() {
        if(isConnected()) return false;
        setConnection(new ExceptionCatcher<>(() -> DriverManager.getConnection(getUrl(), getUserName(), getPassword())).tryCatch());
        return isConnected();
    }
}

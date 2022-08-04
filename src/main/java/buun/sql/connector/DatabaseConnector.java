package buun.sql.connector;

import buun.exception.ExceptionCatcher;

import java.sql.Connection;

public abstract class DatabaseConnector {

    private Connection connection;
    private boolean connected;

    private String url;
    private String userName;
    private String password;

    protected DatabaseConnector(){
        this.connected = false;
    }

    public abstract boolean connect();

    public boolean disconnect(){
        if(!connected) return false;
        new ExceptionCatcher<>(() -> {
            connection.close();
            connection = null;
            connected = false;
        }).tryCatch();
        return !connected;
    }

    public boolean isConnected(){
        return this.connected;
    }

    public DatabaseConnector setUrl(String url){
        this.url = url;
        return this;
    }

    public DatabaseConnector setUserName(String userName){
        this.userName = userName;
        return this;
    }

    public DatabaseConnector setPassword(String password){
        this.password = password;
        return this;
    }

    public String getUrl(){
        return this.url;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }

    public Connection getConnection(){
        return this.connection;
    }

    protected void setConnection(Connection connection){
        this.connection = connection;
        this.connected = true;
    }
}

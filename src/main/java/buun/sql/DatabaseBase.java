package buun.sql;

import buun.exception.ExceptionCatcher;
import buun.sql.connector.DatabaseConnector;
import buun.sql.resultconsumer.ResultSetConsumer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseBase implements Database{

    private final DatabaseConnector connector;

    public DatabaseBase(DatabaseConnector connector){
        this.connector = connector;
    }

    @Override
    public void execute(SQLCommand sqlCommand) {
        execute(sqlCommand, null);
    }

    @Override
    public <T> T execute(SQLCommand sqlCommand, ResultSetConsumer<T> resultConsumer) {
        if(!connector.isConnected()) return null;
        return new ExceptionCatcher<T, SQLException, PreparedStatement>
                (statement -> {
            if(!sqlCommand.isResultBased()){
                statement.execute();
                return null;
            }
            ResultSet resultSet = statement.executeQuery();
            return (resultSet == null || resultConsumer == null)? null : resultConsumer.accept(resultSet);
        }).tryCatchRessource(() -> connector.getConnection().prepareStatement(sqlCommand.toString()));
    }

    @Override
    public DatabaseConnector getConnector() {
        return this.connector;
    }
}

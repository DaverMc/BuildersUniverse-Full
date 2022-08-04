package buun.sql;

import buun.sql.connector.DatabaseConnector;
import buun.sql.resultconsumer.ResultSetConsumer;

public interface Database {

    void execute(SQLCommand sqlCommand);

    <T> T execute(SQLCommand sqlCommand, ResultSetConsumer<T> resultConsumer);

    DatabaseConnector getConnector();


}

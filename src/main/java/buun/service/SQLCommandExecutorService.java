package buun.service;

import buun.BuildersUniverse;
import buun.sql.SQLCommand;

public class SQLCommandExecutorService extends ExecutorService<Object, SQLCommand> {

    protected SQLCommandExecutorService() {
        super("Database-Worker", 60000);
    }

    @Override
    public void accept(SQLCommand sqlCommand) {
        if(BuildersUniverse.getDatabase() == null) return;
        BuildersUniverse.getDatabase().execute(sqlCommand);
    }
}

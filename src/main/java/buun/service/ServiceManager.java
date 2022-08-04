package buun.service;

public class ServiceManager {

    private final SQLCommandExecutorService sqlCommandService;

    public ServiceManager(){
        this.sqlCommandService = new SQLCommandExecutorService();
    }

    public SQLCommandExecutorService getSqlCommandService(){
        return this.sqlCommandService;
    }

}

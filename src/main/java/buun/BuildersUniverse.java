package buun;

import buun.lang.LanguageManager;
import buun.service.ServiceManager;
import buun.sql.Database;
import buun.sql.DatabaseBase;
import buun.sql.connector.SQLiteConnector;

import java.io.File;

public class BuildersUniverse{

    private static BuildersUniverse instance;

    private final Database database;
    private final ServiceManager serviceManager;
    private final LanguageManager languageManager;
    private final File pluginDir;

    private BuildersUniverse(File pluginDir){
        this.pluginDir = pluginDir;
        this.database = new DatabaseBase(new SQLiteConnector(pluginDir, "server-data"));
        this.serviceManager = new ServiceManager();
        this.languageManager = new LanguageManager(pluginDir);
    }

    public void enable() {}

    public void disable() {

    }

    public static BuildersUniverse enableBuildersUniverse(File pluginDir){
        BuildersUniverse universe = new BuildersUniverse(pluginDir);
        instance = universe;
        universe.enable();
        return universe;
    }

    public static Database getDatabase(){
        return instance.database;
    }

    public static ServiceManager getServiceManager(){
        return instance.serviceManager;
    }

    public static LanguageManager getLanguageManager(){
        return instance.languageManager;
    }
}

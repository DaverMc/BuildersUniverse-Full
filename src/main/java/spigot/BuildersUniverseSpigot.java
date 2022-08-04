package spigot;

import buun.BuildersUniverse;

public class BuildersUniverseSpigot {

    private BuildersUniverse buildersUniverse;

    public void onEnable(){
        buildersUniverse = BuildersUniverse.enableBuildersUniverse(null);
        buildersUniverse.enable();
    }

    public void onDisable(){
        buildersUniverse.disable();
    }

}

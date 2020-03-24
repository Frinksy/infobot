package infobot;


import org.bukkit.plugin.java.JavaPlugin;



public class App extends JavaPlugin 
{   

    @Override
    public void onEnable () {
        getLogger().info("Hello SpigotMC!!");
        this.getCommand("list-players").setExecutor(new CommandListPlayers());
        this.getCommand("genmap").setExecutor(new MapMaker());
        getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        getServer().getPluginManager().registerEvents(new DiamondFinderListener(), this);

    }

    @Override
    public void onDisable () {
        getLogger().info("See you again, SpigotMC!");
    }
    

}

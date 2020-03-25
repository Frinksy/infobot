package infobot;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;

import infobot.discord.DiscordBot;
import infobot.mc.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;




public class App extends JavaPlugin 
{   

    @Override
    public void onEnable () {
        getLogger().info("Hello SpigotMC!!");
        this.getCommand("list-players").setExecutor(new CommandListPlayers());
        this.getCommand("genmap").setExecutor(new MapMaker());
        getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        getServer().getPluginManager().registerEvents(new DiamondFinderListener(), this);
        
        
        JDA jda = null;

        
        try {
            
            jda = new JDABuilder("MzM3NjU2NzM1MzQwOTUzNjA2.XnpCNA.barydHEjcEjmddrJz3JJtyn_QRk").addEventListeners(new DiscordBot())
            .setActivity(Activity.playing("Type !ping"))
            .build();

        } catch (LoginException e) {
            Logger logger = this.getLogger();
            logger.log(Level.SEVERE,"Could not login to discord!");
        }

        getServer().getPluginManager().registerEvents(new ChatListener(jda), this);

    }

    @Override
    public void onDisable () {
        getLogger().info("See you again, SpigotMC!");
    }
    

}

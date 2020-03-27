package infobot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import infobot.discord.DiscordBot;
import infobot.mc.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;





public class App extends JavaPlugin 
{   

    @Override
    public void onEnable () {
        getLogger().info("Hello SpigotMC!!");
        this.getCommand("list-players").setExecutor(new CommandListPlayers());
        getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        getServer().getPluginManager().registerEvents(new DiamondFinderListener(), this);
        
        
        JDA jda = null;
        String token = null;
        String channelId = null;
        FileConfiguration fconfig = this.getConfig();

        // Save default config if none exists
        saveDefaultConfig();

        if (fconfig.contains("botToken")) {
            token = fconfig.getString("botToken");
            if (token.equals("0")) {
                this.getLogger().log(Level.SEVERE, "NO TOKEN SPECIFIED IN CONFIG FILE");
                token = null;
            }
        } else {
            this.getLogger().log(Level.SEVERE, "NO TOKEN SPECIFIED IN CONFIG FILE");
        }

        if (fconfig.contains("channelId")) {
            channelId = fconfig.getString("channelId");
            if (channelId.equals("0")) {
                this.getLogger().log(Level.SEVERE, "NO CHANNEL ID SPECIFIED IN CONFIG FILE");
                channelId = null;
            }
        } else {
            this.getLogger().log(Level.SEVERE, "NO CHANNEL ID SPECIFIED IN CONFIG FILE");
        }
        
        try {
            
            Collection<GatewayIntent> intents = new ArrayList<GatewayIntent>();
            intents.add(GatewayIntent.GUILD_MESSAGES);
            Collection<CacheFlag> disabled_caches = new ArrayList<CacheFlag>();
            disabled_caches.add(CacheFlag.ACTIVITY);
            disabled_caches.add(CacheFlag.CLIENT_STATUS);
            disabled_caches.add(CacheFlag.MEMBER_OVERRIDES);
            disabled_caches.add(CacheFlag.VOICE_STATE);
            disabled_caches.add(CacheFlag.EMOTE);

            jda =  JDABuilder
            .create(token, intents)
            .setMemberCachePolicy(MemberCachePolicy.NONE)
            .disableCache(disabled_caches)
            .addEventListeners(new DiscordBot(channelId))
            .setActivity(Activity.playing("Type !ping"))
            .build();

        } catch (LoginException e) {
            Logger logger = this.getLogger();
            logger.log(Level.SEVERE,"Could not login to discord!");
        }

        getServer().getPluginManager().registerEvents(new ChatListener(jda, channelId), this);

    }

    @Override
    public void onDisable () {
        getLogger().info("See you again, SpigotMC!");
    }
    

}

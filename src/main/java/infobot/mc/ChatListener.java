package infobot.mc;

import java.util.concurrent.TimeUnit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public class ChatListener implements Listener {

    JDA jda;
    TextChannel channel;
    public ChatListener(JDA jda, String channelId) {
        super();
        this.jda = jda;
        

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        channel = jda.getTextChannelById(channelId);

    }


    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        
        channel.sendMessage("```ini\n[" + event.getPlayer().getName() +"] " + event.getMessage() + "\n```").queue();
    }


}
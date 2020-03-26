package infobot.discord;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordBot extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        if (msg.getAuthor().isBot()) {
            return;
        }
        if (msg.getContentRaw().equals("!ping"))
        {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                   .queue(response /* => Message */ -> {
                       response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                   });
        } else if (msg.getContentRaw().equals("!list-players")) {
            listPlayers(event);
        } else {
            Bukkit.broadcastMessage("§9(discord)§r<" + event.getAuthor().getName() + "> " + msg.getContentRaw());
        }

    }


    public void listPlayers(MessageReceivedEvent event) {

        List<World> worlds = Bukkit.getWorlds();
        String message = "```ini\n";

        for (World w : worlds) {
            String wname = "["  + w.getName() + "]";

            List<Player> players = w.getPlayers();
    
            String output = new String("   ");

            Iterator<Player> playerIter = players.iterator();

            if (playerIter.hasNext()) {
                output += playerIter.next().getDisplayName();
            }

            while (playerIter.hasNext()) {
                output += ", ";
                output += playerIter.next().getDisplayName();
            }

            message += wname + "  \n" +  output + "  \n";       

        }

        message += "```";

        event.getChannel().sendMessage(message).queue();


    }


}
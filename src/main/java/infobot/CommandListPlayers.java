package infobot;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListPlayers implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
        // Get list of players in the world
        List<World> worlds = Bukkit.getWorlds();

        for (World w : worlds) {
            String wname = "§c"  + w.getName();

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
            

            sender.sendMessage(wname + ":");
            sender.sendMessage(output);

        }


        return false;
    }

}
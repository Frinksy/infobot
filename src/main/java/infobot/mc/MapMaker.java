package infobot.mc;

import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MapMaker implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileOutputStream out = null;
        try {

            World world = null;
            Location loc = null;

            if (sender instanceof Player) {
                world = ((Player)sender).getWorld();
                loc = ((Player)sender).getLocation();
            } else {
                sender.sendMessage("This command can only be used as a Player.");
                return false;
            }
            
            // Prep file
            out = new FileOutputStream("map.ppm");
            writeString(out, "P3\n");
            writeString(out, "# Map of world\n");
            writeString(out, "128 128\n");
            writeString(out, "255\n");


            int x = loc.getBlockX();
            int z = loc.getBlockZ();

            
            for (int i = 0; i < 128; i++) {
                for (int j = 0; j < 128; j++) {
                    switch (world.getHighestBlockAt(x+i, z+j).getType())   {

                        case GRASS_BLOCK:
                            writeString(out, "0 255 0 ");
                            break;

                        default:
                            writeString(out, "255 0 255 ");
                            break;
                    }              
                }
                out.write('\n');
            }

            out.close();
        }
        catch (IOException e) {
            sender.sendMessage("There was an error creating your map. Please contact the server administrator.");
            return true;
        }


        
        sender.sendMessage("Map generated");
        return true;
    }


    private static void writeString (FileOutputStream file, String str) throws IOException {

        try {
            for (int i = 0; i < str.length(); i++) {
                file.write(str.charAt(i));
            }
        }
        catch (IOException e) {
            throw e;
        }

    }


}
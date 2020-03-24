package infobot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DiamondFinderListener implements Listener {


    @EventHandler
    public void onBlockBreakEvent (BlockBreakEvent event) {
        Block broken_block = event.getBlock();
        if (broken_block.getType() == Material.DIAMOND_ORE) {
            Bukkit.broadcastMessage(event.getPlayer().getDisplayName() + "has mined a diamond ore!");
        }
    }

}
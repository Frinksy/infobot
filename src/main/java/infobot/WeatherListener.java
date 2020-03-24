package infobot;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            Bukkit.broadcastMessage("It is now raining!");
        }else {
            Bukkit.broadcastMessage("The rain has cleared up!");
        }
    }

}
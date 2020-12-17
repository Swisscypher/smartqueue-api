package ch.swisscypher.smartqueue.spigot.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class SmartQueue {

    public abstract void addPlayer(Player player, String queue);
    public abstract void removePlayerFromQueue(Player player, String queue);
    public abstract void removePlayerFromAllQueue(Player player);

    private static SmartQueue instance;

    public static SmartQueue getInstance() {
        if(instance == null) {
            instance = Bukkit.getServer().getServicesManager().getRegistration(SmartQueue.class).getProvider();
        }

        return instance;
    }
}

package ch.swisscypher.smartqueue.spigot.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class SmartQueue {

    /**
     * Add a player to an existing queue using the priority set in the permission, or using the default one
     * if the queue doesn't require it.
     * @param player The player to add to the queue
     * @param queue The queue name set in the server config
     */
    public abstract void addPlayer(Player player, String queue);

    /**
     * Remove a player from a queue. If the player isn't already in the queue, does a NO-OP.
     * @param player The player to remove from the queue
     * @param queue The queue name from which the player will be removed
     */
    public abstract void removePlayerFromQueue(Player player, String queue);

    /**
     * Remove a player from ALL queues. If a player isn't in any queue, does a NO-OP.
     * Note : When a player is disconnected from the network, it is automatically removed from all queues.
     * @param player The player we want to remove from all queues
     */
    public abstract void removePlayerFromAllQueue(Player player);

    /**
     * Instance for the singleton implementation
     */
    private static SmartQueue instance;

    /**
     * getInstance for the singleton implementation
     * @return The <code>SmartQueue</code> singleton instance
     */
    public static SmartQueue getInstance() {
        if(instance == null) {
            instance = Bukkit.getServer().getServicesManager().getRegistration(SmartQueue.class).getProvider();
        }

        return instance;
    }
}

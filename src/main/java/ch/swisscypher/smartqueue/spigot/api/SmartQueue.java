package ch.swisscypher.smartqueue.spigot.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
     * Activate or deactivate the given queue
     * @param queue The queue we want to change the status
     * @param status true in order to activate the queue, false to deactivate it
     */
    public abstract void setQueueStatus(String queue, boolean status);

    /**
     * Retrieve the status of the given queue
     * @param queue The queue name we want the status
     * @return true if the queue is activated, false otherwise
     */
    public abstract CompletableFuture<Optional<Boolean>> getQueueStatus(String queue);

    /**
     * Check if a player is waiting inside a given queue
     * Note : do not block the main thread when using this {@link CompletableFuture}
     * @param player The player we want to check
     * @param queue The queue we want to check
     * @return true if the player is waiting inside the queue, false otherwise
     */
    public abstract CompletableFuture<Boolean> isPlayerInQueue(Player player, String queue);

    /**
     * Retrieve the position of a given player inside a given queue
     * Note : do not block the main thread when using this {@link CompletableFuture}
     * @param player The player whose position will be retrieved
     * @param queue The queue inside which the player position will be retrieved
     * @return The player position inside the given queue
     */
    public abstract CompletableFuture<Optional<Long>> getPlayerPositionInQueue(Player player, String queue);

    /**
     * Return all the players that are inside a queue.
     * Warning, there is no guarantee the player is connected to this instance
     * Note : do not block the main thread when using this {@link CompletableFuture}
     * @param queue The queue name from which the players will be retrieved
     * @return The list containing all the connected players
     */
    public abstract CompletableFuture<Optional<List<OfflinePlayer>>> getPlayersInQueue(String queue);

    /**
     * Instance for the singleton implementation
     */
    private static SmartQueue instance;

    /**
     * getInstance for the singleton implementation
     * @return The {@link SmartQueue} singleton instance
     */
    public static SmartQueue getInstance() {
        if(instance == null) {
            instance = Bukkit.getServer().getServicesManager().getRegistration(SmartQueue.class).getProvider();
        }

        return instance;
    }
}

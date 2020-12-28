package de.raik.namehider.playermenu;

import net.labymod.user.User;
import net.labymod.user.util.UserActionEntry;
import net.labymod.utils.Consumer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Addon Executor to set
 * it easier
 *
 * @author Raik
 * @version 1.0
 */
public class AddonEntryExecutor implements UserActionEntry.ActionExecutor {

    /**
     * Consumer called by executing
     */
    private final Consumer<EntityPlayer> executeConsumer;

    /**
     * Constructor to set the
     * callback
     *
     * @param executeConsumer The callback
     */
    public AddonEntryExecutor(Consumer<EntityPlayer> executeConsumer) {
        this.executeConsumer = executeConsumer;
    }

    /**
     * Execute method to call the
     * consumer
     *
     * @param user the labymod user
     * @param entityPlayer the entity
     * @param networkPlayerInfo networkPlayerInfo
     */
    @Override
    public void execute(User user, EntityPlayer entityPlayer, NetworkPlayerInfo networkPlayerInfo) {
        this.executeConsumer.accept(entityPlayer);
    }

    /**
     * Can appear always because
     * it will be check in another place
     *
     * @param user user
     * @param entityPlayer entityPlayer
     * @param networkPlayerInfo networkPlayerInfo
     * @return always true
     */
    @Override
    public boolean canAppear(User user, EntityPlayer entityPlayer, NetworkPlayerInfo networkPlayerInfo) {
        return true;
    }
}

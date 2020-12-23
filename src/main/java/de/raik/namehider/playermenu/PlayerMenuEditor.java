package de.raik.namehider.playermenu;

import net.labymod.api.events.UserMenuActionEvent;
import net.labymod.user.User;
import net.labymod.user.util.UserActionEntry;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * Editor of the player menu to
 * add selected player menu additions
 *
 * @author Raik
 * @version 1.0
 */
public class PlayerMenuEditor implements UserMenuActionEvent {

    /**
     * Listener to add the entries
     * to the player menu
     *
     * @param user The lnbymod user
     * @param entityPlayer The target entity
     * @param networkPlayerInfo The player info
     * @param playerMenuEntries The list of all entries
     */
    @Override
    public void createActions(User user, EntityPlayer entityPlayer, NetworkPlayerInfo networkPlayerInfo, List<UserActionEntry> playerMenuEntries) {

    }
}

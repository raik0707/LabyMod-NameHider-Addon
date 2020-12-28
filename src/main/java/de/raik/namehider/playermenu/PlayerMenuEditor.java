package de.raik.namehider.playermenu;

import de.raik.namehider.FeatureSection;
import de.raik.namehider.NameHiderAddon;
import de.raik.namehider.playermenu.entries.ChangeTagEntry;
import de.raik.namehider.playermenu.entries.RemoveTagEntry;
import net.labymod.api.events.UserMenuActionEvent;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.user.User;
import net.labymod.user.util.UserActionEntry;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashSet;
import java.util.List;

/**
 * Editor of the player menu to
 * add selected player menu additions
 *
 * @author Raik
 * @version 1.0
 */
public class PlayerMenuEditor extends FeatureSection implements UserMenuActionEvent {

    /**
     * Collection of entries to store them
     */
    private final HashSet<AddonMenuEntry> entries = new HashSet<>();

    /**
     * Constructor to set addon
     * variable
     *
     * @param addon The addon
     */
    public PlayerMenuEditor(NameHiderAddon addon) {
        super(addon);

        //Register event
        addon.getApi().getEventManager().register(this);

        //Adding element
        this.entries.add(new RemoveTagEntry(this));
        this.entries.add(new ChangeTagEntry(this));
    }

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
        this.entries.forEach(entry -> entry.addIfPossible(playerMenuEntries, entityPlayer));
    }

    /**
     * Loading the configs for commands
     * to set enabled and to add
     * config section
     * Should be overridden by sub classes
     * should call super
     */
    @Override
    public void loadConfig() {
        super.loadConfig();

        //Set for entries
        this.entries.forEach(entry -> entry.loadConfig(this.getConfig()));
    }

    /**
     * Method to add settings dynamically
     * to the addon settings
     * should be overridden by sub class
     * but calling super
     *
     * @param settings The settings array to add the settings to
     */
    @Override
    public void addSettings(List<SettingsElement> settings) {
        super.addSettings(settings);

        //Adding elements
        for (AddonMenuEntry entry: this.entries) {
            settings.add(entry.getSetting());
        }
    }

    /**
     * Method for feature section to set
     * the display name
     *
     * @return The display name
     */
    @Override
    public String getDisplayName() {
        return "§9Player menu";
    }

    /**
     * Method the set the configuration
     * sub sections name of feature set
     *
     * @return The configuration name
     */
    @Override
    public String getConfigName() {
        return "playermenu";
    }
}

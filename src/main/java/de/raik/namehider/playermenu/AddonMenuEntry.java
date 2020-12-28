package de.raik.namehider.playermenu;

import com.google.gson.JsonObject;
import de.raik.namehider.settingelements.DescribedBooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.user.util.UserActionEntry;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * Addon menu entry super class
 * to define sub entries of
 * the player menu
 *
 * @author Raik
 * @version 1.0
 */
public abstract class AddonMenuEntry extends UserActionEntry {

    private boolean enabled = false;

    /**
     * Playermenu editor instance for accessing addon in the commands
     */
    private final PlayerMenuEditor playerMenuEditor;

    /**
     * Constructor to set player menu entry
     *
     * @param playerMenuEditor playerMenuEditor
     */
    public AddonMenuEntry(PlayerMenuEditor playerMenuEditor) {
        //Calling super with default things to set it later
        super("", EnumActionType.NONE, null, null);

        //Setting name
        this.setDisplayName(this.getName());

        //Setting instance
        this.playerMenuEditor = playerMenuEditor;
    }

    /**
     * Adding player menu entry possible
     *
     * @param playerMenuEntries the list to add the entry to
     * @param entityPlayer the entityplayer to handle it
     */
    public void addIfPossible(List<UserActionEntry> playerMenuEntries, EntityPlayer entityPlayer) {
        if (this.enabled)
            playerMenuEntries.add(this);
    }

    /**
     * Getter for the setting element of the command
     * adding additional callback for changing enabled
     *
     * @return The setting element
     */
    public DescribedBooleanElement getSetting() {
        //Creating setting and callback
        DescribedBooleanElement setting = new DescribedBooleanElement(this.getName(), this.playerMenuEditor.getAddon(), this.getIcon()
                , this.getSettingName(), this.enabled, this.getDescription(), this.playerMenuEditor.getConfig(), true);
        setting.addCallback(changedValue -> this.enabled = changedValue);

        return setting;
    }

    /**
     * Set the enabled with loading configs
     *
     * @param configObject The config boject
     */
    public void loadConfig(JsonObject configObject) {
        this.enabled = configObject.has(this.getSettingName()) ? configObject.get(this.getSettingName()).getAsBoolean() : this.enabled;
    }

    /**
     * Method to set the name of a command
     * in a sub class
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Method to set the name of the config
     *
     * @return the setting name
     */
    public abstract String getSettingName();

    /**
     * Method to set the icon of a command
     * in a sub class
     *
     * @return the icon
     */
    public abstract ControlElement.IconData getIcon();

    /**
     * Method to set the description of a command
     * in a sub class
     *
     * @return the description
     */
    public abstract String getDescription();

    /**
     * Getter for the player menu editor
     * to access the addon in entries
     *
     * @return The editor
     */
    public PlayerMenuEditor getPlayerMenuEditor() {
        return this.playerMenuEditor;
    }
}

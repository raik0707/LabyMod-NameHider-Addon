package de.raik.namehider.playermenu.entries;

import com.google.gson.JsonObject;
import de.raik.namehider.NameHiderAddon;
import de.raik.namehider.playermenu.AddonMenuEntry;
import de.raik.namehider.playermenu.PlayerMenuEditor;
import net.labymod.settings.elements.ControlElement;
import net.labymod.user.util.UserActionEntry;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * Entry calling command to except user
 * via player menu more easy
 *
 * @author Raik
 * @version 1.0
 */
public class ExceptNameEntry extends AddonMenuEntry {

    /**
     * Constructor to set player menu entry
     *
     * @param playerMenuEditor playerMenuEditor
     */
    public ExceptNameEntry(PlayerMenuEditor playerMenuEditor) {
        super(playerMenuEditor);

        //Setting attributes
        this.setType(EnumActionType.RUN_COMMAND);
        this.setValue("namehider {name}");
    }

    /**
     * Adding player menu entry possible
     * hooking into adding process to check
     * if command is enabled and change name if needed
     *
     * @param playerMenuEntries the list to add the entry to
     * @param entityPlayer      the entityplayer to handle it
     */
    @Override
    public void addIfPossible(List<UserActionEntry> playerMenuEntries, EntityPlayer entityPlayer) {
        NameHiderAddon addon = this.getPlayerMenuEditor().getAddon();

        //Checking command
        JsonObject addonConfig = addon.getConfig();
        if (addonConfig.has("commands")) {
            JsonObject commandsObject = addonConfig.getAsJsonObject("commands");
            //Breaking if disabled
            if (commandsObject.has("namehider") && !commandsObject.get("namehider").getAsBoolean())
                return;
        }

        //Changing name if needed
        this.setDisplayName(addon.getUserConfigurations().containsKey(entityPlayer.getName().toLowerCase()) ? "Unexcept name": this.getName());

        //Executing super
        super.addIfPossible(playerMenuEntries, entityPlayer);
    }

    /**
     * Method to set the name of a command
     * in a sub class
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "Except name";
    }

    /**
     * Method to set the name of the config
     *
     * @return the setting name
     */
    @Override
    public String getSettingName() {
        return "exceptname";
    }

    /**
     * Method to set the icon of a command
     * in a sub class
     *
     * @return the icon
     */
    @Override
    public ControlElement.IconData getIcon() {
        return new ControlElement.IconData("labymod/textures/settings/settings/showmyname.png");
    }

    /**
     * Method to set the description of a command
     * in a sub class
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "If enabled you can easily, except users using the player menu";
    }
}

package de.raik.namehider.playermenu.entries;

import de.raik.namehider.playermenu.AddonMenuEntry;
import de.raik.namehider.playermenu.PlayerMenuEditor;
import net.labymod.gui.GuiTagsAdd;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Entry to change the tag of the current user
 *
 * @author Raik
 * @version 1.0
 */
public class ChangeTagEntry extends AddonMenuEntry {

    /**
     * Constructor to set player menu entry
     *
     * @param playerMenuEditor playerMenuEditor
     */
    public ChangeTagEntry(PlayerMenuEditor playerMenuEditor) {
        super(playerMenuEditor);
    }

    /**
     * Method called on execute by the executor
     * calling gui yes no to handle removing
     *
     * @param entityPlayer The entityPlayer
     */
    @Override
    public void execute(EntityPlayer entityPlayer) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiTagsAdd(null, entityPlayer.getName()));
    }

    /**
     * Method to set the name of a command
     * in a sub class
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "Change tag";
    }

    /**
     * Method to set the name of the config
     *
     * @return the setting name
     */
    @Override
    public String getSettingName() {
        return "changetag";
    }

    /**
     * Method to set the icon of a command
     * in a sub class
     *
     * @return the icon
     */
    @Override
    public ControlElement.IconData getIcon() {
        return new ControlElement.IconData("labymod/textures/chat/gui_editor.png");
    }

    /**
     * Method to set the description of a command
     * in a sub class
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Entry to change the tag if player. Will add if not present.";
    }
}

package de.raik.namehider.playermenu.entries;

import de.raik.namehider.playermenu.AddonMenuEntry;
import de.raik.namehider.playermenu.PlayerMenuEditor;
import net.labymod.main.lang.LanguageManager;
import net.labymod.settings.elements.ControlElement;
import net.labymod.user.util.UserActionEntry;
import net.labymod.utils.ModColor;
import net.labymod.utils.manager.TagManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;
import java.util.Objects;

/**
 * Entry to remove a friend tag
 * if present
 *
 * @author Raik
 * @version 1.0
 */
public class RemoveTagEntry extends AddonMenuEntry {

    /**
     * Constructor to set player menu entry
     *
     * @param playerMenuEditor playerMenuEditor
     */
    public RemoveTagEntry(PlayerMenuEditor playerMenuEditor) {
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
        Minecraft.getMinecraft().displayGuiScreen(new GuiYesNo((result, id) -> {
            if (result) {
                TagManager.getConfigManager().getSettings().getTags().remove(entityPlayer.getName());
            }

            //Closing gui
            Minecraft.getMinecraft().displayGuiScreen(null);
        }, LanguageManager.translate("warning_delete"), ModColor.cl("c") + entityPlayer.getName(), 1));
    }

    /**
     * Adding player menu entry possible
     * overridden to check if tag is there
     *
     * @param playerMenuEntries the list to add the entry to
     * @param entityPlayer the entityplayer to handle it
     */
    @Override
    public void addIfPossible(List<UserActionEntry> playerMenuEntries, EntityPlayer entityPlayer) {
        //Breaking if no tag
        if (Objects.isNull(TagManager.getTaggedMessage(entityPlayer.getName())))
            return;

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
        return "Delete tag";
    }

    /**
     * Method to set the name of the config
     *
     * @return the setting name
     */
    @Override
    public String getSettingName() {
        return "deletetag";
    }

    /**
     * Method to set the icon of a command
     * in a sub class
     *
     * @return the icon
     */
    @Override
    public ControlElement.IconData getIcon() {
        return new ControlElement.IconData("labymod/textures/buttons/trash.png");
    }

    /**
     * Method to set the description of a command
     * in a sub class
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "If enabled a entry will show up to remove the tag if a user has a tag";
    }
}

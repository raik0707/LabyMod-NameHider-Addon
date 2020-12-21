package de.raik.namehider.command.commands;

import de.raik.namehider.NameHiderAddon;
import de.raik.namehider.command.AddonCommand;
import net.labymod.addon.online.AddonInfoManager;
import net.labymod.addon.online.info.AddonInfo;
import net.labymod.settings.LabyModAddonsGui;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;

/**
 * Command class for the
 * name hider command which opens
 * the addon settings
 *
 * @author Raik
 * @version 1.0
 */
public class NameHiderCommand extends AddonCommand {

    /**
     * Addon field to set the currently opened setting to the gui
     * of the addon settings
     */
    private static final Field OPENED_SETTINGS_FIELD;

    /*
     * Static block to set the addon field
     */
    static {
        Field openedSettingsField = null;

        try {
            openedSettingsField = LabyModAddonsGui.class.getDeclaredField("openedAddonSettings");
            openedSettingsField.setAccessible(true);
        } catch (NoSuchFieldException exception) {
            exception.printStackTrace();
        }

        OPENED_SETTINGS_FIELD = openedSettingsField;
    }

    /**
     * Constructor to set addon
     * variable
     *
     * @param addon The addon
     */
    public NameHiderCommand(NameHiderAddon addon) {
        super(addon);
    }

    /**
     * Abstract method called on execution
     * of the command
     *
     * @param arguments The command arguments
     */
    @Override
    public void execute(String[] arguments) {
        /*
         * Code snippet to open the Settings of EmoteChat Addon
         * <3
         */
        AddonInfoManager.getInstance().init();
        AddonInfo addonInfo = AddonInfoManager.getInstance().getAddonInfoMap().get(this.getAddon().about.uuid);

        LabyModAddonsGui addonsGui = new LabyModAddonsGui(Minecraft.getMinecraft().currentScreen);
        try {
            OPENED_SETTINGS_FIELD.set(addonsGui, addonInfo.getAddonElement());
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }

        Minecraft.getMinecraft().displayGuiScreen(addonsGui);
    }

    /**
     * Method to set the name of a command
     * in a sub class
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "namehider";
    }

    /**
     * Method to set the icon of a command
     * in a sub class
     *
     * @return the icon
     */
    @Override
    public ControlElement.IconData getIcon() {
        return new ControlElement.IconData("labymod/textures/buttons/advanced.png");
    }

    /**
     * Method to set the description of a command
     * in a sub class
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Command to quickly open the name hider settings.";
    }
}

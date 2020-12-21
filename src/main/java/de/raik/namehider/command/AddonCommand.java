package de.raik.namehider.command;

import de.raik.namehider.NameHiderAddon;
import de.raik.namehider.settingelements.DescribedBooleanElement;
import net.labymod.settings.elements.ControlElement;

/**
 * Abstract super class for
 * an addon command
 * provides handling for the command
 *
 * @author Raik
 * @version 1.0
 */
public abstract class AddonCommand {

    private boolean enabled = true;

    /**
     * Addon instance for accessing the addon in the Commands
     */
    private final NameHiderAddon addon;

    /**
     * Constructor to set addon
     * variable
     *
     * @param addon The addon
     */
    public AddonCommand(NameHiderAddon addon) {
        this.addon = addon;
    }

    /**
     * Abstract method called on execution
     * of the command
     *
     * @param arguments The command arguments
     */
    public abstract void execute(String[] arguments);

    /**
     * Dispatch method to handle command
     * Checking if valid
     * executing the command on success
     *
     * @param sentMessage the message to check
     * @return Logical value if it can be dispatched
     */
    public boolean dispatch(String sentMessage) {
        String commandName = this.getName();

        /*
         * Break up if to short to prevent exception
         * in substring
         */
        if (sentMessage.length() < commandName.length())
            return false;

        /*
         * Break up if command isn't right
         * substring for separating in command piece
         */
        if (!sentMessage.substring(0, commandName.length()).equalsIgnoreCase(commandName))
            return false;

        //Checking if arguments are possible with the length
        if (sentMessage.length() < commandName.length() + 2) {
            //Executing without arguments
            this.execute(new String[0]);
            //Returning to prevent wrong call
            return true;
        }
        //Executing
        this.execute(sentMessage.substring(commandName.length() + 2).split(" "));
        return true;
    }

    /**
     * Getter for the setting element of the command
     * adding additional callback for changing enabled
     *
     * @param addon The addon instance for creating the setting
     * @return The setting element
     */
    public DescribedBooleanElement getSetting(NameHiderAddon addon) {
        //Creating setting and adding additional callback
        DescribedBooleanElement setting = new DescribedBooleanElement("/" + this.getName(), addon, this.getIcon()
                , this.getName(), this.enabled , this.getDescription(), addon.getConfig().getAsJsonObject("commands"), true);
        setting.addCallback(changedValue -> this.enabled = changedValue);

        return setting;
    }

    /**
     * Method to set the name of a command
     * in a sub class
     *
     * @return the name
     */
    public abstract String getName();

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
     * Getter for the addon
     *
     * @return The addon
     */
    public NameHiderAddon getAddon() {
        return this.addon;
    }
}

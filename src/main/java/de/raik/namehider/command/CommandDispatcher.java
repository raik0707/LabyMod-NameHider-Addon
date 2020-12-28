package de.raik.namehider.command;

import de.raik.namehider.FeatureSection;
import de.raik.namehider.NameHiderAddon;
import de.raik.namehider.command.commands.ExceptNameCommand;
import de.raik.namehider.command.commands.NameHiderCommand;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.settings.elements.SettingsElement;

import java.util.HashSet;
import java.util.List;

/**
 * Manager of the commands
 * registering and config handling
 * needs to be created in the Addon config
 * implementing message sent event for
 * dispatching commands
 *
 * @author Raik
 * @version 1.0
 */
public class CommandDispatcher extends FeatureSection implements MessageSendEvent {

    /**
     * Addon instance to access it
     */
    private final NameHiderAddon addon;

    /**
     * Collection of commands for storing
     */
    private final HashSet<AddonCommand> commands = new HashSet<>();

    /**
     * Constructor for registering commands
     * and setting addon variable
     *
     * @param addon The addon to set
     */
    public CommandDispatcher(NameHiderAddon addon) {
        super(addon);

        this.addon = addon;

        //Register event
        addon.getApi().getEventManager().register(this);

        //Registering commands
        this.commands.add(new NameHiderCommand(this));
        this.commands.add(new ExceptNameCommand(this));
    }

    /**
     * Loading the configs for commands
     * to set enabled
     */
    @Override
    public void loadConfig() {
        super.loadConfig();

        //Set for commands
        this.commands.forEach(command -> command.loadConfig(this.getConfig()));
    }

    /**
     * Method for feature section to set
     * the display name
     *
     * @return The display name
     */
    @Override
    public String getDisplayName() {
        return "§7Commands";
    }

    /**
     * Method the set the configuration
     * sub sections name of feature set
     *
     * @return The configuration name
     */
    @Override
    public String getConfigName() {
        return "commands";
    }

    /**
     * Message listener to get message on send
     *
     * @param message The message to send
     * @return Value if message sent to the server
     */
    @Override
    public boolean onSend(String message) {
        //Breaking if not a command
        if (!message.startsWith("/"))
            return false;

        //Looping commands to check
        for (AddonCommand command: this.commands) {
            if (command.dispatch(message.substring(1)))
                return true;
        }

        return false;
    }

    /**
     * Method to add settings dynamically
     * to the addon settings
     *
     * @param settings The settings array to add the settings to
     */
    @Override
    public void addSettings(List<SettingsElement> settings) {
        super.addSettings(settings);

        //Adding elements
        for (AddonCommand command: this.commands) {
            settings.add(command.getSetting());
        }
    }

    @Override
    public NameHiderAddon getAddon() {
        return this.addon;
    }
}

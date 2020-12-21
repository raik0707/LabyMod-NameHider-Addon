package de.raik.namehider.command;

import com.google.gson.JsonObject;
import de.raik.namehider.NameHiderAddon;
import de.raik.namehider.command.commands.NameHiderCommand;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.settings.elements.HeaderElement;
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
public class CommandDispatcher implements MessageSendEvent {

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
        this.addon = addon;

        //Register event
        addon.getApi().getEventManager().register(this);

        //Adding config
        if (!addon.getConfig().has("commands"))
            addon.getConfig().add("commands", new JsonObject());

        //Registering commands
        this.commands.add(new NameHiderCommand(this.addon));
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
     * Method to add command settings dynamically
     * to the addon settings
     *
     * @param settings The settings array to add the settings to
     */
    public void addCommandSettings(List<SettingsElement> settings) {
        //Header
        settings.add(new HeaderElement("§7Commands"));

        //Adding elements
        for (AddonCommand command: this.commands) {
            settings.add(command.getSetting(this.addon));
        }
    }
}

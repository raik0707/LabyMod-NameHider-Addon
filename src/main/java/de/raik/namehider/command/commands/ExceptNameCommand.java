package de.raik.namehider.command.commands;

import de.raik.namehider.DisplayConfiguration;
import de.raik.namehider.NameHiderAddon;
import de.raik.namehider.command.AddonCommand;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.ControlElement;

import java.util.HashMap;

/**
 * Command to set a special configuration of
 * every user
 *
 * @author Raik
 * @version 1.0
 */
public class ExceptNameCommand extends AddonCommand {

    /**
     * Constructor to set addon
     * variable
     *
     * @param addon The addon
     */
    public ExceptNameCommand(NameHiderAddon addon) {
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
        //Argument check
        if (arguments.length < 1) {
            LabyMod.getInstance().displayMessageInChat("§eSyntax: /exceptname <playername> [<attribute>]");
            return;
        }

        HashMap<String, DisplayConfiguration> configurationMap = this.getAddon().getUserConfigurations();
        String targetName = arguments[0].toLowerCase();

        //Adding new if not added
        if (!configurationMap.containsKey(targetName)) {
            configurationMap.put(targetName, new DisplayConfiguration(this.getAddon()));
            LabyMod.getInstance().displayMessageInChat("§a" + arguments[0] + "'s nickname will be excepted.");
            return;
        }

        //Removing if no change argument
        if (arguments.length < 2) {
            configurationMap.remove(targetName);
            LabyMod.getInstance().displayMessageInChat("§c" + arguments[0] + "'s nickname will no longer be excepted");
            return;
        }

        //Changing
        DisplayConfiguration userConfiguration = configurationMap.get(targetName);

        switch (arguments[1].toLowerCase()) {
            case "pen": userConfiguration.setYellowPen(!userConfiguration.isYellowPen()); break;
            case "ranks": userConfiguration.setRanks(!userConfiguration.isRanks()); break;
            case "subtitle": userConfiguration.setSubtitles(!userConfiguration.isSubtitles()); break;
            case "scoreboard": userConfiguration.setScoreboards(!userConfiguration.isScoreboards()); break;
            default: LabyMod.getInstance().displayMessageInChat("§eSyntax: /exceptname <playername> [<pen | ranks | subtitle | scoreboard>]"); return;
        }

        //Success message
        LabyMod.getInstance().displayMessageInChat("§aAttribute \"" + arguments[1].toLowerCase() + "\" updated for " + arguments[0] + "'s nickname.");
    }

    /**
     * Method to set the name of a command
     * in a sub class
     *
     * @return the name
     */
    @Override
    public String getName() {
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
        return new ControlElement.IconData("labymod/textures/buttons/exclamation_mark.png");
    }

    /**
     * Method to set the description of a command
     * in a sub class
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Command to edit the displaying of a specific user.";
    }
}

package de.raik.namehider;

import com.google.gson.JsonObject;
import de.raik.namehider.command.CommandDispatcher;
import de.raik.namehider.implementation.HiderCoreImplementation;
import net.labymod.api.LabyModAddon;
import net.labymod.core.LabyModCore;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;

/**
 * Addon instance
 * to manage the addon
 *
 * @author Raik
 * @version 1.0
 */
public class NameHiderAddon extends LabyModAddon {

    /**
     * If true names will show
     */
    private boolean showNames = true;

    /**
     * Command handling class in the addon
     */
    private CommandDispatcher commandDispatcher;

    /**
     * Init method called by
     * the addon api to setup the addon
     */
    @Override
    public void onEnable() {
        //Setting core adapter to apply the changes to the rendering
        LabyModCore.setCoreAdapter(new HiderCoreImplementation(this));

        //Creating command dispatcher
        commandDispatcher = new CommandDispatcher(this);
    }

    /**
     * Method called by api
     * to load setting from config file
     */
    @Override
    public void loadConfig() {
        JsonObject addonConfig = this.getConfig();

        this.showNames = addonConfig.has("shownames") ? addonConfig.get("shownames").getAsBoolean() : this.showNames;

        //Commands
        this.commandDispatcher.loadConfig();
    }

    /**
     * Method called by the addon api
     * for adding settings to the addon config
     *
     * @param settings The list of setting elements
     */
    @Override
    protected void fillSettings(List<SettingsElement> settings) {
        //Settings

        //Setting for the show Names property setting the value
        settings.add(new BooleanElement("Show player names", new ControlElement.IconData("labymod/textures/settings/settings/showmyname.png")
                , changeValue -> this.showNames = changeValue, this.showNames));

        //Commands
        this.commandDispatcher.addCommandSettings(settings);
    }

    public boolean isShowNames() {
        return showNames;
    }
}

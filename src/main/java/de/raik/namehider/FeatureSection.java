package de.raik.namehider;

import com.google.gson.JsonObject;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;

/**
 * An abstract class to declare
 * greater feature section for
 * an easier management
 *
 * @author Raik
 * @version 1.0
 */
public abstract class FeatureSection {

    /**
     * Addon instance to access it
     */
    private final NameHiderAddon addon;

    /**
     * The config object to handle the config
     */
    private JsonObject config = null;

    /**
     * Constructor to set addon
     * variable
     *
     * @param addon The addon
     */
    public FeatureSection(NameHiderAddon addon) {
        this.addon = addon;
    }

    /**
     * Method to add settings dynamically
     * to the addon settings
     * should be overridden by sub class
     * but calling super
     *
     * @param settings The settings array to add the settings to
     */
    public void addSettings(List<SettingsElement> settings) {
        //Adding header by default
        settings.add(new HeaderElement(this.getDisplayName()));
    }

    /**
     * Loading the configs for commands
     * to set enabled and to add
     * config section
     * Should be overridden by sub classes
     * should call super
     */
    public void loadConfig() {
        //Break if already set
        if (this.config != null)
            return;

        //Checking if available and adding if not
        if (!this.addon.getConfig().has(this.getConfigName()))
            this.addon.getConfig().add(this.getConfigName(), new JsonObject());

        //Setting
        this.config = this.addon.getConfig().getAsJsonObject(this.getConfigName());
    }

    /**
     * Getter for the addon
     *
     * @return The addon
     */
    public NameHiderAddon getAddon() {
        return this.addon;
    }

    /**
     * Config getter for the
     * feature sub config
     *
     * @return The config
     */
    public JsonObject getConfig() {
        return this.config;
    }

    /**
     * Method for feature section to set
     * the display name
     *
     * @return The display name
     */
    public abstract String getDisplayName();

    /**
     * Method the set the configuration
     * sub sections name of feature set
     *
     * @return The configuration name
     */
    public abstract String getConfigName();
}

package de.raik.namehider;

import de.raik.namehider.implementation.HiderCoreImplementation;
import net.labymod.api.LabyModAddon;
import net.labymod.core.LabyModCore;
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
     * Init method called by
     * the addon api to setup the addon
     */
    @Override
    public void onEnable() {
        //Setting core adapter to apply the changes to the rendering
        LabyModCore.setCoreAdapter(new HiderCoreImplementation());
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }
}

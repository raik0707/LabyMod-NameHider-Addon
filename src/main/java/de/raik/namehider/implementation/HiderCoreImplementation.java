package de.raik.namehider.implementation;

import de.raik.namehider.NameHiderAddon;
import net.labymod.core.RenderPlayerAdapter;
import net.labymod.core_implementation.mc18.CoreImplementation;

/**
 * Adding the core implementation to insert
 * new render player implementation without
 * reflections
 *
 * @author Raik
 * @version 1.0
 */
public class HiderCoreImplementation extends CoreImplementation {

    /**
     * Implementation variable for the rendere
     */
    private final RenderPlayerAdapter renderPlayerImplementation;

    /**
     * Constructor for setting addon instance in implementation
     *
     * @param addon The addon instance
     */
    public HiderCoreImplementation(NameHiderAddon addon) {
        this.renderPlayerImplementation =  new HiderRenderPlayerImplementation(addon);
    }

    /**
     * Overriding getter of the overwritten implementation
     * for providing it to the services
     *
     * @return The hider implementation
     */
    @Override
    public RenderPlayerAdapter getRenderPlayerImplementation() {
        return this.renderPlayerImplementation;
    }

}

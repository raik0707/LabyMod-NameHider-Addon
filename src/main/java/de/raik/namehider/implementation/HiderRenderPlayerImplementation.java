package de.raik.namehider.implementation;

import de.raik.namehider.NameHiderAddon;
import net.labymod.core_implementation.mc18.RenderPlayerImplementation;
import net.labymod.mojang.RenderPlayerHook;
import net.minecraft.client.entity.AbstractClientPlayer;

/**
 * New render implementation to access
 * the rendering of the client
 *
 * @author Raik
 * @version 1.0
 */
public class HiderRenderPlayerImplementation extends RenderPlayerImplementation {

    /**
     * Addon instance for accessing it
     */
    private final NameHiderAddon addon;

    /**
     * Constructor for setting addon
     *
     * @param addon The addon to set
     */
    public HiderRenderPlayerImplementation(NameHiderAddon addon) {
        this.addon = addon;
    }

    /**
     * Overriding the renderName method to
     * apply wanted changes
     *
     * @param renderPlayer renderPlayer
     * @param entity the entity to render
     * @param x x
     * @param y y
     * @param z z
     */
    @Override
    public void renderName(RenderPlayerHook.RenderPlayerCustom renderPlayer, AbstractClientPlayer entity, double x, double y, double z) {
        if (this.addon.isShowNames())
            super.renderName(renderPlayer, entity, x, y, z);
    }

}

package de.raik.namehider.implementation;

import de.raik.namehider.DisplayConfiguration;
import de.raik.namehider.NameHiderAddon;
import net.labymod.core_implementation.mc18.RenderPlayerImplementation;
import net.labymod.main.LabyMod;
import net.labymod.mojang.RenderPlayerHook;
import net.labymod.user.User;
import net.labymod.utils.manager.TagManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

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
        DisplayConfiguration configuration = this.addon.receiveConfigurationForPlayer(entity.getName().toLowerCase());

        //Breaking if disabled
        if (!configuration.showNameTag())
            return;

        //Break on render issues
        boolean canRender = Minecraft.isGuiEnabled() && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer) && entity.riddenByEntity == null;
        if (!(renderPlayer.canRenderTheName(entity) || entity == renderPlayer.getRenderManager().livingPlayer && LabyMod.getSettings().showMyName && canRender))
            return;

        //Check distance
        double distance = entity.getDistanceSqToEntity(renderPlayer.getRenderManager().livingPlayer);
        float distanceLimiter = entity.isSneaking() ? 32.0F : 64.0F;
        if (distance >= (double)(distanceLimiter * distanceLimiter))
            return;

        //Start of render process
        User user = LabyMod.getInstance().getUserManager().getUser(entity.getUniqueID());
        y += (LabyMod.getSettings().cosmetics ? user.getMaxNameTagHeight() : 0.0F);
        String username = entity.getDisplayName().getFormattedText();
        GlStateManager.alphaFunc(516, 0.1F);
        String tag = TagManager.getTaggedMessage(username);
        if (tag != null)
            username = tag;

        float fixedPlayerViewX = renderPlayer.getRenderManager().playerViewX * (float)(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -1 : 1);
        FontRenderer fontRenderer = renderPlayer.getFontRendererFromRenderManager();

        //Sneaking render
        if (entity.isSneaking()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) x, (float) y + entity.height + 0.5F, (float) z);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-renderPlayer.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(fixedPlayerViewX, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(-0.02666667F, -0.02666667F, 0.02666667F);
            GlStateManager.translate(0.0F, 9.374999F, 0.0F);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            int stringWidth = fontRenderer.getStringWidth(username) / 2;
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos(-stringWidth - 1, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldRenderer.pos(-stringWidth - 1, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldRenderer.pos(stringWidth + 1, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldRenderer.pos(stringWidth + 1, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
            fontRenderer.drawString(username, -stringWidth, 0, 553648127);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
            return;
        }
    }

}

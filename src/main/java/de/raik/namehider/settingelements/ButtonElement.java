package de.raik.namehider.settingelements;

import net.labymod.core.LabyModCore;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Consumer;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

/**
 * Config element to trigger an action on a
 * config action in the commands
 * Thanks to the HDSkins Developers for creating such element before
 *
 * @author Raik
 * @version 1.0
 */
public class ButtonElement extends ControlElement {

    /**
     * The runnable which will be called when the button is clicked
     * which element callback to
     */
    private final Consumer<ButtonElement> buttonListener;

    /**
     * The button of the module
     */
    private final GuiButton button;

    /**
     * Constructor calling super to setup element
     * adding variables
     *
     * @param displayName The module displayName
     * @param iconData The icon
     * @param buttonListener The callback runnable for the button action
     * @param buttonText The text of the button
     * @param description The module description
     */
    public ButtonElement(String displayName, IconData iconData, Consumer<ButtonElement> buttonListener, String buttonText, String description) {
        super(displayName, iconData);

        this.setDescriptionText(description);

        this.buttonListener = buttonListener;
        this.button = new GuiButton(-10, 0, 0, 0, 20, buttonText);
    }

    /**
     * Overwriting method to add button checking
     *
     * @param mouseX mouseX
     * @param mouseY mouseY
     * @param mouseButton mouseButton
     */
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.buttonListener != null && this.button.mousePressed(this.mc, mouseX, mouseY)) {
            this.button.playPressSound(this.mc.getSoundHandler());
            this.buttonListener.accept(this);
        }
    }

    /**
     * Added button drawing
     * for module
     * calling super
     *
     * @param x x position of element
     * @param y y position of element
     * @param maxX maximum x of element
     * @param maxY maximum y of element
     * @param mouseX x position of mouse
     * @param mouseY y position of mouse
     */
    @Override
    public void draw(int x, int y, int maxX, int maxY, int mouseX, int mouseY) {
        super.draw(x, y, maxX, maxY, mouseX, mouseY);

        if (this.displayName != null)
            LabyMod.getInstance().getDrawUtils().drawRectangle(x - 1, y, x, maxY, Color.GRAY.getRGB());

        int buttonWidth = this.displayName == null ? maxX - x : this.mc.fontRendererObj.getStringWidth(this.button.displayString) + 20;

        this.button.setWidth(buttonWidth);

        LabyModCore.getMinecraft().setButtonXPosition(this.button, maxX - buttonWidth - 2);
        LabyModCore.getMinecraft().setButtonYPosition(this.button, y + 1);

        LabyModCore.getMinecraft().drawButton(this.button, mouseX, mouseY);
    }

    /**
     * Setting the button enable state
     *
     * @param enabled The enable value
     */
    public void setEnabled(boolean enabled) {
        this.button.enabled = enabled;
    }

}

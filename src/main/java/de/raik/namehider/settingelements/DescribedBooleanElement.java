package de.raik.namehider.settingelements;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;

/**
 * Boolean element with a description
 * Used for more complex settings
 * that may need a description
 *
 * @author Raik
 * @version 1.0
 */
public class DescribedBooleanElement extends BooleanElement {

    /**
     * Constructor for creating such element.
     * Equal to normal boolean element with attribute callback
     * Extra parameter to set description
     *
     * @param displayName The displayName of the element
     * @param addon The reference addon for the config attribute
     * @param iconData The element's icon
     * @param attributeName The name of the config attribute
     * @param defaultValue The default value of the element
     * @param description The setting description
     */
    public DescribedBooleanElement(String displayName, final LabyModAddon addon, IconData iconData
            , final String attributeName, boolean defaultValue, final String description) {
        super(displayName, addon, iconData, attributeName, defaultValue);

        this.setDescriptionText(description);
    }
}

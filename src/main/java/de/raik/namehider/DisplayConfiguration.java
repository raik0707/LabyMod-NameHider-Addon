package de.raik.namehider;

/**
 * Configuration of displaying
 * the parts of the name tags
 * for individuals and global
 *
 * @author Raik
 * @version 1.0
 */
public class DisplayConfiguration {

    /**
     * Addon instance for accessing it
     */
    private final NameHiderAddon addon;

    /**
     * If true the enabled value will be negated
     */
    private final boolean negate;

    /**
     * If false the yellow pen of tags will be hidden
     */
    private boolean yellowPen = true;

    /**
     * If false rank things will be hidden
     */
    private boolean ranks = true;

    /**
     * If false subtitles will be hidden
     */
    private boolean subtitles = true;

    /**
     * If false scoreboard things will be hidden
     */
    private boolean scoreboards = true;

    /**
     * Constructor to set the addon and
     * negation
     *
     * @param addon The addon
     * @param negate negate value
     */
    public DisplayConfiguration(NameHiderAddon addon, boolean negate) {
        this.addon = addon;
        this.negate = negate;
    }

    /**
     * More simple constructor for setting the
     * addon with default negate value true
     *
     * @param addon the addon
     */
    public DisplayConfiguration(NameHiderAddon addon) {
        this(addon, true);
    }

    /**
     * Method to get if the name tag should show
     *
     * @return The value
     */
    public boolean showNameTag() {
        if (this.negate)
            return !this.addon.isShowNames();
        return this.addon.isShowNames();
    }

    public boolean isYellowPen() {
        return this.yellowPen;
    }

    public void toggleYellowPen() {
        this.yellowPen = !this.yellowPen;
    }

    public boolean isRanks() {
        return this.ranks;
    }

    public void toggleRanks() {
        this.ranks = !this.ranks;
    }

    public boolean isSubtitles() {
        return this.subtitles;
    }

    public void toggleSubtitles() {
        this.subtitles = !this.subtitles;
    }

    public boolean isScoreboards() {
        return this.scoreboards;
    }

    public void toggleScoreboards() {
        this.scoreboards = !this.scoreboards;
    }
}

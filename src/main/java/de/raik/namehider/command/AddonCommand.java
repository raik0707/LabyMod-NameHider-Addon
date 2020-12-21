package de.raik.namehider.command;

/**
 * Abstract super class for
 * an addon command
 * provides handling for the command
 *
 * @author Raik
 * @version 1.0
 */
public abstract class AddonCommand {

    private boolean enabled = true;

    /**
     * Abstract method called on execution
     * of the command
     *
     * @param arguments The command arguments
     */
    public abstract void execute(String[] arguments);

    /**
     * Dispatch method to handle command
     * Checking if valid
     * executing the command on success
     *
     * @param sentMessage the message to check
     * @return Logical value if it can be dispatched
     */
    public boolean dispatch(String sentMessage) {
        String commandName = this.getName();

        /*
         * Break up if to short to prevent exception
         * in substring
         */
        if (sentMessage.length() < commandName.length())
            return false;

        /*
         * Break up if command isn't right
         * substring for separating in command piece
         */
        if (!sentMessage.substring(0, commandName.length()).equalsIgnoreCase(commandName))
            return false;

        //Checking if arguments are possible with the length
        if (sentMessage.length() < commandName.length() + 2) {
            //Executing without arguments
            this.execute(new String[0]);
            //Returning to prevent wrong call
            return true;
        }
        //Executing
        this.execute(sentMessage.substring(commandName.length() + 2).split(" "));
        return true;
    }

    /**
     * Method to set the name of a command
     * in a child class
     *
     * @return the name
     */
    public abstract String getName();

}

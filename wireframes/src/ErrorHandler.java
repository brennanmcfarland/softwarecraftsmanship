/**
 * Responsible for handling all errors.  Since the project does not use exceptions as a rule,
 * any erroneous behavior that cannot be handled where it occurs will instead result in a
 * call to a method in this class, which subsequently reports it and takes the appropriate
 * action.  ErrorHandler is a singleton.
 */
class ErrorHandler {

    private static ErrorHandler instance = new ErrorHandler();

    /**
     * Private constructor because it's a singleton
     */
    private ErrorHandler() {}

    static ErrorHandler getInstance() { return instance; }

    /**
     * Display an error to the user.  The assertion is so that any error that is caught is
     * brought to attention in debugging.  This method can be called externally when a non-
     * fatal error occurs and is also used by other error-reporting methods to ensure
     * consistent reporting behavior.
     * @param message The error message to display
     */
    void displayError(String message) {
        System.out.println("Error: " + message);
        assert false;
    }

    /**
     * Display an error to the user via overridable displayError() and quit the program.
     * @param message
     */
    void displayFatalError(String message) {
        displayError(message);
        System.exit(1);
    }
}

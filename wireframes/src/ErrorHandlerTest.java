import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorHandlerTest {

    @Test(expected = AssertionError.class)
    public void test_displayError() throws Exception {
        ErrorHandler.getInstance().displayError("");
    }

    @Test(expected = AssertionError.class)
    public void displayFatalError() throws Exception {
        ErrorHandler.getInstance().displayFatalError("");
    }

}
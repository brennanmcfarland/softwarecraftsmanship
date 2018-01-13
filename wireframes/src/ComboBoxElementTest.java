import org.junit.Assert;
import org.junit.Before;

import java.util.Optional;

import static org.junit.Assert.*;

public class ComboBoxElementTest {

    private ComboBoxElement testComboBox;

    @Before
    public void setUp() throws Exception {
        Transform testTransform = new Transform(Optional.empty());
        testComboBox = new ComboBoxElement(testTransform, new String[]{"one", "two"});
    }

    @org.junit.Test
    public void test_getValue() throws Exception {
        Assert.assertEquals(testComboBox.getValue(), "one");
    }

    @org.junit.Test
    public void test_setValue_nominal() throws Exception {
        testComboBox.setValue((short)1);
        Assert.assertEquals(testComboBox.getValue(), "two");
    }

    @org.junit.Test(expected = AssertionError.class)
    public void test_setValue_out_of_bounds() throws Exception {
        testComboBox.setValue((short)2);
    }

}
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ListElementTest {

    @Test
    public void test_add() throws Exception {
        int lengthBefore = 0;
        ListElement testListElement = new ListElement(new Transform(Optional.empty()), lengthBefore);
        testListElement.add();
        int lengthAfter = testListElement.new TestHook().getLength();
        Assert.assertEquals(lengthBefore+1, lengthAfter);
    }

    @Test
    public void test_subtract_nominal() throws Exception {
        int lengthBefore = 1;
        ListElement testListElement = new ListElement(new Transform(Optional.empty()), lengthBefore);
        testListElement.subtract();
        int lengthAfter = testListElement.new TestHook().getLength();
        Assert.assertEquals(lengthBefore, lengthAfter+1);
    }

    @Test
    public void test_subtract_zero_length() throws Exception {
        ListElement testListElement = new ListElement(new Transform(Optional.empty()), 0);
        testListElement.subtract();
        int lengthAfter = testListElement.new TestHook().getLength();
        Assert.assertEquals(lengthAfter, 0);
    }

    @Test
    public void test_clear_nominal() throws Exception {
        ListElement testListElement = new ListElement(new Transform(Optional.empty()), 1);
        testListElement.clear();
        int lengthAfter = testListElement.new TestHook().getLength();
        Assert.assertEquals(lengthAfter, 0);
    }

    @Test
    public void test_clear_zero_length() throws Exception {
        ListElement testListElement = new ListElement(new Transform(Optional.empty()), 0);
        testListElement.clear();
        int lengthAfter = testListElement.new TestHook().getLength();
        Assert.assertEquals(lengthAfter, 0);
    }

}
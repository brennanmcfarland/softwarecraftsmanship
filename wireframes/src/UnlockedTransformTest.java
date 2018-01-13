import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UnlockedTransformTest {

    @Test
    public void test_move_nominal() throws Exception {
        new TransformTest().test_move_nominal();
    }

    @Test
    public void test_move_zero() throws Exception {
        new TransformTest().test_move_zero();
    }

    @Test
    public void test_sendAbove_nominal() throws Exception {
        new TransformTest().test_sendAbove_nominal();
    }

    @Test
    public void test_sendBelow_nominal() throws Exception {
        new TransformTest().test_sendBelow_nominal();
    }

    @Test
    public void test_sendToTop_nominal() throws Exception {
        new TransformTest().test_sendToTop_nominal();
    }

    @Test
    public void sendToBottom() throws Exception {
        new TransformTest().test_sendToBottom_nominal();
    }

}
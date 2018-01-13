import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    @Test
    public void test_verifyPositive_nominal() throws Exception {
        Pair.verifyPositive(new Pair<Integer>(0, 0));
    }

    @Test(expected = AssertionError.class)
    public void test_verifyPositive_negative_x() throws Exception {
        Pair.verifyPositive(new Pair<Integer>(-1, 3));
    }

    @Test(expected = AssertionError.class)
    public void test_verifyPositive_negative_y() throws Exception {
        Pair.verifyPositive(new Pair<Integer>(0, -1));
    }

}
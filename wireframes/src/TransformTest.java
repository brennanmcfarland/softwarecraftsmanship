import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class TransformTest {

    @Test
    public void test_move_nominal() throws Exception {
        test_move(20, 30);
    }

    @Test
    public void test_move_zero() throws Exception {
        test_move(0, 0);
    }

    private void test_move(int x, int y) {
        Transform testTransform = new Transform(Optional.empty());
        Pair<Integer> oldLocation = testTransform.getLocalLocation();
        testTransform.move(x, y);
        Pair<Integer> newLocation = testTransform.getLocalLocation();
        Assert.assertEquals(oldLocation.getX()+x, newLocation.getX()+0); //+0 to disambiguate method call
        Assert.assertEquals(oldLocation.getY()+y, newLocation.getY()+0);
    }

    @Test
    public void test_sendAbove_nominal() throws Exception {
        Transform aboveTransform = new Transform(Optional.empty());
        Transform belowTransform = new Transform(Optional.empty());
        aboveTransform.sendAbove(belowTransform);
        Assert.assertEquals(aboveTransform.getBelow().get(), belowTransform);
        Assert.assertEquals(belowTransform.getAbove().get(), aboveTransform);
    }

    @Test
    public void test_sendBelow_nominal() throws Exception {
        Transform aboveTransform = new Transform(Optional.empty());
        Transform belowTransform = new Transform(Optional.empty());
        belowTransform.sendBelow(aboveTransform);
        Assert.assertEquals(aboveTransform.getBelow().get(), belowTransform);
        Assert.assertEquals(belowTransform.getAbove().get(), aboveTransform);
    }

    @Test
    public void test_sendToTop_nominal() throws Exception {
        Transform[] transforms = getOrderedTransforms();
        transforms[0].sendToTop();
        Assert.assertFalse(transforms[0].getAbove().isPresent());
        Assert.assertEquals(transforms[0].getBelow().get(), transforms[transforms.length-1]);
    }

    @Test
    public void test_sendToBottom_nominal() throws Exception {
        Transform[] transforms = getOrderedTransforms();
        transforms[transforms.length-1].sendToBottom();
        Assert.assertFalse(transforms[transforms.length-1].getBelow().isPresent());
        Assert.assertEquals(transforms[transforms.length-1].getAbove().get(), transforms[0]);
    }

    @Test
    public void test_getGlobalLocation_nominal() throws Exception {
        Transform[] transforms = getGlobalLocation_moved(1);
        for(int i=0; i<transforms.length; i++) {
            assertGlobalLocation(transforms, i, i+1);
        }
    }

    @Test
    public void test_getGlobalLocation_moved_zero() throws Exception {
        Transform[] transforms = getGlobalLocation_moved(0);
        for(int i=0; i<transforms.length; i++) {
            assertGlobalLocation(transforms, i, 0);
        }
    }

    private Transform[] getGlobalLocation_moved(int newCoordinate) {
        Transform[] transforms = getParentedTransforms();
        for(Transform transform : transforms) {
            transform.move(newCoordinate, newCoordinate);
        }
        return transforms;
    }

    @Test
    public void test_getGlobalLocation_no_parent() throws Exception {
        Transform[] transforms = new Transform[]{ new Transform(Optional.empty()) };
        transforms[0].move(3, 3);
        assertGlobalLocation(transforms, 0, 3);
    }

    @Test
    public void test_getGlobalLocation_initial_location() throws Exception {
        Transform[] transforms = new Transform[]{ new Transform(Optional.empty()) };
        assertGlobalLocation(transforms, 0, 0);
    }

    private void assertGlobalLocation(Transform[] transforms, int i, int locationCoordinate) {
        Assert.assertEquals((long)transforms[i].getGlobalLocation().getX(), locationCoordinate);
        Assert.assertEquals((long)transforms[i].getGlobalLocation().getY(), locationCoordinate);
    }

    private Transform[] getOrderedTransforms() {
        Transform[] transforms = initializeTransforms();
        for(int i=1; i<transforms.length; i++) {
            orderTransform(transforms, i);
        }
        return transforms;
    }

    private Transform[] getParentedTransforms() {
        Transform[] transforms = initializeTransforms();
        for(int i=1; i<transforms.length; i++) {
            parentTransform(transforms, i);
        }
        return transforms;
    }

    private Transform[] initializeTransforms() {
        Transform[] transforms = new Transform[5];
        transforms[0] = new Transform(Optional.empty());
        return transforms;
    }

    private void orderTransform(Transform[] transforms, int i) {
        transforms[i] = new Transform(Optional.empty());
        transforms[i].setBelow(Optional.of(transforms[i-1]));
        transforms[i-1].setAbove(Optional.of(transforms[i]));
    }

    private void parentTransform(Transform[] transforms, int i) {
        transforms[i] = new Transform(Optional.of(transforms[i-1]));
    }

}
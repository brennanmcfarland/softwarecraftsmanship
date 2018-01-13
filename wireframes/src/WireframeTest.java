import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class WireframeTest {

    @Test
    public void add() throws Exception {
        Transformable transformable = addTransformable();
        Assert.assertTrue(Wireframe.TestHook.getTransformables().contains(transformable));
    }

    @Test
    public void remove() throws Exception {
        Wireframe wireframe = new Wireframe();
        Transformable transformable = addTransformable();
        wireframe.remove(transformable);
        Assert.assertFalse(Wireframe.TestHook.getTransformables().contains(transformable));
    }

    private Transformable addTransformable() {
        Transformable transformable = new ParagraphElement(new Transform(Optional.empty()));
        Wireframe.add(transformable);
        return transformable;
    }

}
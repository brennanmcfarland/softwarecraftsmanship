import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a rounded box.  It doesn't really contain anything, being
 * just a shape, but it still has the same properties of any other element, being
 * transformable, scalable, editable, etc.
 */
class RoundedBoxElement extends TransformableScalable implements Element {

    private Editable editable;

    /**
     * Create a new rounded box element
     * @param transform The associated transform of this rounded box element
     */
    RoundedBoxElement(Transform transform) {
        super(transform);
        editable = new Editable(transform);
    }

    @Override
    public Editable getEditable() {
        return editable;
    }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Label("I'm a box!"), 150, 150);
    }
}

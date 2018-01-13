import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a paragraph.  A paragraph on a wireframe is represented by
 * blocks and thus has no actual text content.
 */
class ParagraphElement extends TransformableScalable implements Element {

    private Editable editable;

    /**
     * Create a paragraph element with the given transform
     * @param transform The transform associated with this paragraph element
     */
    ParagraphElement(Transform transform) {
        super(transform);
        editable = new Editable(transform);
    }

    @Override
    public Editable getEditable() {
        return editable;
    }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Label("paragraph: Billy is great"), 200, 150);
    }
}

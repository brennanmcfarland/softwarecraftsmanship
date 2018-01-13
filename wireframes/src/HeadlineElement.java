import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a headline.  Its content is represented in block form, so
 * the element itself contains no text
 */
class HeadlineElement extends TransformableScalable implements Element {

    private Editable editable;

    /**
     * Create a new headline
     * @param transform The associated transform of this headline
     */
    HeadlineElement(Transform transform) {
        super(transform);
        editable = new Editable(transform);
    }

    @Override
    public Editable getEditable() {
        return editable;
    }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Label("headline: Billy grades mercifully"), 250, 50);
    }
}

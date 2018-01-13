import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a list.  A list is represented in block form so it has no
 * contents, but it does have a length which is the number of list items to display.
 */
class ListElement extends TransformableScalable implements Element {

    private Editable editable;
    private int length;

    /**
     * Create a list element, with a given number of list elements
     * @param transform The associated transform component of this element
     * @param length The number of items to display in this list element
     */
    ListElement(Transform transform, int length) {
        super(transform);
        editable = new Editable(transform);
        this.length = length;
    }

    /**
     * Wireframe representation of the list has no contents, so the number of items is just
     * a representation
     */
    void add() { length++; }

    /**
     * Attempting to subtract items from an empty list will do nothing
     */
    void subtract() { length = Math.max(0, --length); }

    /**
     * Set the list element to be empty of items
     */
    void clear() { length = 0; }

    @Override
    public Editable getEditable() {
        return editable;
    }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Label("I'm a list!  Billy is kind"), 200, 150);
    }

    class TestHook {
        int getLength() { return length; }
    }
}

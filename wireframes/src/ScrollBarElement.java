import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a scroll bar element.  It inherits from FractionalElement,
 * its value representing the fraction to the end that the scroll bar is scrolled.  No
 * distinction is made between horizontal and vertical scroll bars, and none is needed
 * except for the UI itself.
 */
class ScrollBarElement extends TransformableScalable implements Element, FractionalElement {

    private Editable editable;
    private float value;

    /**
     * Create a scroll bar element with the default scroll value
     * @param transform The associated transform of this scroll bar element
     */
    ScrollBarElement(Transform transform) {
        super(transform);
        editable = new Editable(transform);
        this.value = 0f;
    }

    /**
     * Create a scroll bar element with a given scroll value
     * @param transform The associated transform of this scroll bar element
     * @param value The initial scroll value
     */
    ScrollBarElement(Transform transform, float value) {
        super(transform);
        editable = new Editable(transform);
        this.value = value;
    }

    @Override
    public Editable getEditable() { return editable; }

    @Override
    public void setValue(float value) { this.value = value; }

    @Override
    public float getValue() { return value; }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Scrollbar(), 20, 100);
    }
}

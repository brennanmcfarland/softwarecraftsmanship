import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a slider.  It inherits from FractionalElement, its value
 * representing the fraction to the right or left that the slider is slid.
 */
class SliderElement extends TransformableScalable implements Element, FractionalElement {

    private Editable editable;
    private float value;

    /**
     * Create a slider element with the default slid value
     * @param transform The associated transform of this slider element
     */
    SliderElement(Transform transform) {
        super(transform);
        editable = new Editable(transform);
        this.value = 0f;
    }

    /**
     * Create a slider element with the given slid value
     * @param transform The associated transform of this slider element
     * @param value The initial slid value
     */
    SliderElement(Transform transform, float value) {
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
        return initializeComponent(new Scrollbar(), 150, 100);
    }
}

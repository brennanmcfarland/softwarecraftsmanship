import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a progress bar.  It inherits from FractionalElement, and
 * its fractional value represents the percent progress.
 */
class ProgressBarElement extends TransformableScalable implements Element, FractionalElement {

    private Editable editable;
    private float value;

    /**
     * Create a new progress bar element with the default progress value
     * @param transform The associated transform of this progress bar element
     */
    ProgressBarElement(Transform transform) {
        super(transform);
        editable = new Editable(transform);
        this.value = 0f;
    }

    /**
     * Create a new progress bar element with a given progress value
     * @param transform The associated transform of this progress bar element
     * @param value The progress value to set the progress bar at initially
     */
    ProgressBarElement(Transform transform, float value) {
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
        return initializeComponent(new Scrollbar(), 200, 50);
    }
}

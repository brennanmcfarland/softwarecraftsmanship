import java.awt.*;
import java.util.Optional;

/**
 * Provides a skeleton implementation for a transformable scalable object, to make implementing
 * Transformable and Scalable easier and to avoid repeated code
 */
abstract class TransformableScalable {

    private Transform transform;

    public TransformableScalable(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }

    public Pair<Integer> getScale() {
        return transform.getScale();
    }

    /**
     * must be public because the interface enforces it
     */
    public void setScale(Pair<Integer> scale) {
        transform.setScale(scale);
    }

    /**
     * Helper method for packaging a JFrame Component to return
     * @param component The component to package
     * @param xScale The new x scale of the component
     * @param yScale The new y scale of the component
     * @return an Optional possibly containing the resized JFrame Component
     */
    Optional<Component> initializeComponent(Component component, int xScale, int yScale) {
        component.setSize(xScale, yScale);
        return Optional.of(component);
    }

}

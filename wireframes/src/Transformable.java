import java.awt.*;
import java.util.Optional;

/**
 * Guarantees an inheriting object can be transformed.  As with Scalable and
 * FractionalElement, it's just enforcing setters but with the option of adding other logic
 * behind the scenes like a C# property.  GetComponent() also ensures that a transformable
 * can return a JFrame Component (or empty optional if it isn't associated with one)
 */
interface Transformable extends Scalable {
    Transform getTransform();
    Optional<Component> getComponent();
}

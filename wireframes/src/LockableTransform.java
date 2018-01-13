import java.awt.*;
import java.util.Collection;
import java.util.Optional;

/**
 * LockableTransform is the interface for what's behind the Transform facade class, allowing
 * an object to be transformed without knowing whether it's in the locked or unlocked state
 * or how these methods are actually implemented.  Essentially Transform delegates methods
 * to this interface, which then results in the appropriate behavior given the state of the
 * Transform based on which class implementing LockableTransform is present.  For details
 * about the methods, see Transform.
 */
interface LockableTransform {

    void move(int newX, int newY);
    void sendAbove(Transform other);
    void sendBelow(Transform other);
    void sendToTop(Transform other);
    void sendToBottom(Transform other);
    Optional<Transform> getAbove();
    Optional<Transform> getBelow();
    void setAbove(Optional<Transform> other);
    void setBelow(Optional<Transform> other);
    Optional<Transform> getParent();
    Collection<Transform> getChildren();
    Pair<Integer> getLocalLocation();
    Transform getTransform();
    Pair<Integer> getScale();
    void setScale(Pair<Integer> scale);
}

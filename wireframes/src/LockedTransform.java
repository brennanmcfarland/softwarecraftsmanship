import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * The implementation behind the Transform facade class for an object that is locked.
 * A locked transform cannot be moved or reordered, and any attempts to do so will have no
 * effect.
 */
class LockedTransform extends TransformData implements LockableTransform {

    private final Transform transform;

    /**
     * Create a new locked transform.  Uses transform's overridable sendAbove() method
     * to put the new transform on top.
     * @param transform The transform that is the facade for this locked transform
     * @param parent The parent of this transform
     */
    LockedTransform(Transform transform, Optional<Transform> parent) {
        this.transform = transform;
        location = new Pair<Integer>(0,0);
        scale = new Pair<Integer>(100, 100);
        children = new HashSet<Transform>();
        this.parent = parent;
        parent.ifPresent(this::sendAbove);
    }

    /**
     * Create an equivalent locked transform from an existing lockable transform, used when
     * locking an object.  Uses lockableTransform's overridable methods getLocalLocation(),
     * getScale(), getChildren(), getParent(), getAbove() and getBelow() to replace the
     * transform in the ordering and transform hierarchy.
     * @param transform The transform that is the facade for this now locked transform
     * @param lockableTransform The parent of this transform
     */
    LockedTransform(Transform transform, LockableTransform lockableTransform) {
        this.transform = transform;
        location = lockableTransform.getLocalLocation();
        scale = lockableTransform.getScale();
        children = lockableTransform.getChildren();
        parent = lockableTransform.getParent();
        above = lockableTransform.getAbove();
        below = lockableTransform.getBelow();
    }

    /**
     * Has no effect, as a locked object cannot be moved
     * @param newX
     * @param newY
     */
    @Override
    public void move(int newX, int newY) {
    }

    /**
     * Has no effect, as a locked object cannot be reordered
     * @param other
     */
    @Override
    public void sendAbove(Transform other) {
    }

    /**
     * Has no effect, as a locked object cannot be reordered
     * @param other
     */
    @Override
    public void sendBelow(Transform other) {
    }

    /**
     * Has no effect, as a locked object cannot be reordered
     * @param other
     */
    @Override
    public void sendToTop(Transform other) {
    }

    /**
     * Has no effect, as a locked object cannot be reordered
     * @param other
     */
    @Override
    public void sendToBottom(Transform other) {
    }

    @Override
    public Optional<Transform> getAbove() {
        return above;
    }

    @Override
    public Optional<Transform> getBelow() {
        return below;
    }

    /**
     * Despite not being able to reorder locked objects, above and below's setters must be
     * public because the LockableTransform has to include them so they can be
     * polymorphically called from Transform when reordering another object (and interface
     * methods must be public).  They should not be used outside of that case.
     * @param other
     */
    @Override
    public void setAbove(Optional<Transform> other) {
        above = other;
    }

    /**
     * Despite not being able to reorder locked objects, above and below's setters must be
     * public because the LockableTransform has to include them so they can be
     * polymorphically called from Transform when reordering another object (and interface
     * methods must be public).  They should not be used outside of that case.
     * @param other
     */
    @Override
    public void setBelow(Optional<Transform> other) {
        below = other;
    }

    @Override
    public Optional<Transform> getParent() {
        return parent;
    }

    @Override
    public Collection<Transform> getChildren() { return children; }

    @Override
    public Pair<Integer> getLocalLocation() {
        return location;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public Pair<Integer> getScale() {
        return scale;
    }

    /**
     * @param scale Scale is checked to be good data when set.  If it is not, a fatal error
     *              is created.
     */
    @Override
    public void setScale(Pair<Integer> scale) {
        Pair.verifyPositive(scale);
        this.scale = scale;
    }

}

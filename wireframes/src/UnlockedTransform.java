import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * The implementation behind the Transform facade class for an object that is unlocked.
 * A locked transform cannot be moved or reordered, and everything else.
 */
class UnlockedTransform extends TransformData implements LockableTransform {

    private final Transform transform;

    /**
     * Create a new unlocked transform
     * @param transform The transform that is the facade for this unlocked transform
     * @param parent The parent of this transform
     */
    UnlockedTransform(Transform transform, Optional<Transform> parent) {
        this.transform = transform;
        location = new Pair<Integer>(0,0);
        scale = new Pair<Integer>(100, 100);
        children = new HashSet<Transform>();
        this.parent = parent;
    }

    /**
     * Create an equivalent unlocked transform from an existing lockable transform, used when
     * unlocking an object.  Uses overridable methods getLocalLocation(), getScale(),
     * getChildren(), getParent(), getAbove(), and getBelow() in LockabelTransform.
     * @param transform The transform that is the facade for this now unlocked transform
     * @param lockableTransform The parent of this transform
     */
    UnlockedTransform(Transform transform, LockableTransform lockableTransform) {
        this.transform = transform;
        location = lockableTransform.getLocalLocation();
        scale = lockableTransform.getScale();
        children = lockableTransform.getChildren();
        parent = lockableTransform.getParent();
        above = lockableTransform.getAbove();
        below = lockableTransform.getBelow();
    }

    /**
     * Move the object to a new location
     * @param newX The new x location
     * @param newY The new y location
     */
    @Override
    public void move(int newX, int newY) {
        location = new Pair<Integer>(newX, newY);
    }

    /*
    function sendAbove(Transfrom other):
        if(isLocked) then return
        Transform newAbove = other.getAbove()
        this.getBelow().setAbove() <- this.getAbove()
        other.above = this
        this.below = other
        this.above = newAbove
     */

    /**
     * Send this transform's object just above another in the list of orderings (so it
     * shows up in front of it on the wireframe).  Uses overridable methods getAbove(),
     * getBelow(), setAbove(), and setBelow().
     * @param other The object to send this one above
     */
    @Override
    public void sendAbove(Transform other) {
        Optional<Transform> newAbove = other.getAbove();
        Optional<Transform> oldBelow = this.getBelow();
        oldBelow.ifPresent(transform -> transform.setAbove(newAbove));
        other.setAbove(Optional.of(transform));
        this.setBelow(Optional.of(other));
        this.setAbove(newAbove);
    }

    /**
     * Send this transform's object just below another in the list of orderings (so it shows
     * up behind it on the wireframe).  Uses overridable methods getAbove(), getBelow(),
     * setAbove(), and setBelow().
     * @param other The object to send this one below
     */
    @Override
    public void sendBelow(Transform other) {
        Optional<Transform> newBelow = other.getBelow();
        Optional<Transform> oldAbove = other.getAbove();
        oldAbove.ifPresent(transform -> transform.setBelow(newBelow));
        other.setBelow(Optional.of(transform));
        this.setAbove(Optional.of(other));
        this.setBelow(newBelow);
    }

    /**
     * Put this object on top of everything else (in its ordering list)
     * Uses overridable method sendAbove().
     */
    @Override
    public void sendToTop(Transform other) {
        while(true) {
            Optional<Transform> above = getAbove();
            if(!above.isPresent()) { return; }
            sendAbove(above.get());
        }
    }

    /**
     * Put this object below everything else (in its ordering list)
     * Uses overridable method sendBelow().
     */
    @Override
    public void sendToBottom(Transform other) {
        while(true) {
            Optional<Transform> below = getBelow();
            if(!below.isPresent()) { return; }
            sendBelow(below.get());
        }
    }

    @Override
    public Optional<Transform> getAbove() {
        return above;
    }

    @Override
    public Optional<Transform> getBelow() {
        return below;
    }

    @Override
    public void setAbove(Optional<Transform> other) {
        above = other;
    }

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

import java.awt.*;
import java.util.Collection;
import java.util.Optional;

/**
 * Transform is analogous to a geometric transform; it essentially is responsible for a
 * location, order, scale, and any other space-related property of an object.  The Transform
 * component is an example of the Component design pattern.  For example, a transformable
 * (say a box) can get the reference to its associated transform to get its position or
 * send it in front of something else.
 * Transform is a facade class for either a locked or unlocked transform, all
 * transformations are sent to a Transform which then delegates to the appropriate locked or
 * unlocked transform method via the LockableTransform interface using polymorphism.
 * Transforms can be combined to form two implicit data structures.  The first is a linked
 * list formed from the ordering of a collection of transforms, which can be traversed via
 * the getAbove() and getBelow() methods.  The second is a parenting tree.  When a transform
 * is parented to another transform (and each transform can only have one parent), the child
 * transform moves with its parent and the location is stored relative to the parent.
 */

class Transform {

    private LockableTransform lockableTransform;
    private Component component;

    /**
     * Create a new Transform and send it above its parent if one is given.  Uses overridable
     * method getParent() and sendAbove().
     * @param parent The parent of this new transform
     */
    Transform(Optional<Transform> parent) {
        lockableTransform = new UnlockedTransform(this, parent);
        lockableTransform.getParent().ifPresent(this::sendAbove);
    }

    /**
     * Set the new local location
     * @param newX  The new local x coordinate
     * @param newY The new local y coordinate
     */
    void move(int newX, int newY) {
        lockableTransform.move(newX, newY);
    }

    /**
     * Send this object above another, ie, reorder it to be in front of the other
     * @param other The transform of the object to be sent above
     */
    void sendAbove(Transform other) {
        lockableTransform.sendAbove(other);
    }

    /**
     * Send this object below another, ie, reorder it to be behind the other
     * @param other The transform of the object to be sent behind
     */
    void sendBelow(Transform other) {
        lockableTransform.sendBelow(other);
    }

    /*
    function sendToTop():
        if(isLocked) then return
        while(!isEmpty(this.above)):
            sendAbove(this.above)
     */

    /**
     * Put this object on top of everything else (in its ordering list)
     * Uses overridable methods sendAbove() and getAbove().
     */
    void sendToTop() {
        while(this.getAbove().isPresent()) {
            sendAbove(this.getAbove().get());
        }
    }

    /**
     * Put this object below everything else (in its ordering list)
     * Uses overridable methods sendBelow() and getBelow().
     */
    void sendToBottom() {
        while(this.getBelow().isPresent()) {
            sendBelow(this.getBelow().get());
        }
    }

    public Optional<Transform> getAbove() {
        return lockableTransform.getAbove();
    }

    public Optional<Transform> getBelow() {
        return lockableTransform.getBelow();
    }

    /**
     * In contrast to sendAbove, this just sticks this object into a given point in the
     * order, almost like a setter except that neighboring above and below references have
     * to be updated to be consistent.  Consider it like an insert operation into the
     * ordering list.  Uses overridable methods setAbove() and setBelow() in lockableTransform.
     * @param other The transform of the object to set this one above
     */
    void setAbove(Optional<Transform> other) {
        lockableTransform.setAbove(other);
        other.ifPresent(transform -> transform.lockableTransform.setBelow(Optional.of(this)));
    }

    /**
     * In contrast to sendBelow, this just sticks this object into a given point in the
     * order, almost like a setter except that neighboring above and below references have
     * to be updated to be consistent.  Consider it like an insert operation into the
     * ordering list.  Uses overridable methods setBelow() and setAbove() in lockableTransform.
     * @param other The transform of the object to set this one below
     */
    void setBelow(Optional<Transform> other) {
        lockableTransform.setBelow(other);
        other.ifPresent(transform -> transform.lockableTransform.setAbove(Optional.of(this)));
    }

    Optional<Transform> getParent() {
        return lockableTransform.getParent();
    }

    /**
     * @return The local location is the location relative to the parent transform
     */
    Pair<Integer> getLocalLocation() {
        return lockableTransform.getLocalLocation();
    }

    /**
     * @return The global location, ie the location not relative to any parent but to the
     * "global space" of the wireframe.  Uses overridable methods getLocalLocation().
     */
    Pair<Integer> getGlobalLocation() {
        int x = 0;
        int y = 0;
        Optional<Transform> ancestor = Optional.of(this);
        while(ancestor.isPresent()) {
            Transform ancestorTransform = ancestor.get();
            x += ancestorTransform.getLocalLocation().getX();
            y += ancestorTransform.getLocalLocation().getY();
            ancestor = ancestor.get().getParent();
            if(!ancestor.isPresent()) { return new Pair<>(x, y); }
        }
        return new Pair<>(x, y);
    }

    /**
     * Lock the transform so it cannot be moved or reordered
     */
    void lock() {
        lockableTransform = new LockedTransform(this, lockableTransform);
    }

    /**
     * Unlock the transform so it can be moved or reordered
     */
    void unlock() {
        lockableTransform = new UnlockedTransform(this, lockableTransform);
    }

    public Pair<Integer> getScale() { return lockableTransform.getScale(); }

    void setScale(Pair<Integer> scale) { lockableTransform.setScale(scale);}
}

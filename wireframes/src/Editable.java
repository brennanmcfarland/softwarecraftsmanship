import java.util.Collection;
import java.util.HashSet;

/**
 * Editable is an implementation of the component design pattern, being the component for
 * all behaviors relating to the "editability" of an object, ie, its transform and
 * annotations.  Classes needing to implement these behaviors can simply contain an Editable
 * instance.
 */
class Editable {

    private Transform transform;
    Collection<Annotation> annotations;

    /**
     * Create a new editable
     * @param transform The transform this editable will contain, ie, associated with the
     *                  containing object
     */
    Editable(Transform transform) {
        this.transform = transform;
        annotations = new HashSet();
    }

    public Transform getTransform() { return transform; }
}

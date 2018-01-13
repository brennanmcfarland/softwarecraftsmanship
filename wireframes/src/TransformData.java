import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * The data associated with a locked or unlocked transform, for location, scale, etc.
 * Children is the transforms parented to the lockable transform containing this class.
 * Parent is the parent transform of the lockable transform containing this class.
 */
class TransformData {

    protected Pair<Integer> location;
    protected Pair<Integer> scale;
    protected Collection<Transform> children;
    protected Optional<Transform> parent;
    protected Optional<Transform> above;
    protected Optional<Transform> below;
    protected Component component;

    /**
     * Create a new TransformData, with the default location and scale
     */
    TransformData() {
        location = new Pair<Integer>(0, 0);
        scale = new Pair<Integer>(100, 100);
        children = new HashSet<>();
        parent = Optional.empty();
        above = Optional.empty();
        below = Optional.empty();
    }
}

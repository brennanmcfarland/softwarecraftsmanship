import java.awt.*;
import java.util.Collection;
import java.util.Optional;

/**
 * A group of Editables, which can be either associated with elements or other, nested
 * Groups.  Transformables can be grouped and ungrouped by creating and destroying Groups,
 * but once created the Transformables associated with a given Group cannot be changed.
 * Transformables in a Group can only be transformed with the whole group, ie, all
 * Transformables contained by a Group are locked.  Since it implements Transformable, Groups
 * can be transformed just like other Transformables, and can also be scaled and otherwise
 * edited, annotated, etc.
 */
class Group extends TransformableScalable implements Transformable {

    private Editable editable;
    private Collection<Transformable> transformables;

    /**
     * Group a collection of transformables.  Since members of a group can only be moved
     * as a group, all their transforms are locked via the overridable lock() method.
     * @param transform This group's associated transform
     * @param transformables The transformables to make a part of this group
     */
    Group(Transform transform, Collection<Transformable> transformables) {
        super(transform);
        editable = new Editable(transform);
        this.transformables = transformables;
        for(Transformable transformable : this.transformables) {
            editable.getTransform().lock(); //lock group members so only the group can be modified
        }
    }

    /**
     * Ungroup this group's transformables.
     * Note that if external references point to this group, whatever is referencing it is
     * responsible for ceasing to do so in order that the group object itself can be garbage
     * collected.  Ungrouping involves unlocking and moving the transforms via overridable
     * methods unlock() and move().
     * @return The (now ungrouped) collection of transformables that were in the group
     */
    Collection<Transformable> ungroup() {
        //reset the positions of transforms
        for(Transformable transformable : transformables) {
            transformable.getTransform().unlock();
            transformable.getTransform().move(
                    transformable.getTransform().getLocalLocation().getX()
                            + this.editable.getTransform().getLocalLocation().getX(),
                        transformable.getTransform().getLocalLocation().getY()
                            +this.editable.getTransform().getLocalLocation().getY());
        }
        return transformables;
    }

    Editable getEditable() {
        return editable;
    }

    Collection<Transformable> getTransformables() {
        return transformables;
    }

    @Override
    public Optional<Component> getComponent() {
        return Optional.empty();
    }
}

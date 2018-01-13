import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * The wireframe/canvas for a collection of transformables (elements and groups).
 * This class and its methods are public because it is supposed to act as an interface for
 * the rest of the package, in addition to containing its own main method for demonstration.
 * The wireframe is essentially a dumb container, with all the useful functionality contained
 * in the transformables themselves.  All it does is keep track of the collection of
 * transformables on the wireframe and renders their corresponding JFrame Components in a
 * UI.
 *
 * I had to change the signature of methods to accept Transformables
 * instead of Editables as there was otherwise no way to access the Transformable to add it
 * to transformables (also Transformables is at the appropriate level of abstraction,
 * editable is really only a component)
 */
public class Wireframe {

    private static Collection<Transformable> transformables = new HashSet<>();
    private static JFrame frame = new JFrame("wireframe");

    /**
     * Creates a bunch of different elements and displays them via the JFrame UI framework in
     * a window for demonstration.
     * @param args
     */
    public static void main(String[] args) {
        frame = new JFrame("wireframe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(50, 50, 800, 600);
        frame.setLayout(null);

        /*
        here I'm just making a list of elements and their locations to add them to the wireframe,
        iterating over this list to avoid repeated code
         */
        Element[] elementsToAdd = new Element[]{
                new TextElement(new Transform(Optional.empty()), "Text: Lorem ipsum"),
                new SliderElement(new Transform(Optional.empty())),
                new ScrollBarElement(new Transform(Optional.empty()), 4.0f),
                new RoundedBoxElement(new Transform(Optional.empty())),
                new ProgressBarElement(new Transform(Optional.empty()), .1f),
                new ParagraphElement(new Transform(Optional.empty())),
                new ListElement(new Transform(Optional.empty()), 3),
                new ImageElement(new Transform(Optional.empty()), null),
                new HeadlineElement(new Transform(Optional.empty())),
                new ComboBoxElement(new Transform(Optional.empty()), new String[]{"a", "b", "c"})
        };
        int[][] elementLocationsToAdd = new int[][] {{0, 0}, {100, 0}, {200, 0}, {300, 0},
                                                    {0, 100}, {100, 100}, {200, 100}, {300, 100},
                                                    {0, 200}, {100, 200}};
        for(int i=0; i<elementsToAdd.length; i++) {
            addAt(elementLocationsToAdd[i][0], elementLocationsToAdd[i][1], elementsToAdd[i]);
        }

        render();
    }

    /**
     * Displays the corresponding JFrame Component for every element in a UI window.
     */
    public static void render() {
        for(Transformable transformable : transformables) {
            Optional<Component> componentOptional = transformable.getComponent();
            if(componentOptional.isPresent()) {
                Component component = componentOptional.get();
                Pair<Integer> location = transformable.getTransform().getGlobalLocation();
                component.setLocation(location.getX(), location.getY());
                if(transformable instanceof FractionalElement) {
                    ((Adjustable)component).setValue((int)(
                            (FractionalElement) transformable).getValue()*100);
                }
                frame.add(component);
            } else {
                //it's a group, render its children; TODO: I didn't get to this
            }
        }
        frame.setVisible(true);
    }

    /**
     * Add a new element or group to this wireframe
     * @param transformable The element or group to add
     */
    public static void add(Transformable transformable) {
        //order it on top of everything else
        //if there's anything in transformables, put it on top, otherwise, it's fine as-is
        if(transformables.iterator().hasNext()) {
            transformable.getTransform().setAbove(Optional.of(transformables.iterator().next().getTransform()));
            transformable.getTransform().sendToTop();
        }
        transformables.add(transformable);
    }

    /**
     * Add a new element or group to this wireframe at the given position
     * @param x x coordinate for the new transformable's location
     * @param y y coordinate for the new transformable's location
     * @param transformable the new transformable to add
     */
    private static void addAt(int x, int y, Transformable transformable) {
        add(transformable);
        transformable.getTransform().move(x, y);
    }

    /**
     * Remove an existing element or group from this wireframe
     * @param transformable the element or group to remove
     */
    public static void remove(Transformable transformable) {
        //remove it from the list of ordering
        Optional<Transform> above = transformable.getTransform().getAbove();
        Optional<Transform> below = transformable.getTransform().getBelow();
        above.ifPresent(transform -> transform.setBelow(below));
        below.ifPresent(transform -> transform.setAbove(above));
        //and from the collection of top-level transforms, if it is one (otherwise does nothing)
        transformables.remove(transformable);
    }

    /**
     * Group a collection of transfromables into a group and add the group to the wireframe.
     * Not all or any of the transformables need to already be in the wireframe; this allows
     * for greater flexibility in how grouping in wireframes is used, for example when
     * creating complex "superelements" composed of other elements not previously on the
     * wireframe that may have been stored for easier creation, or for other use cases.
     * @param transformables The transformables to group together
     */
    public static void group(Collection<Transformable> transformables) {
        for(Transformable transformable : transformables) {
            remove(transformable);
        }
        Group group = new Group(new Transform(Optional.empty()), transformables);
        transformables.add(group);
    }

    /**
     * Ungroup a group that may be in the wireframe.
     * The group itself is garbage collected; any references to it are responsible for
     * deleting it.
     * @param group The group to ungroup
     */
    public static void ungroup(Group group) {
        transformables.remove(group);
    }

    static class TestHook {
        static Collection<Transformable> getTransformables() { return transformables; }
    }
}

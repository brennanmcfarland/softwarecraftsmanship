/**
 * An element is essentially an individual "piece" that goes on the wireframe, for example
 * a paragraph, scroll bar, image, etc.  It is essentially the embodiment of a visual
 * component on the wireframe and everything it can do.  Inheriting from Transformable, it
 * can be transformed and can return a JFrame Component for visualizing it on a UI.
 */
interface Element extends Transformable {

    /**
     * @return The editable component (from the component design pattern) associated with
     * this element.  Usually uses overridable method initializeComponent in
     * transformableScalable().
     */
    Editable getEditable();
}

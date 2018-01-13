import java.awt.*;
import java.util.Optional;

/**
 * The element representation of an image.  Basically just a wrapper for an image with
 * element properties.
 */
class ImageElement extends TransformableScalable implements Element {

    private Editable editable;
    private Image image;

    /**
     * Create a new image
     * @param transform The transform associated with this element
     * @param image The image contents of this image element
     */
    ImageElement(Transform transform, Image image) {
        super(transform);
        editable = new Editable(transform);
        this.image = image;
    }

    public Image getImage() { return image; }

    @Override
    public Editable getEditable() {
        return editable;
    }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Label("I'm an image!"), 150, 150);
    }
}

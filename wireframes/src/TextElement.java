import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a block of text.  Unlike most other elements, TextElement
 * actually contains text, albeit canned placeholder text.
 */
class TextElement extends TransformableScalable implements TextualElement {

    private Editable editable;
    private CannedText cannedText;

    /**
     * Create a new text element containing the given cannedText
     * @param transform The associated transform of this text element
     * @param cannedText The placeholder text contained by this text element
     */
    TextElement(Transform transform, String cannedText) {
        super(transform);
        editable = new Editable(transform);
        this.cannedText = new CannedText(cannedText);
    }

    @Override
    public Editable getEditable() {
        return editable;
    }

    @Override
    public CannedText getCannedText() {
        return cannedText;
    }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Label(cannedText.getText()), 150, 100);
    }
}

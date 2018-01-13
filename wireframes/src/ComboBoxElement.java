import java.awt.*;
import java.util.Optional;

/**
 * The element representation of a combobox.  A combobox contains a collection of possible
 * (text) values to select from, one and only one of which is selected at any given time.
 * Once a combobox is created, the possible values cannot be changed, only the selection.
 */
class ComboBoxElement extends TransformableScalable implements Element {

    private Editable editable;
    String[] possibleValues;
    short valueIndex;

    /**
     * Create a new ComboBox element with the given values to choose from
     * @param transform The transform associated with this element
     * @param possibleValues The collection of values that can be chosen from
     */
    ComboBoxElement(Transform transform, String[] possibleValues) {
        super(transform);
        editable = new Editable(transform);
        this.possibleValues = possibleValues;
        valueIndex = 0;
    }

    /**
     * Create a new ComboBox element with the given values to choose from and the given value
     * preselected
     * @param transform The transform associated with this element
     * @param possibleValues The collection of values that can be chosen from
     * @param initialValueIndex The value to initially be selected (before the user selects)
     */
    ComboBoxElement(Transform transform, String[] possibleValues, short initialValueIndex) {
        super(transform);
        editable = new Editable(transform);
        this.possibleValues = possibleValues;
        valueIndex = initialValueIndex;
    }

    public String getValue() { return possibleValues[valueIndex]; }

    /**
     * @param valueIndex If the value index is outside the range of possible values, a fatal
     *                   error is triggered
     */
    void setValue(short valueIndex) {
        if(valueIndex >= possibleValues.length || valueIndex < 0) {
            ErrorHandler.getInstance().displayFatalError("Invalid combobox index");
        }
        this.valueIndex = valueIndex;
    }

    @Override
    public Editable getEditable() {
        return editable;
    }

    @Override
    public Optional<Component> getComponent() {
        return initializeComponent(new Label("I'm a combo box!  Gimme an A"), 200, 150);
    }
}

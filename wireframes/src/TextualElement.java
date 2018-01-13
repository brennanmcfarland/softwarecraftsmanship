/**
 * An element that has placeholder text, as opposed to a block representation of it.  As with
 * Scalable, it only enforces a getter, but there can be additional logic behind it if
 * required.
 */
interface TextualElement extends Element {
    CannedText getCannedText();
}

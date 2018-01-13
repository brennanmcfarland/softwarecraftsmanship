/**
 * An alignable block of immutable text, used as a placeholder for real text but in places
 * where blocks, squiggles or other representations are insufficient.  Basically use wherever
 * "Lorem Ipsum" would be used.  Elements can contain canned text if they have the
 * aforementioned needs.
 */
class CannedText implements Alignable {

    private String text;
    private Alignment alignment;

    /**
     * Create a new canned text with the default alignment
     * @param text The text contents itself of this canned text
     */
    CannedText(String text) {
        this.text = text;
        this.alignment = Alignment.LEFT;
    }

    /**
     * Create a new canned text with the given alignment
     * @param text The text contents itself of this canned text
     * @param alignment The alignment to set the text to
     */
    CannedText(String text, Alignment alignment) {
        this.text = text;
        this.alignment = alignment;
    }

    String getText() { return text; }

    @Override
    public void align(Alignment alignment) {
        this.alignment = alignment;
    }
}

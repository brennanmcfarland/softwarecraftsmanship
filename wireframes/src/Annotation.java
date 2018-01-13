/**
 * An annotation can be used to provide additional notes or information about a wireframe
 * element (which contains it) in the form of text for the purposes of wireframe development
 * or demonstration.  They can be shown or hidden and their text can be replaced, aligned,
 * and rescaled at will.
 */
class Annotation implements Alignable, Scalable {

    private Alignment alignment;
    private Pair<Integer> scale;
    private String text;
    private boolean hidden;

    /**
     * Create a new, empty annotation with the default alignment
     */
    Annotation() {
        alignment = Alignment.LEFT;
        scale = new Pair<Integer>(100, 100);
        text = "";
    }

    /**
     * Create an empty annotation with a given alignment.  Once text is added the alignment
     * will persist
     * @param alignment The alignment to set the text to
     */
    Annotation(Alignment alignment) {
        this.alignment = alignment;
        scale = new Pair<Integer>(100, 100);
        text = "";
    }

    /**
     * Show the annotation whether or not it was hidden before
     */
    void display() { hidden = false; }

    /**
     * Hide the annotation whether or not it was hidden before
     */
    void hide() { hidden = true; }

    public String getText() { return text; }

    void setText(String text) { this.text = text; }

    @Override
    public void align(Alignment alignment) { this.alignment = alignment; }

    @Override
    public Pair<Integer> getScale() { return scale; }

    @Override
    public void setScale(Pair<Integer> scale) { this.scale = scale; }
}

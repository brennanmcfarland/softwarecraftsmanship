import java.util.Collection;

/**
 * Guarantees an inheriting object can be annotated, that is, that annotations can be attached
 * to it to provide additional information or notes for reference (see Annotation).
 */
interface Annotatable {

    /**
     * @return All annotations associated with this object
     */
    Collection<Annotation> getAnnotations();

    /**
     * Add a new annotation to this object
     * @param annotation
     */
    void addAnnotation(Annotation annotation);

    /**
     * Remove a given annotation from this object
     * @param annotation The annotation to remove
     */
    void removeAnnotation(Annotation annotation);

    /**
     * Remove the annotation at a given index.  Since the details of keeping track of
     * annotations are left to the inheriting class, it is up to the class itself to
     * determine which annotation a given index maps to.
     * @param index
     */
    void removeAnnotation(int index);
}

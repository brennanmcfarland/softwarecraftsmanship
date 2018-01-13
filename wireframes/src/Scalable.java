/**
 * Guarantees an inheriting object can be scaled, ie resized.  Basically just enforces a
 * getter and setter, but it's more open-ended than an abstract class in case additional
 * logic needs to be implemented when getting or setting the scale, similar to a property
 *  in C#.
 */
public interface Scalable {

    Pair<Integer> getScale();
    void setScale(Pair<Integer> scale);
}

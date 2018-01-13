/**
 * A pair of two data of the same type.  Equals and hashCode are overridden based on the two
 * values in the pair.  A pair is for the most part immutable, though it can still (but
 * should not be) subclassed.
 * @param <T>
 */
class Pair<T> {

    private T x;
    private T y;

    /**
     * Create a new pair, initializing it with values that should never be changed afterwards
     * @param x
     * @param y
     */
    Pair(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    /**
     * A convenience method to verify that both data in the pair are nonnegative, creating
     * a fatal error otherwise
     * @param pair The pair to validate
     */
    public static void verifyPositive(Pair<Integer> pair) {
        if(pair.getX() < 0 || pair.getY() < 0) { ErrorHandler.getInstance().displayFatalError("negative pair values"); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?> pair = (Pair<?>) o;

        if (x != null ? !x.equals(pair.x) : pair.x != null) return false;
        return y != null ? y.equals(pair.y) : pair.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}

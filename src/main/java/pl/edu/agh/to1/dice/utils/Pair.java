package pl.edu.agh.to1.dice.utils;

/**
 * Author: Piotr Turek
 */
public class Pair<X, Y> {
    private final X left;
    private final Y right;

    public Pair(X left, Y right) {
        this.left = left;
        this.right = right;
    }

    public X getLeft() {
        return left;
    }

    public Y getRight() {
        return right;
    }
}

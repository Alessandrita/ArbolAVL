package Node;

public class AVLNode<T extends Comparable<? super T>> {

    private T value;
    private AVLNode<T> left;
    private AVLNode<T> right;
    private int count;
    private int height;
    private int balanceFactor;

    public AVLNode(T value) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.count = 0;
    }

    public AVLNode() {
        this.value = null;
        this.right = null;
        this.left = null;
        this.count = -1;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("%s (H %d, BF %d, C %d)",
                value.toString(), height, balanceFactor, count);
    }
}

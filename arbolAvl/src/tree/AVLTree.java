package tree;

import Excepciones.IsEmptyException;
import Node.AVLNode;

public interface AVLTree<T extends Comparable<T>> {

    boolean agregar(T value);

    long beetwen(T start, T end) throws IsEmptyException;

    T mayor() throws IsEmptyException;

    long height() throws IsEmptyException;

    void isEmpty() throws IsEmptyException;

    T menor() throws IsEmptyException;
    
    void inOrder() throws IsEmptyException;
    
    void preOrder() throws IsEmptyException;

    void posOrder() throws IsEmptyException;

    boolean borrar(T value) throws IsEmptyException;

    AVLNode search(T value) throws IsEmptyException;
}

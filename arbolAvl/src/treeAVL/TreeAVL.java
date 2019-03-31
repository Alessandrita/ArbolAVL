package treeAVL;

import Excepciones.IsEmptyException;
import Node.AVLNode;
import tree.AVLTree;

public class TreeAVL<T extends Comparable<T>> implements AVLTree<T> {

    private AVLNode<T> raiz;
    private long size;
    private long count;

    public TreeAVL(T value) {
        agregar(value);
    }

    @Override
    public boolean agregar(T value) {
        if (value == null) {
            return false;
        }
        AVLNode<T> node;
        try {
            if ((node = search(value)) != null) {
                node.setCount(node.getCount() + 1);
                size += 1l;
                return true;
            }
        } catch (IsEmptyException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        node = new AVLNode<>(value);
        if (this.size == 0) {
            raiz = node;
            size += 1l;
            findHtBf(raiz);
            return true;
        } else {
            agregar(raiz, node);
            size += 1l;
            return true;
        }
    }

    private boolean agregar(AVLNode<T> raiz, AVLNode<T> node) {
        if (raiz == null) {
            return false;
        }

        T raizData = raiz.getValue();
        T nodeData = node.getValue();

        if (raizData.compareTo(nodeData) < 0) {
            if (raiz.getRight() == null) {
                raiz.setRight(node);
            } else {
                agregar(raiz.getRight(), node);
            }

        } else if (raizData.compareTo(nodeData) > 0) {
            if (raiz.getLeft() == null) {
                raiz.setLeft(node);
            } else {
                agregar(raiz.getLeft(), node);
            }
        }

        findHtBf(raiz);

        balance(raiz);
        return true;
    }

    private void findHtBf(AVLNode<T> node) {
        int lh = (node.getLeft() != null) ? node.getLeft().getHeight() : -1;
        int rh = (node.getRight() != null) ? node.getRight().getHeight() : -1;
        node.setHeight(Math.max(lh, rh) + 1);
        node.setBalanceFactor(lh - rh);
    }

    private void balance(AVLNode<T> node) {

        if (node.getBalanceFactor() > 1) {
            if (node.getLeft() != null) {
                if (node.getLeft().getBalanceFactor() >= 0) {
                    rightRotate(node);
                } else if (node.getLeft().getBalanceFactor() < 0) {
                    leftRightRotate(node);
                }
            }
            
        } else if (node.getBalanceFactor() < -1) {
            if (node.getRight() != null) {
                if (node.getRight().getBalanceFactor() > 0) {
                    rightLeftRotate(node);
                } else if (node.getRight().getBalanceFactor() <= 0) {
                    leftRotate(node);
                }
            }
        }
    }

    private void rightRotate(AVLNode<T> node) {
        AVLNode<T> newNode = new AVLNode<>(node.getValue());
        AVLNode<T> left = node.getLeft();

        newNode.setRight(node.getRight());
        newNode.setLeft(left.getRight());

        node.setRight(newNode);
        node.setValue(left.getValue());
        node.setLeft(left.getLeft());
        left.setLeft(null);

        findHtBfSub(node);

    }

    private void leftRotate(AVLNode<T> node) {
        T origData = node.getValue();
        AVLNode<T> right = node.getRight();
        AVLNode<T> newNode = new AVLNode<>(origData);

        newNode.setLeft(node.getLeft());
        newNode.setRight(right.getLeft());

        node.setLeft(newNode);
        node.setValue(right.getValue());
        node.setRight(right.getRight());
        right.setRight(null);

        findHtBfSub(node);
    }

    private void leftRightRotate(AVLNode<T> node) {
        leftRotate(node.getLeft());
        rightRotate(node);
    }

    private void rightLeftRotate(AVLNode<T> node) {
        rightRotate(node.getRight());
        leftRotate(node);
    }

    private void findHtBfSub(AVLNode<T> raiz) {
        if (raiz.getLeft() != null) {
            findHtBfSub(raiz.getLeft());
        }
        if (raiz.getRight() != null) {
            findHtBfSub(raiz.getRight());
        }
        findHtBf(raiz);
    }

    public long between(T ini, T fin) throws IsEmptyException {
        count = -1l;
        beetwen(raiz, ini, fin);
        return count + 1;
    }

    private void beetwen(AVLNode<T> node, T x, T y) {
        if (node == null) {
            return;
        }
        if (node.getValue().compareTo(x) == 1) {
            beetwen(node.getLeft(), x, y);
        }
        if ((node.getValue().compareTo(x) == 0 || node.getValue().compareTo(x) == 1) && (node.getValue().compareTo(y) == 0 || node.getValue().compareTo(y) == -1)) {
            if (node.getCount() > 0) {
                for (int i = 0; i < node.getCount() + 1; i++) {
                    count += 1l;
                }
            } else {
                count += 1l;
            }
        }
        if (node.getValue().compareTo(y) == -1) {
            beetwen(node.getRight(), x, y);
        }
    }

    @Override
    public T mayor() throws IsEmptyException {
        return mayor(raiz);
    }

    private T mayor(AVLNode<T> node) {
        return node.getRight() == null ? node.getValue() : mayor(node.getRight());
    }

    private int altura = 0;

    @Override
    public long height() throws IsEmptyException {
        heigth(raiz, 1);
        return altura;
    }

    private void heigth(AVLNode<T> reco, int nivel) {
        if (reco != null) {
            heigth(reco.getLeft(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            heigth(reco.getRight(), nivel + 1);
        }
    }

    @Override
    public void inOrder() throws IsEmptyException {
        System.out.println("\nIn-Order: ");
        inOrder(raiz);
        System.out.println();
    }

    private void inOrder(AVLNode<T> raiz) {
        if (raiz != null) {
            inOrder(raiz.getLeft());
            System.out.print(String.format("%s (hijo %d, bFactor %d, repetido %d)",
                    raiz.getValue(), raiz.getHeight(), raiz.getBalanceFactor(), raiz.getCount()) + " - ");
            inOrder(raiz.getRight());
        }
    }

    @Override
    public void posOrder() throws IsEmptyException {
        System.out.println("\nPos-Order: ");
        posOrder(raiz);
        System.out.println();
    }

    private void posOrder(AVLNode<T> raiz) {
        if (raiz != null) {
            inOrder(raiz.getLeft());
            inOrder(raiz.getRight());
            System.out.print(String.format("%s (H %d, BF %d, C %d)",
                    raiz.getValue(), raiz.getHeight(), raiz.getBalanceFactor(), raiz.getCount()) + " - ");
        }
    }

    @Override
    public void preOrder() throws IsEmptyException {
        System.out.println("\nPre-Order: ");
        preOrder(raiz);
        System.out.println();

    }

    private void preOrder(AVLNode<T> raiz) {
        if (raiz != null) {
            System.out.print(String.format("%s (H %d, BF %d, C %d)",
                    raiz.getValue(), raiz.getHeight(), raiz.getBalanceFactor(), raiz.getCount()) + " - ");
            inOrder(raiz.getLeft());
            inOrder(raiz.getRight());
        }
    }
    
    @Override
    public void isEmpty() throws IsEmptyException {
        if (raiz == null) {
            throw new IsEmptyException("Empty tree.");
        }
    }

    @Override
    public T menor() throws IsEmptyException {
        return menor(raiz);
    }

    private T menor(AVLNode<T> node) {
        if (node.getLeft() == null) {
            return node.getValue();
        } else {
            return menor(node.getLeft());
        }
    }


    @Override
    public boolean borrar(T value) throws IsEmptyException {
        if (value == null) {
            return false;
        }

        if (search(value) == null) {
            return false;
        }

        if (size == 1 && raiz.getValue().compareTo(value) == 0) {
            raiz = null;
            size -= 1;
            return true;

        } else {
            raiz = borrar(raiz, value);
        }
        size -= 1;
        return true;
    }

    private AVLNode<T> borrar(AVLNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getValue()) == 0) {
            AVLNode<T> replaceData = successor(node);
            if (replaceData.getCount() == 0) {
                if (node.getCount() > 0) {
                    node.setCount(node.getCount() - 1);
                } else {
                    if (node.getLeft() == null && node.getRight() == null) {
                        return null;
                    } else if (node.getLeft() == null) {
                        return node.getRight();
                    } else if (node.getRight() == null) {
                        return node.getLeft();

                    } else {
                        node.setValue(replaceData.getValue());
                        node.setCount(replaceData.getCount());
                        node.setHeight(replaceData.getHeight());
                        node.setRight(borrar(node.getRight(), replaceData.getValue()));
                    }
                }
            } else {
                replaceData.setCount(replaceData.getCount() - 1);
            }
            
        } else if (data.compareTo(node.getValue()) < 0) {
            node.setLeft(borrar(node.getLeft(), data));
            
        } else {
            node.setRight(borrar(node.getRight(), data));
        }
        
        findHtBf(node);
        balance(node);
        return node;
    }

    private AVLNode<T> successor(AVLNode<T> node) {
        if (node.getRight() != null) {
            node = node.getRight();
        }
        if (node == null) {
            return null;
        }
        AVLNode<T> left = node.getLeft();
        if (left == null) {
            return node;
        } else {
            return successor(left);
        }
    }

    @Override
    public AVLNode<T> search(T value) throws IsEmptyException {
        return search(value, raiz);
    }

    private AVLNode<T> search(T value, AVLNode<T> raiz) {
        if (raiz == null) {
            return null;
        } else {
            if (raiz.getValue().equals(value)) {
                return raiz;
            } else {
                return value.compareTo(raiz.getValue()) < 0 ? search(value, raiz.getLeft()) : search(value, raiz.getRight());
            }
        }
    }

    @Override
    public String toString() {
        return TreePrinter.print(raiz);
    }

    @Override
    public long beetwen(T start, T end) throws IsEmptyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

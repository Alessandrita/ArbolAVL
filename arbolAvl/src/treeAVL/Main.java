package treeAVL;

import Excepciones.IsEmptyException;

public class Main {

    public static void main(String[] args) throws IsEmptyException {
        TreeAVL<Integer> t = new TreeAVL<>(5);
        
        t.agregar(30);
        t.agregar(4);
        t.agregar(19);
        t.agregar(7);
        t.agregar(6);
        t.agregar(9);
        t.agregar(3);
        t.agregar(22);
        t.agregar(5);
        
        t.borrar(3);
        
        t.inOrder();
        t.preOrder();
        t.posOrder();
        
        System.out.println(":Between ");
        System.out.println(t.between(-1, 2_000));
        System.out.println("Mayor: ");
        System.out.println(t.mayor());
        System.out.println("Menor: ");
        System.out.println(t.menor());
        System.out.println(t);
    }
}

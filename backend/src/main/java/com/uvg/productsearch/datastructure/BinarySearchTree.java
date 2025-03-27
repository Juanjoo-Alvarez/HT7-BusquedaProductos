package com.uvg.productsearch.datastructure;

/**
 * Implementación genérica de un Árbol Binario de Búsqueda
 * @param <E> Tipo de elemento que almacenará el árbol
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    /**
     * Clase interna que representa un nodo del árbol
     */
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Constructor para un árbol vacío
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserta un elemento en el árbol
     * @param element Elemento a insertar
     */
    public void insert(E element) {
        root = insertRecursive(root, element);
    }

    /**
     * Método auxiliar recursivo para insertar un elemento
     */
    private Node<E> insertRecursive(Node<E> current, E element) {
        // Si el nodo actual es nulo, hemos encontrado donde insertar
        if (current == null) {
            return new Node<>(element);
        }

        // Comparamos el elemento a insertar con el elemento del nodo actual
        int compareResult = element.compareTo(current.data);

        if (compareResult < 0) {
            // Si es menor, va en el subárbol izquierdo
            current.left = insertRecursive(current.left, element);
        } else if (compareResult > 0) {
            // Si es mayor, va en el subárbol derecho
            current.right = insertRecursive(current.right, element);
        } else {
            // Si es igual, reemplazamos el valor (aunque en un map normalmente actualizaríamos)
            current.data = element;
        }

        return current;
    }

    /**
     * Busca un elemento en el árbol
     * @param element Elemento a buscar
     * @return El elemento encontrado o null si no existe
     */
    public E search(E element) {
        return searchRecursive(root, element);
    }

    /**
     * Método auxiliar recursivo para buscar un elemento
     */
    private E searchRecursive(Node<E> current, E element) {
        // Si llegamos a un nodo nulo, el elemento no está en el árbol
        if (current == null) {
            return null;
        }

        // Comparamos el elemento a buscar con el elemento del nodo actual
        int compareResult = element.compareTo(current.data);

        if (compareResult == 0) {
            // Si son iguales, hemos encontrado el elemento
            return current.data;
        } else if (compareResult < 0) {
            // Si es menor, buscamos en el subárbol izquierdo
            return searchRecursive(current.left, element);
        } else {
            // Si es mayor, buscamos en el subárbol derecho
            return searchRecursive(current.right, element);
        }
    }
}
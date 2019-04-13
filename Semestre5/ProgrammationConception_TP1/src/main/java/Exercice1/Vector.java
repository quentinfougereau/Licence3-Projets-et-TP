package Exercice1;

import java.util.Arrays;

/** * La classe Vector implémente un tableau d'entiers * de taille dynamique. Les éléments du vecteur sont stockés dans un tableau. * La taille de ce tableau est au minimum doublée à chaque fois qu'il est * nécessaire de le faire grossir. */
public class Vector {

    /**
     * Tableau permettant de stocker les éléments du vecteur.
     * Seuls les size premiers éléments font partie du vecteur.
     * La taille de ce tableau est égale à la capacité du vecteur, c'est-à-dire,
     * au nombre d'éléments maximum que le vecteur peut contenir sans
     * avoir besoin d'allouer un nouveau tableau.
     */
    private int[] elements;

    /**
     * Nombre d'éléments présents dans le vecteur.
     */
    private int size;

    /**
     * Construit un vecteur de capacité initiale initialCapacity.
     *
     * @param initialCapacity Capacité initiale du vecteur
     */
    public Vector(int initialCapacity) {
        elements = new int[initialCapacity];
        size = 0;
    }

    /**
     * Construit un vecteur de capacité initiale 10
     */
    public Vector() {
        this(10);
    }

    /**
     * Augmente la capacité du vecteur si nécessaire de façon
     * à permettre le stockage d'au moins <code>minCapacity</code>
     * éléments. S'il est nécessaire d'augmenter la capacité du vecteur,
     * elle est au minimum doublée.
     *
     * @param minCapacity Capacité minimale à assurer
     */
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= minCapacity) return;
        int newCapacity = Math.max(oldCapacity * 2, minCapacity);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * S'assure que la capacité du vecteur est assez grande pour
     * accueillir la nouvelle taille et remplace l'ancienne taille
     * par la nouvelle.
     *
     * @param newSize Nouvelle taille du vecteur
     */
    public void resize(int newSize) {
        ensureCapacity(newSize);
        this.size = newSize;
    }

    /**
     * Retourne la capacité du vecteur.
     *
     * @return Capacité du vecteur.
     */
    public int capacity() {
        return elements.length;
    }

    /**
     * Retourne la taille du vecteur
     *
     * @return Taille du vecteur
     */
    public int size() {
        return size;
    }

    /**
     * Retourne un booléen
     * Vrai si la taille du vecteur vaut 0, Faux sinon
     *
     * @return true ou false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Redimensionne la taille du vecteur
     * Ajoute un nouvel élément au tableau d'éléments du vecteur
     *
     * @param element Elément du vecteur
     */
    public void add(int element) {
        resize(size + 1);
        elements[size - 1] = element;
    }

    /**
     * Modifie un élément du vecteur à la position index
     *
     * @param index Position de l'élément dans le tableau d'éléments du vecteur
     * @param element Nouvel élément du vecteur
     */
    public void set(int index, int element) {
        if (index < size)
            elements[index] = element;
    }

    /**
     * Retourne un élément du vecteur à la position index
     *
     * @param index Position de l'élément dans le tableau d'éléments du vecteur
     * @return Element du vecteur à la position index ou 0 si cet élément n'existe pas
     */
    public int get(int index) {
        if (index < size) {
            return elements[index];
        } else {
            return 0;
        }
    }
}



package fr.licinfo.structure;

import java.util.Arrays;

public class Stack {

    /**
     * Integer array that store elements of the stack.
     */
    private int[] elements;

    private int size;

    /**
     * Create an empty stack.
     */
    public Stack() {
        elements = new int[0];
        size = 0;
    }

    /**
     * Make sure that capacity of the stack allow new element.
     *
     * Pushes an item onto the top of the stack.
     *
     * @param newElement new item to push onto the top of the stack.
     */
    public void push(int newElement) {
        int[] oldElements = elements;
        elements = Arrays.copyOf(oldElements, size+1);
        elements[size] = newElement;
        size++;
    }

    /**
     * Removes the element at the top of the stack and returns it.
     *
     * @return lastElement; the element at the top of the stack
     */
    public int pop() {
        if (isEmpty())
            return -1;
        int lastElement = elements[size - 1];
        int[] oldElements = elements;
        elements = Arrays.copyOf(oldElements, size - 1);
        size--;
        return lastElement;
    }


    /**
     * Returns the element at the top of the stack without removing it from the stack.
     *
     * @return LastElement; the element at the top of the stack
     */
    public int peek() {
        if (isEmpty())
            return -1;
        return elements[size-1];
    }

    /**
     * Tests if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return size; the number of elements in the stack
     */
    public int size() {
        return size;
    }
}

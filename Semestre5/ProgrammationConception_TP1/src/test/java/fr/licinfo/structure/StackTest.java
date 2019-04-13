package fr.licinfo.structure;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StackTest {

    @Test
    public void push() {
        Stack stack = new Stack();
        stack.push(92);
        stack.push(100);
        assertThat(stack.size(), equalTo(2));
    }

    @Test
    public void pop() {
        Stack stack = new Stack();
        stack.push(12);
        stack.push(75);
        stack.push(42);
        assertThat(stack.pop(), equalTo(42));
        assertThat(stack.size(), equalTo(2));
    }

    @Test
    public void peek() {
        Stack stack = new Stack();
        stack.push(443);
        stack.push(444);
        stack.push(445);
        assertThat(stack.peek(), equalTo(445));
        assertThat(stack.size(), equalTo(3));
    }

    @Test
    public void size() {
        Stack stack = new Stack();
        assertThat(stack.size(), equalTo(0));
        stack.push(666);
        stack.push(667);
        assertThat(stack.size(), equalTo(2));
    }

    @Test
    public void isEmpty() {
        Stack stack = new Stack();
        assertThat(stack.isEmpty(), equalTo(true));
        stack.push(666);
        assertThat(stack.isEmpty(), equalTo(false));
    }
}
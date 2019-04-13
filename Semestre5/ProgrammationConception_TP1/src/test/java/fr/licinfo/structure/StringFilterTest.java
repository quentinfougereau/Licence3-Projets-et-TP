package fr.licinfo.structure;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StringFilterTest {

    @Test
    public void upperCaseStringFilter() {
        String input = "toto";
        StringFilter filter = new UpperCaseStringFilter();
        String output = filter.filter(input);
        assertThat(output, equalTo("TOTO"));
    }

    @Test
    public void upperCaseStringFilterArray() {
        String[] strings = new String[3];
        strings[0] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        strings[1] = "Aenean ornare rutrum blandit";
        strings[2] = "Praesent ullamcorper, diam sit amet luctus lacinia";

        String[] expected_strings = new String[3];
        expected_strings[0] = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISCING ELIT";
        expected_strings[1] = "AENEAN ORNARE RUTRUM BLANDIT";
        expected_strings[2] = "PRAESENT ULLAMCORPER, DIAM SIT AMET LUCTUS LACINIA";

        String[] output = StringFilter.filter(strings, new UpperCaseStringFilter());
        assertThat(output, equalTo(expected_strings));

    }

    @Test
    public void lowerCaseStringFilter() {
        String input = "TATA";
        StringFilter filter = new LowerCaseStringFilter();
        String output = filter.filter(input);
        assertThat(output, equalTo("tata"));
    }

    @Test
    public void prefixStringFilter() {
        String input = "FooBarBaz";
        StringFilter filter = new PrefixStringFilter(0);
        String output = filter.filter(input);
        assertThat(output, equalTo(""));
        filter = new PrefixStringFilter(6);
        output = filter.filter(input);
        assertThat(output, equalTo("FooBar"));
        filter = new PrefixStringFilter(12);
        output = filter.filter(input);
        assertThat(output, equalTo("prefix longer than string length"));
    }

    @Test
    public void postFixStringFilter() {
        String input = "Hello World";
        StringFilter filter = new PostfixStringFilter(0);
        String output = filter.filter(input);
        assertThat(output, equalTo("Hello World"));
        filter = new PostfixStringFilter(5);
        output = filter.filter(input);
        assertThat(output, equalTo("World"));

        filter = new PostfixStringFilter(12);
        output = filter.filter(input);
        assertThat(output, equalTo("postfix size greater than string length"));
    }

    @Test
    public void asciiStringFilter() {
        String input = "";
        StringFilter filter = new AsciiStringFilter();
        String output = filter.filter(input);
        assertThat(output, equalTo(""));

        input = "Portez ce vieux whisky au juge blond qui fume";
        output = filter.filter(input);
        assertThat(output,equalTo("Portez ce vieux whisky au juge blond qui fume"));

        input = "éWhyĩ soû serçious ?à";
        output = filter.filter(input);
        assertThat(output, equalTo("Why so serious ?"));
    }

}
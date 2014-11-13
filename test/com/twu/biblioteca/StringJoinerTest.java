package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringJoinerTest {
    private List<String> strings;
    private StringJoiner joiner;

    @Before
    public void setup(){
        strings = new ArrayList<String>();
        joiner = new StringJoiner();
    }

    @Test
    public void shouldListNothingWhenThereAreNoStrings(){
        assertThat(joiner.join(strings), is(""));
    }

    @Test
    public void shouldListAString() {
        String string = "aaa";
        strings.add(string);
        ArrayList<String> al = new ArrayList<String>();
        assertThat(joiner.join(strings), is(string));
    }

    @Test
    public void shouldContainMultipleStringsWhenListHasMultipleElements(){
        strings.add("a");
        strings.add("b");
        assertThat(joiner.join(strings), both(containsString("c")).and(containsString("b")));
    }

    @Test
    public void shouldSeparateStringsWithNewline(){
        strings.add("a");
        strings.add("b");
        assertThat(joiner.join(strings), is("a\nb"));
    }

}

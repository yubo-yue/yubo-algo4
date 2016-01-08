package jvm.lambda;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by yubo on 1/6/16.
 */
public class OptionalTest {

    @Test
    public void testOptional() {
        Optional<String> a = Optional.of("a");
        assertEquals("a", a.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyOptional() {
        Optional alsoEmpty = Optional.ofNullable(null);
        assertFalse(alsoEmpty.isPresent());
        Optional empty = Optional.empty();
        assertFalse(empty.isPresent());
        empty.get();
    }
}

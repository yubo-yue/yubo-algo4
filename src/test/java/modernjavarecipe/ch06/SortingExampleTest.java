package modernjavarecipe.ch06;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SortingExampleTest {

    private static final List<String> INPUT = Arrays.asList("this", "is", "a", "list", "of", "strings");

    @Test
    public void test() {
        final List<String> lengthSortedStrings = SortingExample.lengthSortUsingSorted(INPUT);
    }
}

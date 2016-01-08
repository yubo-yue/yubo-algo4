package experiments.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yubo on 12/30/15.
 */
public class OptionalTest {

    @Test
    public void testOptional() {

        List<Integer> li =
                Arrays.asList(1, 2, 3, 4, 6);

        List<Integer> li1 = li.stream().filter(i -> i > 6).collect(Collectors.toList());
        Optional<List<Integer>> oi = Optional.ofNullable(li1);

        if (oi.isPresent())
            System.out.println(oi.get());
    }
}

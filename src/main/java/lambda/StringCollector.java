package lambda;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by yubo on 12/22/15.
 */
public class StringCollector implements Collector<String, StringCombiner, String> {
    @Override
    public Supplier<StringCombiner> supplier() {
        return null;
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return null;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}

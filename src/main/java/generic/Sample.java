package generic;

import java.util.List;

/**
 * Created by yubo on 12/11/15.
 */
public class Sample {

    public static <T extends Comparable<T>> void copy(List<T> source, List<T> target) {
        if (source.get(0).compareTo(target.get(0)) != 0) {
            //...
        }

    }

    public static void main(String[] args) {

    }
}

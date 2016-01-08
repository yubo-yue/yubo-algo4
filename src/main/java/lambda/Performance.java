package lambda;

import java.util.stream.Stream;

import static java.util.stream.Stream.*;

/**
 * Created by yubo on 12/21/15.
 */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

    // TODO: test
    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> {
            return concat(Stream.of(artist), artist.getMembers());
        });
    }

}

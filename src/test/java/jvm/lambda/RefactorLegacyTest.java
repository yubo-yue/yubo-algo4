package jvm.lambda;

import static org.junit.Assert.*;

import lambda.Artist;
import lambda.SampleData;
import lambda.StringCombiner;
import org.junit.Test;

import java.util.List;

/**
 * Created by yubo on 12/22/15.
 */
public class RefactorLegacyTest {

    @Test
    public void testLegacyWay() {
        List<Artist> threeArtist = SampleData.getThreeArtists();
        StringBuilder builder = new StringBuilder("[");
        for (Artist a : threeArtist) {
            if (builder.length() > 1)
                builder.append(",");

            String name = a.getName();
            builder.append(name);
        }

        builder.append("]");
        String result = builder.toString();
        assertEquals("[John Coltrane,John Lennon,The Beatles]", result);
        System.out.println(result);

    }

    @Test
    public void testLambda() {
        List<Artist> threeArtist = SampleData.getThreeArtists();

        String result = threeArtist.stream()
                .map(Artist::getName)
                .reduce(
                        new StringCombiner(",", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge
                ).toString();

        assertEquals("[John Coltrane,John Lennon,The Beatles]", result);
        System.out.println(result);

    }
}

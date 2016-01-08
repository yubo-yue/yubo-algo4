package jvm.lambda;

import lambda.Album;
import lambda.Artist;
import lambda.SampleData;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * Created by yubo on 12/21/15.
 */
public class StreamTest3 {

    public static Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return artists.collect(maxBy(comparing(getCount)));
    }

    public static double averageNumberOfTracks(List<Album> albums) {
        return albums.stream().collect(averagingInt(album -> album.getTrackList().size()));
    }

    public static Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(artist -> artist.isSolo()));
    }

    public static Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician()));
    }

    @Test
    public void testMax() {
        Optional<Artist> r = StreamTest3.biggestGroup(SampleData.threeArtists());
        assertTrue(r.isPresent());
        System.out.println(r.get());

        List<Artist> threeArtists = SampleData.getThreeArtists();
        String result = threeArtists.stream().map(Artist::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);

    }

    @Test
    public void testAverage() {
        double r = StreamTest3.averageNumberOfTracks(Arrays.asList(SampleData.aLoveSupreme, SampleData.manyTrackAlbum));
        assertTrue(r > 0.0f);
        System.out.println(r);
    }
}

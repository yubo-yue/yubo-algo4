package modernjavarecipe.ch08;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Recipe87 {

    @Test
    public void test1() {
        final ZonedDateTime zdt = ZonedDateTime.now();
        final ZoneId zoneId = zdt.getZone();
        final List<String> names = getRegionNamesForZoneId(zoneId);
        names.stream().forEach(System.out::println);
        assertThat(names, is(hasItem(zoneId.getId())));
    }

    @Test
    public void getRegionNamesForGMT() {
        final List<String> names = getRegionNamesForOffset(0, 0);

        assertThat(names, hasItem("GMT"));
        assertThat(names, hasItem("Etc/GMT"));
        assertThat(names, hasItem("Etc/UTC"));
        assertThat(names, hasItem("UTC"));
        assertThat(names, hasItem("Etc/Zulu"));
    }

    @Test
    public void getRegionNameForNepal() {
        final List<String> names = getRegionNamesForOffset(5, 45);

        assertThat(names, hasItem("Asia/Kathmandu"));
        assertThat(names, hasItem("Asia/Katmandu"));

    }

    @Test
    public void getRegionNamesForChicago() {
        final ZoneId chichage = ZoneId.of("America/Chicago");

        final List<String> names = getRegionNamesForZoneId(chichage);

        assertThat(names, hasItem("America/Chicago"));
        assertThat(names, hasItem("US/Central"));
        assertThat(names, hasItem("Canada/Central"));
        assertThat(names, anyOf(hasItem("Etc/GMT+5"), hasItem("Etc/GMT+6")));
    }

    public static List<String> getRegionNamesForOffset(final ZoneOffset offset) {
        final LocalDateTime now = LocalDateTime.now();

        return ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter(zoneId -> now.atZone(zoneId).getOffset().equals(offset))
                .map(ZoneId::toString)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<String> getRegionNamesForZoneId(final ZoneId zoneId) {
        final LocalDateTime now = LocalDateTime.now();

        final ZonedDateTime zdt = now.atZone(zoneId);

        final ZoneOffset offset = zdt.getOffset();

        return getRegionNamesForOffset(offset);
    }

    public static List<String> getRegionNamesForOffset(int hours, int minutes) {
        final ZoneOffset offset = ZoneOffset.ofHoursMinutes(hours, minutes);
        return getRegionNamesForOffset(offset);
    }
}

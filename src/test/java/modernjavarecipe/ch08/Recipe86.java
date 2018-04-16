package modernjavarecipe.ch08;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;

public class Recipe86 {
    public static void main(String[] args) {
        final Instant instant = Instant.now();

        final ZonedDateTime current = instant.atZone(ZoneId.systemDefault());
        System.out.printf("instant: %s, current time is %s%n%n", instant, current);

        ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter(zoneId -> {
                    ZoneOffset offset = instant.atZone(zoneId).getOffset();
                    return offset.getTotalSeconds() % (60 * 60) != 0;
                })
                .sorted(Comparator.comparingInt(zoneId -> instant.atZone(zoneId).getOffset().getTotalSeconds()))
                .forEach(zoneId -> {
                    ZonedDateTime zdt = current.withZoneSameInstant(zoneId);
                    System.out.printf("%10s %25s %10s%n", zdt.getOffset(), zoneId, zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
                });

    }
}

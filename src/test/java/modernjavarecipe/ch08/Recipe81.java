package modernjavarecipe.ch08;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class Recipe81 {
    public static void main(String[] args) {
        System.out.println("Instant.now() " + Instant.now());
        System.out.println("LocalDate.now " + LocalDate.now());
        System.out.println("LocalTime.now() " + LocalTime.now());

        System.out.println("LocalDateTime.now() " + LocalDateTime.now());
        System.out.println("ZonedDateTime.now() " + ZonedDateTime.now());

        System.out.println("First landing on the Moon: ");
        LocalDate moonLandingDate = LocalDate.of(1969, Month.JULY, 20);
        LocalTime moonLandingTime = LocalTime.of(20, 16);
        System.out.println("Date : " + moonLandingDate);
        System.out.println("Time : " + moonLandingTime);


        LocalTime walkingTime = LocalTime.of(20, 2, 56, 15_000_000);
        LocalDateTime walkingDateTime = LocalDateTime.of(moonLandingDate, walkingTime);
        System.out.println("Walking data time: " + walkingDateTime);

        System.out.println("Adding Time Zone");
        LocalDateTime dateTime = LocalDateTime.of(2017, Month.JULY, 4, 13, 20, 10);
        System.out.println("Local date time: " + dateTime);
        ZonedDateTime nyc = dateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println("New york city: " + nyc);
        ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println("London : " + london);

        //Month
        System.out.println("Days in Feb in a leap year" + Month.FEBRUARY.length(true));
        System.out.println("Day of year of first day of Aug (leap year)" + Month.AUGUST.firstDayOfYear(true));
        System.out.println("Month.of(1) " + Month.of(1));
        System.out.println("Adding two months: " + Month.JANUARY.plus(2));
        System.out.println("Subtracting 1 month: " + Month.MARCH.minus(1));
    }
}

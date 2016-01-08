package jvm;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by yubo on 12/20/15.
 */
public class Java8DateTimeTest {

    @Test
    public void instantTest() throws InterruptedException {
        Instant start = Instant.now();
        TimeUnit.SECONDS.sleep(1);
        Instant end = Instant.now();

        System.out.println(Duration.between(start, end));
        assertTrue(Duration.between(start, end).toMillis() > 0);
    }

    /**
     * Local Date/Time has no TimeZone information.
     */
    @Test
    public void localDateTest() {
        LocalDate today = LocalDate.now();
        LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
        LocalDate alonzosBirthday1 = LocalDate.of(1903, Month.JUNE, 14);
        assertEquals(alonzosBirthday, alonzosBirthday1);

        LocalDate programmerDay = LocalDate.of(2014, 1, 1).plusDays(255);
        System.out.println(programmerDay);
    }

    @Test
    public void localDateMoreTest() {
        LocalDate birthDay = LocalDate.of(1982, Month.NOVEMBER, 23);
        LocalDate nextBirthDay = birthDay.plusYears(1);
        assertEquals(365L, birthDay.until(nextBirthDay, ChronoUnit.DAYS));

        LocalDate lastMonthDay = LocalDate.of(2016, 1, 31).plusMonths(1);
        assertEquals(LocalDate.of(2016, 2, 29), lastMonthDay);

        assertEquals(1, LocalDate.of(1900, 1, 1).getDayOfWeek().getValue());

        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.SATURDAY.plus(3));
    }

    @Test
    public void temporalAdjusterTest() {
        LocalDate firstTuesday = LocalDate.of(2014, 11, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        assertEquals(LocalDate.of(2014, 11, 4), firstTuesday);
    }


}

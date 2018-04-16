package modernjavarecipe.ch08;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Time between Events: You need to know the amount of time between two events.
 * <p>
 * Options:
 * ChronoUnit.Days.between(a, b);
 * <p>
 * Period: If you are interesting in a breakdown into years, months, and days, then use Period. (Human readable times, like days, months, days)
 * <p>
 * Duration: The Duration class represents an amount of time in terms of seconds and nanoseconds, which make it suitable for working with Instant.
 * <p>
 * </p>
 */
public class Recipe88 {

    @Test
    public void test1() {
        final LocalDate electionDay = LocalDate.of(2020, Month.NOVEMBER, 3);
        final LocalDate today = LocalDate.of(2018, Month.APRIL, 16);

        assertThat(932L, is(ChronoUnit.DAYS.between(today, electionDay)));
    }

    @Test
    public void testByPeriod() {
        final LocalDate electionDay = LocalDate.of(2020, Month.NOVEMBER, 3);
        final LocalDate today = LocalDate.of(2018, Month.APRIL, 16);

        Period until = today.until(electionDay);

        assertThat(until.getYears(), is(2));
        assertThat(until.getMonths(), is(6));
        assertThat(until.getDays(), is(18));
    }

    @Test
    public void testByDuration() {
        Instant start = Instant.now();
        Instant end = Instant.now();

        assertThat(getTiming(start, end), is(0.0));
    }

    public static double getTiming(final Instant start, final Instant end) {
        return Duration.between(start, end).toMillis() / 1000.0;
    }
}

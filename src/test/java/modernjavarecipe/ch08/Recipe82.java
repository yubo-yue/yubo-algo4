package modernjavarecipe.ch08;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import static java.time.Month.FEBRUARY;
import static java.time.format.DateTimeFormatter.*;
import static java.time.temporal.ChronoUnit.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Creating dates and times from existing Instances.
 */
public class Recipe82 {

    @Test
    public void localDatePlus() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate start = LocalDate.of(2017, FEBRUARY, 2);

        assertThat("2017-02-05", equalTo(start.plusDays(3).format(formatter)));
        assertThat("2017-03-09", equalTo(start.plusWeeks(5).format(formatter)));
        assertThat("2017-09-02", equalTo(start.plusMonths(7).format(formatter)));
        assertThat("2019-02-02", equalTo(start.plusYears(2).format(formatter)));
    }

    @Test
    public void localTimePlus() {
        final DateTimeFormatter formatter = ISO_LOCAL_TIME;

        final LocalTime start = LocalTime.of(11, 30, 0, 0);
        assertThat("11:30:00.001", equalTo(start.plusNanos(1_000_000).format(formatter)));
        assertThat("11:30:20", equalTo(start.plusSeconds(20).format(formatter)));
        assertThat("11:35:00", equalTo(start.plusMinutes(5).format(formatter)));
        assertThat("14:30:00", equalTo(start.plusHours(3).format(formatter)));
    }

    @Test
    public void plus_minus() {
        final Period twoYears3Months4Days = Period.of(2, 3, 4);
        final LocalDateTime start = LocalDateTime.of(2017, FEBRUARY, 2, 11, 30);

        assertThat("2019-05-06T11:30:00", equalTo(start.plus(twoYears3Months4Days).format(ISO_LOCAL_DATE_TIME)));
        assertThat("2017-02-03T23:30:00", equalTo(start.plus(3, HALF_DAYS).format(ISO_LOCAL_DATE_TIME)));
        assertThat("2014-10-29T11:30:00", equalTo(start.minus(twoYears3Months4Days).format(ISO_LOCAL_DATE_TIME)));
        assertThat("1817-02-02T11:30:00", equalTo(start.minus(2, CENTURIES).format(ISO_LOCAL_DATE_TIME)));
        assertThat("5017-02-02T11:30:00", equalTo(start.plus(3, MILLENNIA).format(ISO_LOCAL_DATE_TIME)));
    }

    @Test
    public void with() {
        final LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);

        assertThat("2017-02-02T11:45:00", equalTo(start.withMinute(45).format(ISO_LOCAL_DATE_TIME)));
        assertThat("2017-02-02T16:30:00", equalTo(start.withHour(16).format(ISO_LOCAL_DATE_TIME)));
        assertThat("2017-02-28T11:30:00", equalTo(start.withDayOfMonth(28).format(ISO_LOCAL_DATE_TIME)));
        assertThat("2017-10-27T11:30:00", equalTo(start.withDayOfYear(300).format(ISO_LOCAL_DATE_TIME)));
        assertThat("2020-02-02T11:30:00", equalTo(start.withYear(2020).format(ISO_LOCAL_DATE_TIME)));
    }

    @Test
    public void temporalField() {
        final LocalDateTime start = LocalDateTime.of(2017, Month.JANUARY, 31, 11, 30);
        final LocalDateTime end = start.with(ChronoField.MONTH_OF_YEAR, 2);

        assertThat("2017-02-28T11:30:00", equalTo(end.format(ISO_LOCAL_DATE_TIME)));
    }
}

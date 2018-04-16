package modernjavarecipe.ch08;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Given a temporal value, you want to adjust it to a new one based on your own logic,
 * or you want to retrieve information about it.
 */
public class Recipe83 {

    @Test
    public void adjuster() {
        final LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);

        assertThat("2017-03-01T11:30:00", equalTo(
                start.with(TemporalAdjusters.firstDayOfNextMonth())
                        .format(ISO_LOCAL_DATE_TIME))
        );

        assertThat("2017-02-09T11:30:00", equalTo(
                start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY))
                        .format(ISO_LOCAL_DATE_TIME)
        ));


        assertThat("2017-02-02T11:30:00", equalTo(
                start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
                        .format(ISO_LOCAL_DATE_TIME)
        ));
    }

    @Test
    public void queries() {
        assertThat(LocalDate.now().query(TemporalQueries.precision()), is(ChronoUnit.DAYS));
        assertThat(LocalTime.now().query(TemporalQueries.precision()), is(ChronoUnit.NANOS));
        assertThat(ZonedDateTime.now().query(TemporalQueries.zone()), is(ZoneId.systemDefault()));
        assertThat(ZonedDateTime.now().query(TemporalQueries.zoneId()), is(ZoneId.systemDefault()));
    }
}

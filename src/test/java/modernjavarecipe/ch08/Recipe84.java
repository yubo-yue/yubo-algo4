package modernjavarecipe.ch08;

import lombok.NonNull;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Convert from java.util.Date or java.util.Calendar to java.time.*;
 */
public class Recipe84 {

    private Date dateUnderTest;
    private java.sql.Date sqlDateUnderTest;
    private DateTimeFormatter defaultDateTimeFormatter;

    @Before
    public void setUp() {
        try {
            dateUnderTest = new SimpleDateFormat("yyyy-MM-dd").parse("2017-02-18");
            defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fromUtilDateToLocalDate() {
        final LocalDate localDate = fromDate(dateUnderTest);
        assertThat(localDate.format(defaultDateTimeFormatter), equalTo("2017-02-18"));
    }


    public static LocalDate fromDate(@NonNull final Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate fromSqlDate(@NonNull final java.sql.Date date) {
        return date.toLocalDate();
    }

    public static java.sql.Date toSqlDate(@NonNull final LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public static LocalDateTime fromSqlTimeStamp(@NonNull final java.sql.Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static java.sql.Timestamp toSqlTimeStamp(@NonNull final LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    public static LocalDate fromUtilDate(@NonNull final Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public static ZonedDateTime fromUtilCalendar(@NonNull final Calendar calendar) {
        return ZonedDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }

    public static LocalDateTime fromUtilCalendarUsingGetters(@NonNull final Calendar calendar) {
        return LocalDateTime.of(
                calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)
                , calendar.get(Calendar.HOUR)
                , calendar.get(Calendar.MINUTE)
                , calendar.get(Calendar.SECOND)
        );
    }

    public static LocalDateTime fromUtilDateUsingString(@NonNull final Date date) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(df.format(date), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static ZonedDateTime fromGregorianCalendar(@NonNull final Calendar calendar) {
        return ((GregorianCalendar) calendar).toZonedDateTime();
    }

}

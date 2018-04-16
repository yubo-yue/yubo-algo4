package modernjavarecipe.ch08;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Recipe85 {

    @Test
    public void test1() {
        final LocalDateTime now = LocalDateTime.now();
        final String text = now.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(text);
        final LocalDateTime back = LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);

        assertThat(back, equalTo(now));
    }

    @Test
    public void test2() {
        final LocalDate localDate = LocalDate.of(2017, Month.MARCH, 13);

        System.out.println("Full : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.println("Long : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println("Medium : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.println("Short : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println("France : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.FRANCE)));
        System.out.println("India : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("hin", "IN"))));
        System.out.println("Brazil : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("pt", "BR"))));
        System.out.println("Japan : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.JAPAN)));
        System.out.println("PRC : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.PRC)));

        final Locale loc = new Locale.Builder().setLanguage("sr").setScript("Latn").setRegion("RS").build();
        System.out.println("Serbian : " + localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(loc)));
    }
}

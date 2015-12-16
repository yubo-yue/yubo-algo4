package lambda;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yubo on 12/16/15.
 */
public class LambdaStream {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("README.md")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]]+"));
        Stream<String> swords = Stream.of(contents.split("[\\P{L}]]+"));
        Stream<String> echos = Stream.generate(() -> "Echo");
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));


        //Logger.getGlobal().log(Level.INFO, "Hello Java SE 8");
        Logger.getGlobal().finest("Hello Java SE 8");
        //iteration way
        int count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }

        System.out.println("count = [" + count + "]");

        //lambda stream way
        long count1 = words.stream().filter(w -> w.length() > 12).count();
        System.out.println("count1 = [" + count1 + "]");

        Stream<Stream<Character>> result = swords.map(w -> characterStream(w));
        result.forEach(x -> Logger.getGlobal().log(Level.ALL, x.toString()));


        //concate stream
        Stream<String> words2 = Stream.of(contents.split("[\\P{L}]+")).limit(3);
        words2.collect(Collectors.toList()).forEach(System.out::println);


        Stream<String> words3 = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
        Optional<String> largest = words3.max(String::compareToIgnoreCase);
        if (largest.isPresent()) {
            System.out.println("largest: " + largest.get());
        }

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(
                Collectors.toMap(
                        l -> l.getDisplayName(),
                        l -> l.getDisplayLanguage(),
                        (existingValue, newValue) -> existingValue
                )
        );
//        Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));
//
//        List<Locale> swissLocales = countryToLocales.get("CH");
//        swissLocales.forEach(System.out::println);

        languageNames.forEach((x, y) -> System.out.println(x + ": " + y));
    }


    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray())
            result.add(c);
        return result.stream();
    }


}

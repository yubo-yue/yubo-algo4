package modernjavarecipe.ch06;

import modernjavarecipe.Actor;
import modernjavarecipe.Movie;
import yubo.algo.common.StdOut;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Recipe42 {
    public static void main(String[] args) {
        final List<String> superHeros = Stream
                .of("Mr.Furious", "The Blue Raja", "The Shoveler"
                        , "The Bowler", "Invisible Boy", "The Spleen", "The Sphnix")
                .collect(toList());


        final Set<String> villians = Stream
                .of("Casanova Frankenstein")
                .collect(toSet());

        final List<String> actors = Stream.of("Hank Azaria")
                .collect(Collectors.toCollection(LinkedList::new));

        final String[] wannabes = Stream.of("The Waffler", "Reverse Psychologis", "PMS Avenger").toArray(String[]::new);

        StdOut.print(superHeros);
        StdOut.println();
        StdOut.print(villians);
        StdOut.println();
        StdOut.print(actors);
        StdOut.println();
        StdOut.print(wannabes);

        Movie mysteryMen = new Movie();
        mysteryMen.addActor(new Actor("Wes Studi", "The Sphinx"));
        mysteryMen.addActor(new Actor("Ben Stiller", "Mr. Furious"));
        mysteryMen.addActor(new Actor("Hank Azaria", "The Blue Raja"));
        mysteryMen.addActor(new Actor("William H. Macy", "The Shoveler"));
        mysteryMen.addActor(new Actor("Janeane Garofalo", "The Bowler"));
        mysteryMen.addActor(new Actor("Kel Mitchell", "Invisible Boy"));
        mysteryMen.addActor(new Actor("Paul Reubens", "The Spleen"));
        mysteryMen.addActor(new Actor("Geoffrey Rush", "Casanova Frankenstein"));
        mysteryMen.addActor(new Actor("Greg Kinnear", "Captain Amazing"));

        final Map<String, String> actorMap = mysteryMen.getActors().stream().collect(toMap(a -> a.getName(), Actor::getRole));

        actorMap.forEach((key, value) -> System.out.printf("%s played %s%n", key, value));
    }
}


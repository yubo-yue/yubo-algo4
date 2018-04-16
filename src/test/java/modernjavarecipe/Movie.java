package modernjavarecipe;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Movie {
    private Set<Actor> actors = new HashSet<>();


    public void addActor(final Actor actor) {
        this.actors.add(actor);
    }
}

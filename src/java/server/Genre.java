package server;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;


public enum Genre { // schema and examples from IMDB
    // Use int short names decrease space and reduce user input typos on string
    Unknown(0),
    Action(1),
    Adventure(2),
    Animation(3),
    Biography(4),
    Comedy(5),
    Crime(6),
    Documentary(7),
    Drama(8),
    Family(9),
    Fantasy(10),
    FilmNoir(11),
    History(12),
    Horror(13),
    Music(14),
    Musical(15),
    Mystery(16),
    Romance(17),
    SciFi(18),
    Sport(19),
    Thriller(20),
    War(21),
    Western(22);

    private final Integer shortName; // this could be a short string if desired

    Genre(int shortName) {
        this.shortName=shortName;
    }

    private int getShortName() {
        return this.shortName;
    }

    @JsonCreator
    public static Genre create(int value) {
        for(Genre v : values()) {
            if(value == v.getShortName()) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    @JsonValue // so Jackson writes out shortname
    public int toValue() {
        return shortName;
    }

}

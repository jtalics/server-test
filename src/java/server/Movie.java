package server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import db.CompositeKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "movies")

@NamedQueries({
        @NamedQuery(name = "server.Movie.getAll", query = "select m from Movie m"),
        @NamedQuery(name = "server.Movie.getByUserId", query = "select m from Movie m " + "where m.userId = :userId "),
        @NamedQuery(name = "server.Movie.getByName", query = "select m from Movie m " + "where lower(m.compositeId.name) like :name "),
        @NamedQuery(name = "server.Movie.getByYearReleased", query = "select m from Movie m " + "where m.compositeId.yearReleased = :yearReleased "),
        @NamedQuery(name = "server.Movie.getByGenre", query = "select m from Movie m " + "where m.genre like :genre "),
        @NamedQuery(name = "server.Movie.getByRating", query = "select m from Movie m " + "where m.rating like :rating ")
})

public class Movie implements Serializable {

    private long userId; // not primary key and not necessarily unique. this is for the user only

    @EmbeddedId
    private CompositeKey compositeId;

    @Enumerated(EnumType.ORDINAL)
    private Genre genre;
    private Integer rating = null;

    public Movie() {
    } // for Hibernate and Jackson

    public Movie(long userId, String name, int yearReleased, Genre genre) {
        this.userId = userId;
        this.compositeId = new CompositeKey();
        this.compositeId.setName(name);
        this.compositeId.setYearReleased(yearReleased);
        this.genre = genre;
        this.rating = null;
    }

    public Movie(long userId, String name, int yearReleased, Genre genre, int rating) {
        this(userId, name, yearReleased, genre);

        if (rating >= 0 && rating <= 100) {
            this.rating = rating;
        }
    }

    @JsonProperty
    public long getUserId() {
        return userId;
    }

    @JsonProperty
    public String getName() {
        return compositeId.getName();
    }

    @JsonProperty
    public int getYearReleased() {
        return this.compositeId.getYearReleased();
    }

    @JsonProperty
    public Genre getGenre() {
        return genre;
    }

    @JsonProperty
    public Integer getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return userId == movie.userId &&
                Objects.equal(compositeId, movie.compositeId) &&
                genre == movie.genre &&
                Objects.equal(rating, movie.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, compositeId, genre, rating);
    }

    public void set(Optional<String> name, Optional<Integer> yearReleased, Optional<Integer> genre, Optional<Integer> rating) {
        if (name.isPresent()) {
            this.compositeId.setName(name.get());
        }
        if (yearReleased.isPresent()) {
            this.compositeId.setYearReleased(yearReleased.get());
        }
        if (genre.isPresent()) {
            this.genre=Genre.create(genre.get());
        }
        if (rating.isPresent()) {
            this.rating=rating.get();
        }

    }
}

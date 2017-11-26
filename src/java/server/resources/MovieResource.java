package server.resources;

import com.codahale.metrics.annotation.Timed;

import com.google.common.base.Optional;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import server.Genre;
import server.Movie;
import server.db.MovieDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    private final AtomicLong counter = new AtomicLong();

    private MovieDAO movieDAO;

    public MovieResource(MovieDAO movieDAO) {

        this.movieDAO = movieDAO;
    }

    Random r = new Random(0);

    @GET // since testing with only browser, use GETs not POSTs
    @Path("/create")
    @Timed
    @UnitOfWork
    public Movie createMovie(@QueryParam("n") String name, @QueryParam("g") int genre, @QueryParam("y") int yearReleased, @QueryParam("r") Integer rating) {

        Movie movie = new Movie(counter.incrementAndGet(),
                name,
                yearReleased,
                Genre.values()[genre],
                rating);
        movieDAO.persist(movie);
        return movie;
    }

    @GET
    @Path("/update")
    @Timed
    @UnitOfWork
    public List<Movie> updateMovie(@QueryParam("id") Long userId,
                             @QueryParam("n") Optional<String> name,
                             @QueryParam("y") Optional<Integer> yearReleased,
                             @QueryParam("g") Optional<Integer> genre,
                             @QueryParam("r") Optional<Integer> rating) {

        List<Movie> movies= getList(Optional.of(userId),
                Optional.absent(),
                Optional.absent(),
                Optional.absent(),
                Optional.absent());
        for (Movie movie:movies) {
            movie.set(name,yearReleased,genre,rating);
            movieDAO.persist(movie);
        }

        return movies;
    }

    @GET
    @Path("/delete")
    @Timed
    @UnitOfWork
    public void delete(@QueryParam("id") Optional<Long> userId,
                       @QueryParam("n") Optional<String> name,
                       @QueryParam("y") Optional<Integer> yearReleased,
                       @QueryParam("g") Optional<Integer> genre,
                       @QueryParam("r") Optional<Integer> rating) {

        for (Movie movie:getList(userId,name,yearReleased,genre,rating) ){movieDAO.delete(movie);}
    }

    @GET
    @Path("/delete/{id}")
    @Timed
    @UnitOfWork
    public void deleteById(@PathParam("id") LongParam userId) {
        for (Movie movie:getList(
                Optional.of(userId.get()),
                Optional.absent(),
                Optional.absent(),
                Optional.absent(),
                Optional.absent()) ){
            movieDAO.delete(movie);
        }
    }

    @GET
    @Path("/list")
    @Timed
    @UnitOfWork
    public List<Movie> getList(@QueryParam("id") Optional<Long> userId,
                               @QueryParam("n") Optional<String> name,
                               @QueryParam("y") Optional<Integer> yearReleased,
                               @QueryParam("g") Optional<Integer> genre,
                               @QueryParam("r") Optional<Integer> rating
    ) {

        Set<Movie> set = new HashSet<>();
        boolean present = false;
        if (userId.isPresent()) {
            present = true;
            set.addAll(movieDAO.getByUserId(userId.get()));
        }
        if (name.isPresent()) {
            present = true;
            set.addAll(movieDAO.getByName(name.get()));
        }
        if (yearReleased.isPresent()) {
            present = true;
            set.addAll(movieDAO.getByYearReleased(yearReleased.get()));
        }
        if (genre.isPresent()) {
            present = true;
            set.addAll(movieDAO.getByGenre(Genre.create(genre.get())));
        }
        if (rating.isPresent()) {
            present = true;
            set.addAll(movieDAO.getByRating(rating.get()));
        }

        if (!present) {
            return movieDAO.getList();
        }
        return new ArrayList<Movie>(set);
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public List<Movie> getById(@PathParam("id") LongParam id) {

        return movieDAO.getByUserId(id.get().longValue());
    }

    @GET
    @Path("/initdb")
    @Timed
    @UnitOfWork
    public List<Movie> initDB() {
        counter.set(100);
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Apocalypse Now", 1979, Genre.War,  85));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Action Jackson", 1988, Genre.Action, 52));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "The Poseidon Adventure", 1972, Genre.Adventure, 71));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "The Incredibles", 2004, Genre.Animation, 80));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Loving Vincent", 2017, Genre.Biography, 79));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Animal House", 1978, Genre.Comedy,  76));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Goodfellas", 1990, Genre.Crime, 87));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "An Inconvenient Truth", 2006, Genre.Documentary, 75));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "As Good As It Gets",  1997, Genre.Drama, 77));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Harry Potter and the Sorcerer's Stone",  2002, Genre.Family, 75));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "The Lord of the Rings: The Fellowship of the Ring",  2001, Genre.Fantasy, 88));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Film Noir",  2007, Genre.FilmNoir, 65));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Dunkirk",  2017, Genre.History, 83));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Saw",  2004, Genre.Horror, 77));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "This is Spinal Tap",  1984, Genre.Music, 80));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Grease", 1978, Genre.Musical, 72));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Murder on the Orient Express", 1974, Genre.Mystery,  73));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Before Sunrise", 1995,Genre.Romance,  81));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Contact", 1997, Genre.SciFi,  74));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Any Given Sunday", 1999, Genre.Sport,  73));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Identity", 2003, Genre.Thriller,  73));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Hang 'Em High", 1968, Genre.Western,  70));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "2001: A Space Odyssey", 1968, Genre.SciFi,  83));
        movieDAO.persist(new Movie(counter.incrementAndGet(), "Unrated Movie", 2018, Genre.Unknown)); // no rating yet
        return (movieDAO.getList());
    }
}

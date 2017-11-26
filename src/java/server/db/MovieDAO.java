package server.db;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import server.Genre;
import server.Movie;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Collection;
import java.util.List;

public class MovieDAO extends AbstractDAO<Movie> {

    public MovieDAO(SessionFactory sessionFactory) {

        super(sessionFactory);

    }

    public List<Movie> getList() {

        return list(namedQuery("server.Movie.getAll"));
    }

    public List<Movie> getByName(String name) {

        StringBuilder builder = new StringBuilder("%");
        builder.append(name.toLowerCase()).append("%"); // make case insensitive search
        return list(namedQuery("server.Movie.getByName").setParameter("name", builder.toString()));
    }

    public List<Movie> getByYearReleased(int yearReleased) {
        return list(namedQuery("server.Movie.getByYearReleased").setParameter("yearReleased", yearReleased/*builder.toString()*/));
    }

    public List<Movie> getByGenre(Genre genre) {
        return list(namedQuery("server.Movie.getByGenre").setParameter("genre", genre));
    }

    public List<Movie> getByRating(int rating) {
        return list(namedQuery("server.Movie.getByRating").setParameter("rating", rating));
    }

    public List<Movie> getByUserId(long userId) {
        return list(namedQuery("server.Movie.getByUserId").setParameter("userId", userId));
    }

    public void delete(Movie movie) {

        currentSession().delete(movie);
    }

    public Movie persist(Movie movie) {

        return super.persist(movie);
    }

}

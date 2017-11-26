<#-- @ftlvariable name="" type="server.resources.TestView" -->
<html>
    <body>
        <h1>Quick CRUD testing URLs.</h2>
        We might use curl to automate this, of course.

    <h3>CREATE</h3>
    <h4>Initialize the H2 database</h4>
    </font>
    Hibernate drops any tables and creates them in the H2 database each run.
    You might want to load up the database with some 20+ movies.
    Returns all movies so you can see what happened.
    <p>
    <a href="http://localhost:8080/api/movie/initdb" target="_blank">
    http://localhost:8080/api/movie/initdb
    </a>

    <h4>Create a movie</h4>

    Create a new action movie named MyMovie (1988) rated at 5.5 out of ten.

    <a href="http://localhost:8080/api/movie/create?n=MyMovie&g=1&y=1998&rb=4&r=55" target="_blank">
    http://localhost:8080/api/movie/create?n=MyMovie&g=1&y=1998&rb=4&r=55
    </a>

    <h3>RETURN</h3>

    <h4>Return all movies</h4>

    <a href="http://localhost:8080/api/movie/list" target="_blank">
    http://localhost:8080/api/movie/list
    </a>

    <h4>Return a movie by userId</h4>

    Get the movie with userId =101.

    <a href="http://localhost:8080/api/movie/101" target="_blank">
    http://localhost:8080/api/movie/101
    </a>

    <h4>Return a movie by name</h4>

    Get the movie named "Saw".

    <a href="http://localhost:8080/api/movie/list?n=Saw" target="_blank">
    http://localhost:8080/api/movie/list?n=Saw
    </a>

    <h4>Return a movie by year</h4>

    Get the movies from 1997.

    <a href="http://localhost:8080/api/movie/list?y=1997" target="_blank">
    http://localhost:8080/api/movie/list?y=1997
    </a>

    <h4>Return movies by genre</h4>

    Get all the science fiction movies.

    <a href="http://localhost:8080/api/movie/list?g=18" target="_blank">
    http://localhost:8080/api/movie/list?g=18
    </a>

    <h4>Return movies by year or genre</h4>

    Return all movies from 1997 or science fiction (TODO: and)

    <a href="http://localhost:8080/api/movie/list?g=18&y=1997" target="_blank">
    http://localhost:8080/api/movie/list?g=18&y=1997
    </a>

    <H3>UPDATE</H3>

    <h4>Update a rating</h4>

    Downgrade the rating of a movie to 1 out of ten.

    <a href="http://localhost:8080/api/movie/update?id=112&r=10" target="_blank">
    http://localhost:8080/api/movie/update?id=112&r=10
    </a>

    <H3>DELETE

    <h4>Delete all movies</h4>

    Careful!  No confirmation.

    <a href="http://localhost:8080/api/movie/delete" target="_blank">
    http://localhost:8080/api/movie/delete
    </a>

    <h4>Delete a movie by userId</h4>

    Get the movie with userId =101.

    <a href="http://localhost:8080/api/movie/delete/101" target="_blank">
    http://localhost:8080/api/movie/delete/101
    </a>

    <h4>Delete a movie by name</h4>

    Delete the movie named "Saw". (case insensitive too)

    <a href="http://localhost:8080/api/movie/delete?n=Saw" target="_blank">
    http://localhost:8080/api/movie/delete?n=Saw
    </a>

    <h4>Delete a movie by year</h4>

    Delete the movies from 1997.

    <a href="http://localhost:8080/api/movie/delete?y=1997" target="_blank">
    http://localhost:8080/api/movie/delete?y=1997
    </a>

    <h4>Delete movies by genre</h4>

    Delete all the science fiction movies.

    <a href="http://localhost:8080/api/movie/delete?g=18" target="_blank">
    http://localhost:8080/api/movie/delete?g=18
    </a>

    <h4>Delete movies by year or genre</h4>

    <a href="http://localhost:8080/api/movie/delete?g=18&y=1997" target="_blank">
    http://localhost:8080/api/movie/delete?g=18&y=1997
    </a>

    <H3>Local time</H3>

    Returns local time of data pre-parsed for your use.

    <a href="http://localhost:8080/api/timeOfDay" target="_blank">
    http://localhost:8080/api/timeOfDay
    </a>

    <H3>This testing cheat sheet</H3>

    Get this page.

    <a href="http://localhost:8080/api/test" target="_blank">
    http://localhost:8080/api/test

    <H2>Available genre codes for your convenience</h2>
    <pre>
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
        ...
    }
    </pre>
    </body>
</html>
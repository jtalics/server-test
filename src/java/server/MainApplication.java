package server;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.config.AppConfiguration;
import server.db.MovieDAO;
import server.healthcheck.AppHealthCheck;
import server.resources.TestResource;
import server.resources.MovieResource;
import server.resources.TimeOfDayResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainApplication extends Application<AppConfiguration>
{
	final static Logger logger = LoggerFactory.getLogger(MainApplication.class);

	private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(Movie.class) { // comma separated if more than one
		@Override
		public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

    @Override
    public String getName()
	{
        return "Test Server";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // framework bootstrap initialization
		bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ViewBundle<AppConfiguration>());
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {

		try	{
			logger.info("Starting...");

			// application initialization goes here
			final TimeOfDayResource resource = new TimeOfDayResource();
			environment.jersey().register(resource);

			final MovieDAO dao = new MovieDAO(hibernate.getSessionFactory());
			environment.jersey().register(new MovieResource(dao));
            environment.jersey().register(new TestResource());
		}
		catch (Exception exc) {
			// log failure to set up app
			logger.error("Failed to initialize application, exiting...", exc);
			throw new RuntimeException();
		}

		environment.healthChecks().register("app", new AppHealthCheck());

        // register servlet route handlers
		// environment.jersey().register(new YourServlet());
    }

    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }
}

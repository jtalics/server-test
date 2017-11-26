package server.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import server.TimeOfDay;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/timeOfDay")
@Produces(MediaType.APPLICATION_JSON)
public class TimeOfDayResource {

    private final AtomicLong counter = new AtomicLong();

    @GET
    @Timed
    public TimeOfDay getTimeOfDay() {

        return new TimeOfDay(counter.incrementAndGet(), ZonedDateTime.now());
    }
}

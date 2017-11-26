// just a little helper UI for testing

package server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/test") // quick and dirty testing aid
@Produces(MediaType.TEXT_HTML)
public class TestResource {
    public TestResource() {

    }

    @GET
    public TestView getTest() {
        return new TestView();
    }
}

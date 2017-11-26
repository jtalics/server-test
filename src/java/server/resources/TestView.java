package server.resources;

import io.dropwizard.views.View;

public class TestView extends View {

    public TestView() {
        super("test.ftl");
    }
}

package handlers;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

public class PostQuizzHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        String firstname = routingContext.getBodyAsJson().getString("firstname");
        String lastname = routingContext.getBodyAsJson().getString("lastname");
        JsonArray answers = routingContext.getBodyAsJson().getJsonArray("answers");
        routingContext.response().end(firstname);
    }
}
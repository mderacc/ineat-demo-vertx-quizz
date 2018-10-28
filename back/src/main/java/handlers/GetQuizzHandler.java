package handlers;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class GetQuizzHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        String firstname = routingContext.request().params().get("firstname");
        String lastname = routingContext.request().params().get("lastname");
        routingContext.response().end(firstname);
    }
}
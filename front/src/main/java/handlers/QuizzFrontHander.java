package handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;

public class QuizzFrontHander implements Handler<RoutingContext> {

    private ThymeleafTemplateEngine engine;
    public QuizzFrontHander(ThymeleafTemplateEngine engine){
        this.engine = engine;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        routingContext.put("title", "Bienvenu sur le Quizz Ineat");
        engine.render(routingContext, "templates/index.html", res -> {
            if (res.succeeded()) {
                routingContext.response().end(res.result());
            } else {
                routingContext.fail(res.cause());
            }
        });
    }
}

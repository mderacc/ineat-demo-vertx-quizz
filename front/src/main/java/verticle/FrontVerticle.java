package verticle;

import handlers.QuizzFrontHander;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;

public class FrontVerticle extends AbstractVerticle {
    @Override
    public void start() {
        final Router router = Router.router(vertx);
        final ThymeleafTemplateEngine engine = ThymeleafTemplateEngine.create();
        router.get().handler(new QuizzFrontHander(engine));
        vertx.createHttpServer().requestHandler(router::accept).listen(8181);
    }
}

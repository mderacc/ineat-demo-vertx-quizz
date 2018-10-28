package verticle;

import handlers.GetQuizzHandler;
import handlers.PostQuizzHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class BackVerticle extends AbstractVerticle {
    @Override
    public void start() {
        final Router router = Router.router(vertx);
        router.route("/quizz*").handler(BodyHandler.create());
        router.route().handler(CorsHandler.create("*")
                .allowedHeaders(getAllowedHeaders())
                .allowedMethods(getAllowedMethods()));

        router.post("/quizz/").handler(new PostQuizzHandler());
        router.get("/quizz/").handler(new GetQuizzHandler());

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    private Set<String> getAllowedHeaders() {
        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        return allowedHeaders;
    }

    private Set<HttpMethod> getAllowedMethods() {
        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        return allowedMethods;
    }
}

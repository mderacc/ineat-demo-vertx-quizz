import io.vertx.core.Vertx;
import verticle.FrontVerticle;

public class FrontLauncher {
    public static void main (String args[]) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new FrontVerticle());
    }
}

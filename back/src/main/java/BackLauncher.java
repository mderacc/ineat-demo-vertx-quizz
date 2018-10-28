import io.vertx.core.Vertx;
import verticle.BackVerticle;

public class BackLauncher {
    public static void main (String args[]) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new BackVerticle());
    }
}

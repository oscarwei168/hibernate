/**
 * VertxTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/15
 * <p>
 * H i s t o r y
 * 2016/1/15 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.rx.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

/**
 * <p>
 * Title: VertxTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/1/15
 * @since 2016/1/15
 */
public class VertxTest {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40).setClustered(false));

        // Don’t call us, we’ll call you(by handlers)
        vertx.setPeriodic(1000, id -> {
            System.out.println("Time fired...");
        });

        NetServerOptions options = new NetServerOptions().setPort(4321);
        NetServer netServer = vertx.createNetServer(options);
        netServer.listen(1234, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening!");
            } else {
                System.out.println("Failed to bind!");
            }
        });

        // Running blocking code
        vertx.executeBlocking(future -> {
            // Call some blocking API that takes a significant amount of time to return
            String result = "Done"; // someAPI.blockingMethod("hello");
            future.complete(result);
        }, res -> {
            System.out.println("The result is: " + res.result());
        });

        HttpServer httpServer = vertx.createHttpServer();
        Future<HttpServer> httpServerFuture = Future.future();
        // httpServer.listen(httpServerFuture.completer());

        Future<NetServer> netServerFuture = Future.future();
        // netServer.listen(netServerFuture.completer());

//        CompositeFuture.all(httpServerFuture, netServerFuture).setHandler(ar -> {
//            if (ar.succeeded()) {
//                // All server started
//            } else {
//                // At least one server failed
//            }
//        });
    }

    static class Server extends AbstractVerticle {

        public void start() {
            vertx.createHttpServer().requestHandler(req -> req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!")).listen(8080);
        }
    }
}

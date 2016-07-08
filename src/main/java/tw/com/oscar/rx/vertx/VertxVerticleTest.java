/**
 * VertxVerticleTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/3/12
 * <p>
 * H i s t o r y
 * 2016/3/12 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.rx.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

/**
 * <p>
 * Title: VertxVerticleTest.java<br>
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
 * @version v1, 2016/3/12
 * @since 2016/3/12
 */
public class VertxVerticleTest {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new MyVerticle());

        vertx.deployVerticle(new MyVerticle(), stringAsyncResult -> {
            System.out.println("BasicVerticle deployment complete");
        });
    }

    static class MyVerticle extends AbstractVerticle {

        @Override
        public void start(Future<Void> startFuture) throws Exception {
            super.start();
            System.out.println("MyVerticle started!");
            // vertx.deployVerticle(new SecondVerticle());
            vertx.eventBus().consumer("anAddress", message -> {
                System.out.println("1 received message.body() = "
                        + message.body());
            });
        }

        @Override
        public void stop() throws Exception {
            super.stop();
            System.out.println("MyVerticle stopped!");
        }
    }

    static class EventBusSenderVerticle extends AbstractVerticle {

        @Override
        public void start(Future<Void> startFuture) {
            vertx.eventBus().publish("anAddress", "message 2");
            vertx.eventBus().send("anAddress", "message 1");
        }
    }

    static class EventBusReceiverVerticle extends AbstractVerticle {

        private String name = null;

        public EventBusReceiverVerticle(String name) {
            this.name = name;
        }

        @Override
        public void start(Future<Void> startFuture) {
            vertx.eventBus().consumer("anAddress", message -> {
                System.out.println(this.name +
                        " received message: " +
                        message.body());
            });
        }
    }
}

/**
 * HttpServerClientTest.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/3/20
 * <p>
 * H i s t o r y
 * 2016/3/20 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;

/**
 * <p>
 * Title: HttpServerClientTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/3/20
 * @since 2016/3/20
 */
public class HttpServerClientTest {

    static class VertxHttpServerVerticle extends AbstractVerticle {

        private HttpServer httpServer = null;

        @Override
        public void start() throws Exception {
            httpServer = vertx.createHttpServer();

            // Setting request handler
            httpServer.requestHandler(new Handler<HttpServerRequest>() {
                @Override
                public void handle(HttpServerRequest request) {
                    System.out.println("incoming request!");

                    // Reading request header and parameters
                    request.uri();
                    request.path();
                    request.getParam("p1");

                    // Handling POST request
                    Buffer fullRequestBody = Buffer.buffer();
                    if (request.method() == HttpMethod.POST) {
                        request.handler(new Handler<Buffer>() {

                            @Override
                            public void handle(Buffer buffer) {
                                fullRequestBody.appendBuffer(buffer);
                            }
                        });

                        request.endHandler(new Handler<Void>() {

                            @Override
                            public void handle(Void aVoid) {
                                // Access fullRequestBody instance here...
                            }
                        });

                        HttpServerResponse response = request.response();
                        response.setStatusCode(200);
                        response.headers()
                                .add("Content-Length", String.valueOf(57))
                                .add("Content-Type", "text/html");
                        response.write("Vert.x is alive!");
                        response.end();
                    }
                }
            });
            httpServer.listen(9999);
        }

        @Override
        public void stop() throws Exception {
            super.stop();
            if (null != httpServer) {
                httpServer.close();
            }
        }
    }

    static public class VertxHttpClientVerticle extends AbstractVerticle {

        @Override
        public void start() throws Exception {
            HttpClient httpClient = vertx.createHttpClient();
            httpClient.getNow(80, "localhost", "/", new Handler<HttpClientResponse>() {

                @Override
                public void handle(HttpClientResponse httpClientResponse) {
                    System.out.println("Response received");

                    httpClientResponse.bodyHandler(new Handler<Buffer>() {

                        @Override
                        public void handle(Buffer buffer) {
                            System.out.println("Response (" + buffer.length() + "): ");
                            System.out.println(buffer.getString(0, buffer.length()));
                        }
                    });
                }
            });

        }
    }
}

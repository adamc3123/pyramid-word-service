package com.pyramid;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Handles http requests for the pyramid checker
 */
public class PyramidReqHandler implements HttpHandler {
        private final int STATUS_OK = 200;

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String reqUrl = httpExchange.getRequestURI().toString();
            Boolean pyr;

            // Remove '/' from the URL
            reqUrl = reqUrl.substring(1);
            System.out.println(reqUrl);

            pyr = Pyramid.solvePyramid(reqUrl);
            httpExchange.sendResponseHeaders(STATUS_OK, pyr.toString().length());
            httpExchange.getResponseHeaders().set("Content-Type", "text/plain");
            OutputStream os = httpExchange.getResponseBody();
            os.write(pyr.toString().getBytes());
            os.close();
        }
}

package com.pyramid;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

/**
 * Runs a simple http server to handle pyramid word check requests
 */
public class PyramidServer {

    private final String addr = "http://localhost:";
    private final String pyrContext = "/";
    private int port;
    private HttpServer server = null;

    /**
     * Creates a new pyramid server instance
     *
     * @param port server port
     * @param handler server handler
     * @param exe server executor
     * @throws IOException when the server cannot be created
     */
    public PyramidServer(int port, HttpHandler handler, Executor exe) throws IOException {
        this.port = port;
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(pyrContext, handler);
        server.setExecutor(exe);
    }

    /**
     * Starts the server
     */
    public void startServer() {
        server.start();
        System.out.println("INFO: Server started: " + addr + port + "/<string_to_test>");
        System.out.println("INFO: Ctrl-c to exit");
    }

    /**
     * Stops the server
     *
     * @param delay maximum time to wait for requests to finish
     * @return true if the server could be stopped, otherwise false
     */
    public Boolean stopServer(int delay) {
        if (server != null && delay >= 0) {
            server.stop(delay);
            return true;
        }
        return false;
    }

    /**
     * Starts the pyramid word check server
     *
     * @param args server port number
     */
    public static void main(String[] args) {
        int port = 0;
        PyramidServer server = null;

        try{
            port  = Integer.parseInt(args[0]);
            if (port <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Port argument not formatted correctly");
            System.out.println("Usage: PyramidSever <port>");
            return;
        }

        try {
            server = new PyramidServer(port, new PyramidReqHandler(), null);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to start server");
            e.printStackTrace();
            return;
        }

        server.startServer();
    }

}

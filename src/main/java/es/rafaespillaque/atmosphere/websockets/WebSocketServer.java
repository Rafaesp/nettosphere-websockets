package es.rafaespillaque.atmosphere.websockets;

import java.io.IOException;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;

public class WebSocketServer {

    public static void main(String[] args) throws IOException {
        Nettosphere server = new Nettosphere.Builder().config(
                new Config.Builder().host("127.0.0.1").port(8081)
                        .webSocketProtocol(WebSocketHandler.class)
                        .build()).build();
        server.start();
    }   
}


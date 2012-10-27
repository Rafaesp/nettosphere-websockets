package es.rafaespillaque.atmosphere.websockets;

import java.io.IOException;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;

public class WebSocketServer {
	
	private static final String HOST = "0.0.0.0";
	private static final int PORT = 8081;

    public static void main(String[] args) throws IOException {
        Nettosphere server = new Nettosphere.Builder().config(
                new Config.Builder().host(HOST).port(PORT)
                        .webSocketProtocol(WebSocketHandler.class)
                        .build()).build();
        server.start();
        System.out.println(String.format("Servidor empezado en %s:%d", HOST, PORT));
    }   
}


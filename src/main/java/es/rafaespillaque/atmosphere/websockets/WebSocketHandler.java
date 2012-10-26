package es.rafaespillaque.atmosphere.websockets;

import java.io.IOException;
import java.util.List;

import org.atmosphere.config.service.WebSocketHandlerService;
import org.atmosphere.cpr.AtmosphereConfig;
import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.websocket.WebSocket;
import org.atmosphere.websocket.WebSocketProcessor.WebSocketException;
import org.atmosphere.websocket.WebSocketProtocol;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebSocketHandlerService
public class WebSocketHandler implements WebSocketProtocol {

	private JsonParser jParser = new JsonParser();

	public WebSocketHandler() {
	}

	public void onOpen(WebSocket ws) {
		System.out.println("onOpen");
		ws.resource().setBroadcaster(
				BroadcasterFactory.getDefault().lookup("broadcaster", true));
		BroadcasterFactory.getDefault().lookup("broadcaster", true)
				.addAtmosphereResource(ws.resource());

	}

	public List<AtmosphereRequest> onMessage(WebSocket ws, String message) {
		System.out.println("onText: " + message);
		try {
			JsonElement jsonElement = jParser.parse(message);
			JsonObject jObj = jsonElement.getAsJsonObject();
			String type = jObj.getAsJsonPrimitive("type").getAsString();
			if (type.equals("uuid")) {
				ws.write(String.format("{type:uuid,uuid=%s}", ws.resource()
						.uuid()));
			}else if(type.equals("update")){
				Broadcaster b = ws.resource().getBroadcaster();
				b.broadcast(message);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<AtmosphereRequest> onMessage(WebSocket arg0, byte[] arg1,
			int arg2, int arg3) {
		return null;
	}

	public void configure(AtmosphereConfig arg0) {
	}

	public void onClose(WebSocket arg0) {
	}

	public void onError(WebSocket arg0, WebSocketException arg1) {
	}

}

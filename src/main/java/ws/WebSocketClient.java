package ws;

import com.google.gson.Gson;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@ClientEndpoint
public class WebSocketClient {

    private static final Logger logger = Logger.getLogger("WebSocket Client");
    private static final Set<Session> servers = new HashSet<>();
    private static final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    private Map<String, String> brokers;

    public void initMethod(){
        for (Map.Entry<String, String> entry : brokers.entrySet()) {
            String url = "ws://" + entry.getValue() + "/wsTest";
            System.out.println(url);
            this.connect(entry.getKey(), url);
        }
    }

    public void connect(String broker, String uriStr) {
        try {
            URI uri = new URI(uriStr);
            Session s = container.connectToServer(this, uri);
            s.getUserProperties().put("broker", broker);
            servers.add(s);
        } catch (DeploymentException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void open(Session session){
        logger.info("Client WebSocket is opening...");
        servers.add(session);
    }

    @OnMessage
    public void onMessage(String message){
        logger.info("Client get message: " + message);
    }

    @OnClose
    public void onClose(){
        logger.info("WebSocket closed");
    }

    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }

    public void send(String broker, String message) throws IOException {
        for (Session s: servers) {
            if (s.getUserProperties().get("broker").equals(broker)) {
                s.getBasicRemote().sendText(message);
            }
        }
    }

    public void close() throws IOException {
        for (Session s : servers) {
            if(s.isOpen())
                s.close();
        }
    }

    public void setBrokers(Map<String, String> brokers) {
        this.brokers = brokers;
    }

    private String genSubscribeMessage(String product){
        Map<String, String> map = new HashMap<>();
        map.put("msgType", "subscribe");
        map.put("code", product);
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
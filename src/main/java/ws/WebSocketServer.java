package ws;

import ws.decoders.SubscribeMessageDecoder;
import ws.encoders.MarketDepthMessageEncoder;
import ws.message.SubscribeMessage;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(
        value = "/wsTest",
        decoders = { SubscribeMessageDecoder.class },
        encoders = { MarketDepthMessageEncoder.class})
public class WebSocketServer {

    private static final Logger logger = Logger.getLogger("WebSocket Server");
    private static final Set<Session> clients = new HashSet<>();

    @OnOpen
    public void open(Session session){
        clients.add(session);
        logger.info("WebSocket is opening...");
        logger.info("Session id: " + session.getId());
        logger.info("Query string: " + session.getQueryString());
    }

    @OnMessage
    public void onMessage(final Session session, SubscribeMessage msg){
        logger.log(Level.INFO, "Received: {0}", msg.toString());
        System.out.println(msg.toString());
        session.getUserProperties().put("broker", msg.getBroker());
        session.getUserProperties().put("product", msg.getProduct());
        new MockThread(this).run();
    }

    @OnClose
    public void onClose(){
        logger.info("WebSocket closed");
    }

    public void send(String broker, String product, String str) throws IOException {
        for (Session s : clients) {
            if (s.isOpen()) {
                if (s.getUserProperties().get("broker").equals(broker) && s.getUserProperties().get("product").equals(product)) {
                    s.getBasicRemote().sendText(str);
                    logger.log(Level.INFO, "Sent: {0}", str);
                }
            }
        }
    }
}
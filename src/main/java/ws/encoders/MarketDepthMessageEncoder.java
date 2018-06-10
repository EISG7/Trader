package ws.encoders;

import com.google.gson.Gson;
import ws.message.MarketDepthMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MarketDepthMessageEncoder implements Encoder.Text<MarketDepthMessage> {

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(MarketDepthMessage marketDepthMessage) throws EncodeException {
        Gson gson = new Gson();
        return gson.toJson(marketDepthMessage);
    }
}

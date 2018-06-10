package ws.decoders;

import com.google.gson.Gson;
import ws.message.client.ClientMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.HashMap;
import java.util.Set;

public class ClientMessageDecoder implements Decoder.Text<ClientMessage> {
    private HashMap messageMap;

    @Override
    public ClientMessage decode(String s) throws DecodeException {
        ClientMessage msg = null;
        if (willDecode(s)) {

        } else {
            throw new DecodeException(s, "[Message] Can't decode.");
        }
        return msg;
    }

    @Override
    public boolean willDecode(String s) {
        boolean decoded = false;
        Gson gson = new Gson();
        messageMap = gson.fromJson(s, HashMap.class);
        @SuppressWarnings("rawtypes")
        Set keys = messageMap.keySet();
        decoded = true;
        return decoded;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}

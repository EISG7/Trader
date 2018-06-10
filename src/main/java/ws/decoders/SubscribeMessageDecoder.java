package ws.decoders;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import ws.message.SubscribeMessage;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

/* Decode a JSON message into a SubscribeMessage
 */
public class SubscribeMessageDecoder implements Decoder.Text<SubscribeMessage> {
    /* Stores the name-value pairs from a JSON message as a Map */
    private Map<String,String> messageMap;

    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    /* Create a new Message object if the message can be decoded */
    @Override
    public SubscribeMessage decode(String string) throws DecodeException {
        SubscribeMessage msg;
        if (willDecode(string)) {
            msg = new SubscribeMessage(messageMap.get("broker"), messageMap.get("product"));
        } else {
            throw new DecodeException(string, "[Message] Can't decode.");
        }
        return msg;
    }

    /* Decode a JSON message into a Map and check if it contains
     * all the required fields according to its type. */
    @SuppressWarnings("unchecked")
    @Override
    public boolean willDecode(String string) {
        boolean decodes = false;
        /* Convert the message into a map */
        Gson gson = new Gson();
        messageMap = gson.fromJson(string, HashMap.class);
        /* Check the kind of message and if all fields are included */
        @SuppressWarnings("rawtypes")
        Set keys = messageMap.keySet();
        if (keys.contains("broker") && keys.contains("product")) {
            decodes = true;
        }
        return decodes;
    }
}

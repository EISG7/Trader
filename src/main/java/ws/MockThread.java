package ws;

import com.google.gson.Gson;
import entity.MarketDepth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockThread extends Thread {

    private WebSocketServer ws;
    private Gson gson = new Gson();
    private List<MarketDepth> list = new ArrayList<>();
    private Random random = new Random();

    public MockThread(WebSocketServer ws) {
        this.ws = ws;
        list.add(new MarketDepth(2, 100, 1248, "Buy"));
        list.add(new MarketDepth(1, 100, 1250, "Buy"));
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
                list.add(new MarketDepth(3, random.nextInt(200) % 101 + 100, random.nextInt(1260) % 11 + 1250, "Buy"));
                ws.send("broker1","au1809", gson.toJson(list));
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

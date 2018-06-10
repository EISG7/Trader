package ws.message;

import entity.MarketDepth;

import java.util.List;

public class MarketDepthMessage extends Message {
    private List<MarketDepth> marketDepthList;

    public MarketDepthMessage() {}

    public MarketDepthMessage(List<MarketDepth> marketDepthList) {
        this.marketDepthList = marketDepthList;
    }

    public List<MarketDepth> getMarketDepthList() {
        return marketDepthList;
    }

    public void setMarketDepthList(List<MarketDepth> marketDepthList) {
        this.marketDepthList = marketDepthList;
    }


}

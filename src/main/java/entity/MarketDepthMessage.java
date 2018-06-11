package entity;

import java.util.List;

public class MarketDepthMessage{

    private String broker;
    private String product;
    private List<MarketDepth> marketDepth;


    public MarketDepthMessage(String broker, String product, List<MarketDepth> marketDepth) {
        this.broker = broker;
        this.product = product;
        this.marketDepth = marketDepth;
    }

    public MarketDepthMessage() {

    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public List<MarketDepth> getMarketDepth() {
        return marketDepth;
    }

    public void setMarketDepth(List<MarketDepth> marketDepth) {
        this.marketDepth = marketDepth;
    }

    public String genKey() {
        return this.broker + ":" + this.product;
    }

}

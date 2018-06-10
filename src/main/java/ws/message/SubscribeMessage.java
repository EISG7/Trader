package ws.message;

public class SubscribeMessage extends Message{

    public SubscribeMessage(String broker, String product) {
        this.broker = broker;
        this.product = product;
    }

    public SubscribeMessage() {
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

    @Override
    public String toString() {
        return broker + ':' + product;
    }

    private String broker;
    private String product;
}

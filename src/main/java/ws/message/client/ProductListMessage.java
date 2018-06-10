package ws.message.client;

import java.util.List;

public class ProductListMessage{
    private List<ProductMessage> products;
    private String msgType;

    public ProductListMessage(List<ProductMessage> products) {
        this.products = products;
        this.msgType = "products";
    }

    public ProductListMessage() {
        this.msgType = "products";
    }

    public List<ProductMessage> getProducts() {
        return products;
    }

    public void setProducts(List<ProductMessage> products) {
        this.products = products;
    }
}

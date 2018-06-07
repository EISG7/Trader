package entity;

public class OrderView {
    private int id;
    private String broker;
    private String product;
    private String period;
    private int price;
    private int quantity;
    private String initiatorName;
    private String initiatorComp;
    private String initiatorSide;
    private String completionName;
    private String completionComp;
    private String completionSide;

    public OrderView(Orders order) {
        this.id = order.getId();
        this.broker = order.getBroker();
        this.product = order.getProduct();
        this.period = order.getPeriod();
        this.price = order.getPrice();
        this.quantity = order.getQuantity();
        this.initiatorName = order.getInitiatorName();
        this.initiatorComp = order.getInitiatorComp();
        this.initiatorSide = order.getInitiatorSide() ? "Buy" : "Sell";
        this.completionName = order.getCompletionName();
        this.completionComp = order.getCompletionComp();
        this.completionSide = order.getInitiatorSide() ? "Sell" : "Buy";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getInitiatorComp() {
        return initiatorComp;
    }

    public void setInitiatorComp(String initiatorComp) {
        this.initiatorComp = initiatorComp;
    }

    public String getInitiatorSide() {
        return initiatorSide;
    }

    public void setInitiatorSide(String initiatorSide) {
        this.initiatorSide = initiatorSide;
    }

    public String getCompletionName() {
        return completionName;
    }

    public void setCompletionName(String completionName) {
        this.completionName = completionName;
    }

    public String getCompletionComp() {
        return completionComp;
    }

    public void setCompletionComp(String completionComp) {
        this.completionComp = completionComp;
    }

    public String getCompletionSide() {
        return completionSide;
    }

    public void setCompletionSide(String completionSide) {
        this.completionSide = completionSide;
    }
}

package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String broker;
    private String code;
    private String product;
    private String period;
    private int price;
    private int quantity;
    private String initiatorName;
    private String initiatorComp;
    private boolean initiatorSide; // true: buy, false: sell
    private String completionName;
    private String completionComp;
    private Timestamp completionTime;

    public Order(String broker, String code, String product, String period, int price, int quantity, String initiatorName, String initiatorComp, boolean initiatorSide, String completionName, String completionComp, Timestamp completionTime) {
        this.broker = broker;
        this.code = code;
        this.product = product;
        this.period = period;
        this.price = price;
        this.quantity = quantity;
        this.initiatorName = initiatorName;
        this.initiatorComp = initiatorComp;
        this.initiatorSide = initiatorSide;
        this.completionName = completionName;
        this.completionComp = completionComp;
        this.completionTime = completionTime;
    }

    public Order() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public boolean isInitiatorSide() {
        return initiatorSide;
    }

    public void setInitiatorSide(boolean initiatorSide) {
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

    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }
}

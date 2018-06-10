package ws.message.client;

public class ProductMessage extends ClientMessage{
    private String code;
    private String name;
    private String period;
    private String measurement;
    private Integer level;

    public ProductMessage(String code, String name, String period, String measurement, Integer level) {
        this.code = code;
        this.name = name;
        this.period = period;
        this.measurement = measurement;
        this.level = level;
    }

    public ProductMessage() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

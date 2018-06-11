package entity;

public class MarketDepth implements Comparable<MarketDepth> {
    private Integer level;
    private Integer vol;
    private Integer price;
    private String side;

    public MarketDepth(Integer level, Integer vol, Integer price, String side) {
        this.level = level;
        this.vol = vol;
        this.price = price;
        this.side = side;
    }

    public MarketDepth() {
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public int compareTo(MarketDepth o) {
        return o.price.compareTo(this.price);
    }
}

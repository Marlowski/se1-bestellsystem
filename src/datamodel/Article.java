package datamodel;

public class Article {
    private String id;
    private String description;
    private long unitPrice;
    private int unitInStore;

    protected Article(String id, String descr, long price, int units) {
        this.id = id;
        this.description = descr;
        this.unitPrice = price;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr) {
        this.description = descr;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long price) {
        this.unitPrice = price;
    }

    public int getUnitInStore() {
        return unitInStore;
    }

    public void setUnitInStore(int number) {
        this.unitInStore = number;
    }
}

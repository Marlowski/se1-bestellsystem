package datamodel;

public class OrderItem {
    private String description;
    private final Article article;
    private int unitsOrdered;

    protected OrderItem(String descr, Article article, int units) {
        this.article = article;
        this.description = descr;
        this.unitsOrdered = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr) {
        this.description = descr;
    }

    public Article getArticle() {
        return article;
    }

    public int getUnitsOrdered() {
        return unitsOrdered;
    }

    public void setUnitsOrdered(int number) {
        this.unitsOrdered = number;
    }
}

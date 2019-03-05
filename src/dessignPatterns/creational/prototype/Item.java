package dessignPatterns.creational.prototype;

/**
 * Created by Favio on 11/11/2017.
 */
public abstract class Item implements Cloneable {
    private String title;
    private double price;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * It is recommende to implement its own clone methodFromInterface that return the desired Oblect
     * e.g. protected Item (clone) {return (Item)(super.clone());}
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

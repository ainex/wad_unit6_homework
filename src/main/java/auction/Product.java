package auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by yfain11 on 4/4/14.
 */
public class Product {
    public int id;
    public String title;
    public String thumb;
    public String description;
    public int quantity;   // How many items the seller has
    public LocalDateTime auctionEndTime;
    public int watchers;
    public BigDecimal minimalPrice;     // Don't sell unless the bid is more than min price
    public BigDecimal reservedPrice;   // If a bidder offers reserved price, the main.java.auction is closed

    public Product(int id, String title, String thumb, String description, int quantity, LocalDateTime auctionEndTime, int watchers, BigDecimal minimalPrice, BigDecimal reservedPrice) {
        this.id = id;
        this.title = title;
        this.thumb = thumb;
        this.description = description;
        this.quantity = quantity;
        this.auctionEndTime = auctionEndTime;
        this.watchers = watchers;
        this.minimalPrice = minimalPrice;
        this.reservedPrice = reservedPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 31 * result + id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", minimalPrice=" + minimalPrice +
                ", reservedPrice=" + reservedPrice +
                '}';
    }
}

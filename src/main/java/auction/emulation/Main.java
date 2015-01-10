package auction.emulation;


import auction.Bid;
import auction.Product;
import auction.User;
import auction.engine.BindingEngine;
import auction.engine.BindingEngineImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static final List<Bid> bids = new ArrayList<>();
    static final List<User> users = new ArrayList<>();
    static final List<Product> products = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("The Auction is getting started!");

        products.add(new Product(1, "title1", "#", "description1", 3, LocalDateTime.now(), 0, new BigDecimal(10), new BigDecimal(50)));
        products.add(new Product(2, "title2", "#", "description2", 1, LocalDateTime.now(), 0, new BigDecimal(20), new BigDecimal(100)));
        users.add(new User("user1", "email_1@mail.com", true));
        users.add(new User("user2", "email_2@mail.com", false));
        users.add(new User("user3", "email_3@mail.com", true));

        BindingEngine engine = new BindingEngineImpl();
        engine.config(bids);
        Bidder bidder = Bidder.instance(products, bids, users, engine);
        bidder.start();



        try {
            Thread.sleep(12000);
            System.out.println("Bids: " + bids);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

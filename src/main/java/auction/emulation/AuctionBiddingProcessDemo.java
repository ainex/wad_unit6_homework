package auction.emulation;


import auction.Bid;
import auction.Product;
import auction.User;
import auction.engine.BidingEngine;
import auction.engine.BidingEngineImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo of auction bidding process. See also BidingEngineSpec for more details
 */
public class AuctionBiddingProcessDemo {

    public static void main(String[] args) {
        final List<Bid> bids = new ArrayList<>();
        final List<User> users = new ArrayList<>();
        final List<Product> products = new ArrayList<>();

        products.add(new Product(1, "title1", "#", "description1", 3, LocalDateTime.now(), 0, new BigDecimal(30), new BigDecimal(150)));
        products.add(new Product(2, "title2", "#", "description2", 1, LocalDateTime.now(), 0, new BigDecimal(20), new BigDecimal(180)));
        users.add(new User("user1", "email_1@mail.com", true));
        users.add(new User("user2", "email_2@mail.com", true));
        users.add(new User("user3", "email_3@mail.com", true));

        System.out.println("The Auction is getting started!");

        BidingEngine engine = new BidingEngineImpl();
        engine.config(bids);

        Bidder bidder = Bidder.instance(products, users, engine);
        bidder.start();


    }
}

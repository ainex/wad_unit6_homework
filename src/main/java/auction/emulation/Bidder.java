package auction.emulation;


import auction.Bid;
import auction.Product;
import auction.User;
import auction.engine.BidingEngine;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ainex on 07.01.2015.
 */
public class Bidder extends TimerTask {
    private static final int ONE_SECOND = 1000;
    private static final int MAX_PRICE = 200;
    private static final int MIN_PRICE = 5;

    private Timer timer;
    private Random rand;
    private List<Product> products;
    private List<User> users;
    private BidingEngine engine;

    public static Bidder instance(final List<Product> products, final List<User> users, final BidingEngine engine) {
        return new Bidder(products, users, engine);
    }

    private Bidder(final List<Product> products, final List<User> users, final BidingEngine engine) {
        timer = new Timer();
        rand = new Random();
        this.products = new ArrayList<>(products);
        this.users = new ArrayList<>(users);
        this.engine = engine;
    }

    public void start() {
        timer.scheduleAtFixedRate(this, 0, ONE_SECOND);
    }

    @Override
    public void run() {
        int random = rand.nextInt(MAX_PRICE) + MIN_PRICE;

        Bid bid = new Bid(products.get(random % products.size()), new BigDecimal(random), 1, users.get(random % users.size()));
        System.out.println("New bid: " + bid);

        BidingEngine.Result biddingResult = engine.processBid(bid);
        if (BidingEngine.Result.MAX_PRICE_BID.equals(biddingResult)) {
            System.out.println("End of auction on Product: " + bid.product);
            products.remove(bid.product);
            if (products.size() == 0) {
                System.out.println("Auction is closed.");
                timer.cancel();
                timer.purge();
            }
        }
    }
}

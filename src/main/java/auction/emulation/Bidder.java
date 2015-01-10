package auction.emulation;


import auction.Bid;
import auction.Product;
import auction.User;
import auction.engine.BindingEngine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

/**
 * Created by ainex on 07.01.2015.
 */
public class Bidder extends TimerTask {
    private static final int ONE_SECOND = 1 * 1000;
    private static final int MAX_PRICE = 200;
    private static final int MIN_PRICE = 5;

    private Timer timer;
    private Random rand;
    private List<Product> products;
    private List<Bid> bids;
    private List<User> users;
    private BindingEngine engine;

    public static Bidder instance(final List<Product> products, final List<Bid> bids, final List<User> users, final BindingEngine engine) {
        return new Bidder(products, bids, users, engine);

    }

    private Bidder(final List<Product> products, final List<Bid> bids, final List<User> users, final BindingEngine engine) {
        timer = new Timer();
        rand = new Random();
        this.products = products;
        this.bids = bids;
        this.users = users;
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
       BindingEngine.Result biddingResult = engine.processBid(bid);
        if(BindingEngine.Result.MAX_PRICE_BID.equals(biddingResult)){
            System.out.println("End of auction on Product: " + bid.product);
        }
    }
}

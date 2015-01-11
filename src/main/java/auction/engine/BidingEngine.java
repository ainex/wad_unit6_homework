package auction.engine;

import auction.Bid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ainex on 10.01.2015.
 */
public interface BidingEngine {



    Result processBid(final Bid bid);
    void config(final List<Bid> bids);

    static enum Result{
        MIN_PRICE_BID,
        MAX_PRICE_BID,
        VALID_BID;
    }
}

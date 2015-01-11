package auction.engine;

import auction.Bid;
import auction.emulation.Notifier;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by ainex on 10.01.2015.
 */
public class BidingEngineImpl implements BidingEngine {

    private List<Bid> bids;

    @Override
    public Result processBid(Bid bid) {
        if (bid.product.minimalPrice.compareTo(bid.amount) >= 0) {
            Notifier.onMinPriceBid(bid.user);
            return Result.MIN_PRICE_BID; //bid is rejected
        } else if (bid.amount.compareTo(bid.product.reservedPrice) >= 0) {
            Notifier.onReservedPriceBid(bid.user);
            bid.isWinning = true;
            bids.add(bid);
            return Result.MAX_PRICE_BID; //end of auction on this product
        } else {
            //notify all users who has bids on this product
            System.out.println("Bid is accepted!");
            Predicate<Bid> toNotify = b -> b.user.getOverbidNotifications && b.product.equals(bid.product) && (b.amount.compareTo(bid.amount) <= 0);
            bids.stream().filter(toNotify).forEach(b -> Notifier.onOverbid(b.user));
            bids.add(bid);
            return Result.VALID_BID;
        }

    }

    @Override
    public void config(List<Bid> bids) {
        this.bids = bids;
    }
}

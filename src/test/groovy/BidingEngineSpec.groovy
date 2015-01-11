import auction.Bid
import auction.Product
import auction.User
import auction.engine.BidingEngine
import auction.engine.BidingEngineImpl
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by ainex on 11.01.2015.
 */
class BidingEngineSpec extends Specification {
    @Shared
    def bids = new ArrayList<Bid>();
    @Shared
    def users = new ArrayList<User>();
    @Shared
    def products = new ArrayList<Product>();
    @Shared
    def engine = new BidingEngineImpl();


    def setupSpec() {
        engine.config(bids);
        println "//init products and users"
        products.add(new Product(1, "title1", "#", "description1", 3, LocalDateTime.now(), 0, new BigDecimal(30), new BigDecimal(150)))
        products.add(new Product(2, "title2", "#", "description2", 1, LocalDateTime.now(), 0, new BigDecimal(20), new BigDecimal(130)))
        users.add(new User("user1", "email_1@mail.com", true))
        users.add(new User("user2", "email_2@mail.com", true))
        users.add(new User("user3", "email_3@mail.com", true))
    }

    def setup() {
        println "bids clean"
        bids.clear();

    }

    def "reject minimal bids"(Bid bid) {
        when:
        def result = engine.processBid(bid)
        then:
        bids.size() == old(bids.size())
        expect:
        BidingEngine.Result.MIN_PRICE_BID == result

        where:
        bid << [new Bid(products.get(0), new BigDecimal(30), 1, users.get(0)), new Bid(products.get(0), new BigDecimal(20), 1, users.get(0)), new Bid(products.get(1), new BigDecimal(15), 1, users.get(2))]
    }

    def "push valid bids"(Bid bid) {
        when:
        def result = engine.processBid(bid)
        then:
        bids.size() == old(bids.size()) + 1
        expect:
        BidingEngine.Result.VALID_BID == result

        where:
        bid << [new Bid(products.get(0), new BigDecimal(50), 1, users.get(0)), new Bid(products.get(0), new BigDecimal(110), 1, users.get(1)), new Bid(products.get(1), new BigDecimal(100), 1, users.get(0))]
    }

    def "push max bids"(Bid bid) {
        when:
        def result = engine.processBid(bid)
        then:
        bids.size() == old(bids.size()) + 1
        def bidResult = bids.get(bids.size() - 1).isWinning
        expect:
        BidingEngine.Result.MAX_PRICE_BID == result
        true == bidResult

        where:
        bid << [new Bid(products.get(0), new BigDecimal(150), 1, users.get(0)), new Bid(products.get(1), new BigDecimal(130), 1, users.get(1))]
    }

}

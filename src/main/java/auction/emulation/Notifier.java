package auction.emulation;


import auction.User;

/**
 * Created by ainex on 07.01.2015.
 */
public class Notifier {
    public static void onReservedPriceBid(final User user) {
        System.out.println("Email to User " + user.email + " :You are winner!");
    }

    public static void onMinPriceBid(final User user) {
        System.out.println("Email to User " + user.email + " :Sorry, the bidding price is to low!");
    }

    public static void onOverbid(final User user) {
        System.out.println("Email to User " + user.email + " : Your price was overbidded.");
    }

}

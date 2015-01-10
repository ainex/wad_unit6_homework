package auction;

/**
 * Created by yfain11 on 4/4/14.
 */
public class User {
    public int id;
    public String name;
    public String email;
    public boolean getOverbidNotifications;

    public User(String name, String email, boolean getOverbidNotifications) {

        this.name = name;
        this.email = email;
        this.getOverbidNotifications = getOverbidNotifications;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", notifications=" + getOverbidNotifications +
                '}';
    }
}

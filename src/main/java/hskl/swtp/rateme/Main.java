package hskl.swtp.rateme;

import hskl.swtp.rateme.db.PoiDB;
import hskl.swtp.rateme.db.PoiTagDB;
import hskl.swtp.rateme.db.RatingDB;
import hskl.swtp.rateme.db.UserDB;
import hskl.swtp.rateme.model.Rating;
import hskl.swtp.rateme.model.User;

public class Main {

    public static void main() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadPois");
        PoiDB.getInstance().loadPois().forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadPoiById");
        System.out.println(PoiDB.getInstance().loadPoi(3188600462L));

        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadTagsForPoi");
        PoiTagDB.getInstance().loadTagsForPoi(590876729).forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadUserById");
        User user = UserDB.getInstance().loadUser(2);
        System.out.println(user.toString());

        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadUserByUsername");
        User user2 = UserDB.getInstance().loadUser("otto123");
        System.out.println(user2.toString());

        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadRating");
        Rating rating2 = RatingDB.getInstance().loadRating(1);
        System.out.println(rating2.toString());

        System.out.println("------------------------------------------------------------------------");
        System.out.println("validatePassword");
        boolean bool = UserDB.getInstance().validatePassword("otto234", "password2");
        System.out.println(bool);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadRatingsForPoi");
        RatingDB.getInstance().loadRatingsForPoi(29444682).forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("loadRatingsForUser");
        RatingDB.getInstance().loadRatingsForUser(2).forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("Main Done");

    }
}

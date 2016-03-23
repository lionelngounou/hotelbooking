package challenge.hotelbooking;

import java.util.Date;

/**
 * @author lionel ngounou
 */
public class App {

    public static void main(String[] args) {
        BookingManager bm = new BookingManagerImpl();
        Date today = java.sql.Date.valueOf("2012-03-28");
        System.out.println(bm.isRoomAvailable(101, today)); // outputs true 
        bm.addBooking("Smith", 101, today);
        System.out.println(bm.isRoomAvailable(101, today)); // outputs false
    }
}

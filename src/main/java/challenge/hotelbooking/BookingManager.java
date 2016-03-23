package challenge.hotelbooking;

import java.util.Date;

/**
 * @author lionel ngounou
 */
public interface BookingManager {
    
    /**
        * Return true if there is no booking for the given room on the date, * otherwise false
    */ 
    public boolean isRoomAvailable(Integer room, Date date);
    
    /**
        * Add a booking for the given guest in the given room on the given date.
    */
    public void addBooking(String guest, Integer room, Date date);
    
    public Iterable<Integer> getAvailableRooms(Date date);
}

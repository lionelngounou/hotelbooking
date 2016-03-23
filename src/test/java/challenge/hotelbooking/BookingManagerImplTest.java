package challenge.hotelbooking;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author lionel ngounou
 */
public class BookingManagerImplTest {
    
    private BookingManager bookingManager ; 
        
    @Before
    public void setUp() { 
        bookingManager = new BookingManagerImpl();
    }

    @Test
    public void testIsRoomAvailable() {
        
        //room 101 available when not booked today
        Date today = new Date();
        assertTrue(bookingManager.isRoomAvailable(Room._101.getNumber(), today));
        
        //room 101 not available already booked for today
        bookingManager.addBooking("guest 1", Room._101.getNumber(), today);
        assertFalse(bookingManager.isRoomAvailable(Room._101.getNumber(), today));
        
        //room 101 available when booked for a different date
        assertTrue(bookingManager.isRoomAvailable(Room._101.getNumber(), new Date(today.getTime() + 1000_000_000)));
        
        //room 102 available when booked for today
        assertTrue(bookingManager.isRoomAvailable(Room._102.getNumber(), today));
    }

    @Test(expected = AvailabilityException.class)
    public void testAddBookingWhenNotAvailable() {
        Date today = new Date();
        bookingManager.addBooking("guest 1", Room._101.getNumber(), today);
        bookingManager.addBooking("guest 1", Room._101.getNumber(), today);       
    }

    @Test
    public void testAddBooking() {
        Date today = new Date();
        
        assertTrue(bookingManager.isRoomAvailable(Room._101.getNumber(), today));        
        //room 101 not available already booked for today
        bookingManager.addBooking("guest 1", Room._101.getNumber(), today);
        assertFalse(bookingManager.isRoomAvailable(Room._101.getNumber(), today));
        
        assertTrue(bookingManager.isRoomAvailable(Room._102.getNumber(), today));        
        //room 101 not available already booked for today
        bookingManager.addBooking("guest 1", Room._102.getNumber(), today);
        assertFalse(bookingManager.isRoomAvailable(Room._102.getNumber(), today));
    }
    
    @Test
    public void testGetAvailableRooms(){
        Date today = new Date();
        //all rooms available when no bookings made
        bookingManager.getAvailableRooms(today).forEach((n) -> {
            if(!Room.stream().anyMatch(r -> r.getNumber()==n))
                fail();
        });
        
        //room 101 not among the rooms available today when already booked
        bookingManager.addBooking("guest 1", Room._101.getNumber(), today);
        bookingManager.getAvailableRooms(today).forEach((n) -> {
            if(n==Room._101.getNumber())
                fail();
            else if(!Room.stream().anyMatch(r -> r.getNumber()==n))
                fail();
        });
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddRoomsWithWrongGuest(){
        bookingManager.addBooking(null, Room._101.getNumber(), new Date());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddRoomsWithWrongRoom(){
        bookingManager.addBooking("guest 1", 39938, new Date());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddRoomsWithWrongDate(){
        bookingManager.addBooking("guest 1", Room._101.getNumber(), null);
    }
    
}

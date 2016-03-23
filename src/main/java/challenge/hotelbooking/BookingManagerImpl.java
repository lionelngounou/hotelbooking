package challenge.hotelbooking;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author lionel ngounou
 */
public class BookingManagerImpl implements BookingManager {
    
    private final Set<RoomBooking> roomBookings;

    public BookingManagerImpl() {
        this.roomBookings = new CopyOnWriteArraySet();
    }   

    @Override
    public boolean isRoomAvailable(Integer room, Date date) {
        verifyRoom(room);
        return !roomBookings.stream().filter(
                rb -> rb.getRoomNumber()==room && Util.sameDate(date, rb.getDate())
            ).findAny().isPresent();
    }

    @Override
    public void addBooking(String guest, Integer room, Date date) {
        checkRequired("guest", guest);
        checkRequired("date", date);
        checkRequired("room", guest);
        verifyRoom(room);
        if(!roomBookings.add(new RoomBooking(getRoomByNumber(room).get(), date, guest)))
            throw new AvailabilityException(room, date);
    }
    
    @Override
    public Iterable<Integer> getAvailableRooms(Date date){
        return Room.stream().mapToInt(r -> r.getNumber())
            .filter(n ->  isRoomAvailable(n, date))
            .boxed()
            .collect(Collectors.toList());
    }    
    
    /**checks if room exists in our set of rooms*/
    private static void verifyRoom(Integer room){
        if(!getRoomByNumber(room).isPresent())
            throw new IllegalArgumentException("Room [" + room + "] does not exists");
    }
    
    /**throws an exception if the arg is null - as its required*/
    private static void checkRequired(String arg, Object value){
        if(value==null)
            throw new IllegalArgumentException("argument [" + arg + "] required");
    }
    
    /**should be present in optional if room found*/
    private static Optional<Room> getRoomByNumber(Integer num){
        return Room.stream().filter(r -> r.getNumber()==num).findAny();
    }
    
}

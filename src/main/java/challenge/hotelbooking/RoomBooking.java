package challenge.hotelbooking;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * @author lionel ngounou
 */
public class RoomBooking {
    
    private final Room room; 
    private final LocalDate date; 
    private final String guest; 

    public RoomBooking(Room room, LocalDate date, String guest) {
        this.room = room;
        this.guest = guest;
        this.date = date;
    }
    
    public RoomBooking(Room room, Date date, String guest) {
        this(room, Util.toLocalDate(date), guest);
    }

    @Override
    public String toString() {
        return "RoomBooking{" + "room=" + room + ", date=" + date + ", guest=" + guest + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.room);
        hash = 67 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RoomBooking other = (RoomBooking) obj;
        if (this.room != other.room || !Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    public Room getRoom() {
        return room;
    }

    public int getRoomNumber() {
        return room.getNumber();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getGuest() {
        return guest;
    }
    
}

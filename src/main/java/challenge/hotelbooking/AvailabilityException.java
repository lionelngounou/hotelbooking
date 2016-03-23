package challenge.hotelbooking;

import java.util.Date;

/**
 * @author lionel.ngounou
 */
public class AvailabilityException extends RuntimeException {

    public AvailabilityException(Integer room, Date date) {
        super("Room ["+room+"] not available for date ["+date+"]");
    }
    
}

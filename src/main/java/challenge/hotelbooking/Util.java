package challenge.hotelbooking;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lionel.ngounou
 */
public class Util {
    
    public static LocalDate toLocalDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }
    
    public static boolean sameDate(Date date, LocalDate localDate){
        return toLocalDate(date).isEqual(localDate);
    }
}

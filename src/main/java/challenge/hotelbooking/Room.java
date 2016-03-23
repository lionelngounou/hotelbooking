package challenge.hotelbooking;

import java.util.stream.Stream;

/**
 * @author lionel.ngounou
 */
public enum Room {
    
    _101(101), _102(102), _201(201), _203(203);
    
    private final int number; 

    private Room(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Room["+number+"]";
    }
    
    public static Stream<Room> stream(){
        return Stream.of(values());
    }
    
}

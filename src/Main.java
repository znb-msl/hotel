import com.reservation.RoomType;
import com.reservation.Service;

import java.text.SimpleDateFormat;

// Main class to test the reservation system
public class Main {
    public static void main(String[] args) throws Exception {
        Service service = new Service();

        // Create rooms
        service.setRoom(1, RoomType.STANDARD_SUITE, 1000);
        service.setRoom(2, RoomType.JUNIOR_SUITE, 2000);
        service.setRoom(3, RoomType.MASTER_SUITE, 3000);

        // Create users
        service.setUser(1, 5000);
        service.setUser(2, 10000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Bookings
        try {
            // Valid booking
            service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026"));
        } catch (Exception e) {
            System.out.println("Booking error 1: " + e.getMessage());
        }

        try {
            // Invalid booking due to incorrect date range
            service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026"));
        } catch (Exception e) {
            System.out.println("Booking error 2: " + e.getMessage());
        }

        try {
            // Valid booking
            service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));
        } catch (Exception e) {
            System.out.println("Booking error 3: " + e.getMessage());
        }

        try {
            // Overlapping booking with previous reservation
            service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026"));
        } catch (Exception e) {
            System.out.println("Booking error 4: " + e.getMessage());
        }

        try {
            // Valid booking
            service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));
        } catch (Exception e) {
            System.out.println("Booking error 5: " + e.getMessage());
        }

        // Modify room 1's price after booking (previous bookings remain unaffected)
        service.setRoom(1, RoomType.STANDARD_SUITE, 10000);

        // Final display of all rooms and bookings
        service.printAll();
        System.out.println();

        // Display all users
        service.printAllUsers();
    }
}

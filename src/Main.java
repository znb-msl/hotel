import com.reservation.RoomType;
import com.reservation.Service;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        Service service = new Service();

        // Création des chambres
        service.setRoom(1, RoomType.STANDARD_SUITE, 1000);
        service.setRoom(2, RoomType.JUNIOR_SUITE, 2000);
        service.setRoom(3, RoomType.MASTER_SUITE, 3000);

        // Création des utilisateurs
        service.setUser(1, 5000);
        service.setUser(2, 10000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Réservations
        try {
            service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026")); // OK
        } catch (Exception e) {
            System.out.println("Erreur réservation 1: " + e.getMessage());
        }

        try {
            service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026")); // Dates invalides
        } catch (Exception e) {
            System.out.println("Erreur réservation 2: " + e.getMessage());
        }

        try {
            service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026")); // OK
        } catch (Exception e) {
            System.out.println("Erreur réservation 3: " + e.getMessage());
        }

        try {
            service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026")); // Chevauche réservation utilisateur 1
        } catch (Exception e) {
            System.out.println("Erreur réservation 4: " + e.getMessage());
        }

        try {
            service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026")); // OK
        } catch (Exception e) {
            System.out.println("Erreur réservation 5: " + e.getMessage());
        }

        // Modification chambre 1
        service.setRoom(1, RoomType.STANDARD_SUITE, 10000);

        // Affichage final
        service.printAll();
        System.out.println();
        service.printAllUsers();
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    // Room class
    static class Room {
        int roomNumber;
        String category;
        boolean isBooked;

        Room(int roomNumber, String category) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.isBooked = false;
        }
    }

    // Reservation class
    static class Reservation {
        String customerName;
        Room room;

        Reservation(String customerName, Room room) {
            this.customerName = customerName;
            this.room = room;
        }
    }

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeRooms();

        int choice;
        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom(sc);
                    break;
                case 3:
                    cancelReservation(sc);
                    break;
                case 4:
                    viewReservations();
                    break;
                case 5:
                    System.out.println("Thank you for using Hotel Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    // Initialize rooms
    static void initializeRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
    }

    // View available rooms
    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room No: " + room.roomNumber + " | Category: " + room.category);
            }
        }
    }

    // Book room
    static void bookRoom(Scanner sc) {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && !room.isBooked) {
                room.isBooked = true;
                reservations.add(new Reservation(name, room));
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available or already booked.");
    }

    // Cancel reservation
    static void cancelReservation(Scanner sc) {
        System.out.print("Enter customer name to cancel reservation: ");
        String name = sc.nextLine();

        for (Reservation r : reservations) {
            if (r.customerName.equalsIgnoreCase(name)) {
                r.room.isBooked = false;
                reservations.remove(r);
                System.out.println("Reservation cancelled successfully.");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }

    // View reservations
    static void viewReservations() {
        System.out.println("\nCurrent Reservations:");
        for (Reservation r : reservations) {
            System.out.println("Customer: " + r.customerName +
                    " | Room No: " + r.room.roomNumber +
                    " | Category: " + r.room.category);
        }
    }
}


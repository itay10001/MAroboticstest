import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Airline airline = new Airline("MARKIA AIR", 50);

        int choice = -1;

        while (choice != 0) {
            printMenu();
            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                addFlight(input, airline);
            } else if (choice == 2) {
                addPassengerToFlight(input, airline);
            } else if (choice == 3) {
                airline.printAllFlights();
            } else if (choice == 4) {
                searchPassenger(input, airline);
            } else if (choice == 5) {
                movePassenger(input, airline);
            } else if (choice == 6) {
                printFullestFlight(airline);
            } else if (choice == 7) {
                printSeatMap(input, airline);
            } else if (choice == 0) {
                System.out.println("Goodbye from MARKIA AIR.");
            } else {
                System.out.println("Invalid choice. Please choose again.");
            }
        }

        input.close();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=== MARKIA AIR ===");
        System.out.println("1. Add new flight");
        System.out.println("2. Add passenger to flight");
        System.out.println("3. Show all flights");
        System.out.println("4. Search passenger by passport");
        System.out.println("5. Move passenger between flights");
        System.out.println("6. Show fullest flight");
        System.out.println("7. Bonus: show seat map");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    public static void addFlight(Scanner input, Airline airline) {
        System.out.print("Flight number: ");
        String number = input.nextLine();

        System.out.print("Origin: ");
        String origin = input.nextLine();

        System.out.print("Destination: ");
        String destination = input.nextLine();

        System.out.print("Capacity: ");
        int capacity = input.nextInt();
        input.nextLine();

        Flight flight = new Flight(number, origin, destination, capacity);

        if (airline.addFlight(flight)) {
            System.out.println("Flight added successfully.");
        } else {
            System.out.println("Could not add flight.");
        }
    }

    public static void addPassengerToFlight(Scanner input, Airline airline) {
        System.out.print("Flight number: ");
        String flightNumber = input.nextLine();

        Flight flight = airline.getFlightByNumber(flightNumber);

        if (flight == null) {
            System.out.println("Flight was not found.");
        } else {
            System.out.print("Passenger full name: ");
            String name = input.nextLine();

            System.out.print("Passport id: ");
            String passport = input.nextLine();

            System.out.print("Age: ");
            int age = input.nextInt();

            System.out.print("Luggage weight: ");
            double luggageWeight = input.nextDouble();
            input.nextLine();

            Passenger passenger = new Passenger(name, passport, age, luggageWeight);

            if (flight.addPassenger(passenger)) {
                System.out.println("Passenger added successfully.");
            } else {
                System.out.println("Could not add passenger. The flight may be full or passport may already exist.");
            }
        }
    }

    public static void searchPassenger(Scanner input, Airline airline) {
        System.out.print("Passport id: ");
        String passport = input.nextLine();

        Passenger passenger = airline.findPassenger(passport);
        Flight flight = airline.getFlightForPassenger(passport);

        if (passenger == null) {
            System.out.println("Passenger was not found.");
        } else {
            System.out.println(passenger);
            System.out.println("Flight: " + flight.getFlightNumber());
        }
    }

    public static void movePassenger(Scanner input, Airline airline) {
        System.out.print("Passport id: ");
        String passport = input.nextLine();

        System.out.print("From flight: ");
        String fromFlight = input.nextLine();

        System.out.print("To flight: ");
        String toFlight = input.nextLine();

        if (airline.movePassenger(passport, fromFlight, toFlight)) {
            System.out.println("Passenger moved successfully.");
        }
    }

    public static void printFullestFlight(Airline airline) {
        Flight fullest = airline.getFullestFlight();

        if (fullest == null) {
            System.out.println("No flights yet.");
        } else {
            System.out.println("Fullest flight:");
            System.out.println(fullest);
        }
    }

    public static void printSeatMap(Scanner input, Airline airline) {
        System.out.print("Flight number: ");
        String flightNumber = input.nextLine();

        Flight flight = airline.getFlightByNumber(flightNumber);

        if (flight == null) {
            System.out.println("Flight was not found.");
        } else {
            flight.printSeatMap();
        }
    }
}

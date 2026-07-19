// Name: Gilad | Date: 2026-07-09 | Manages all flights for the airline.
public class Airline {
    private String name;
    private Flight[] flights;
    private int flightCount;

    public Airline(String name, int maxFlights) {
        this.name = name;

        if (maxFlights <= 0) {
            System.out.println("Invalid max flights. Default max flights 1 was used.");
            maxFlights = 1;
        }

        flights = new Flight[maxFlights];
        flightCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getFlightCount() {
        return flightCount;
    }

    public boolean addFlight(Flight flight) {
        if (flight == null) {
            return false;
        }

        if (flightCount == flights.length) {
            return false;
        }

        if (getFlightByNumber(flight.getFlightNumber()) != null) {
            return false;
        }

        flights[flightCount] = flight;
        flightCount++;
        return true;
    }

    public Flight getFlightByNumber(String number) {
        if (number == null) {
            return null;
        }

        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getFlightNumber().equals(number)) {
                return flights[i];
            }
        }

        return null;
    }

    public void printFlightsTo(String destination) {
        boolean found = false;

        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getDestination().equals(destination)) {
                System.out.println(flights[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No flights found to " + destination + ".");
        }
    }

    public Flight getFullestFlight() {
        if (flightCount == 0) {
            return null;
        }

        Flight fullest = flights[0];

        for (int i = 1; i < flightCount; i++) {
            if (flights[i].getOccupancyPercent() > fullest.getOccupancyPercent()) {
                fullest = flights[i];
            }
        }

        return fullest;
    }

    public boolean movePassenger(String passportId, String fromFlightNumber, String toFlightNumber) {
        Flight fromFlight = getFlightByNumber(fromFlightNumber);
        Flight toFlight = getFlightByNumber(toFlightNumber);

        if (fromFlight == null) {
            System.out.println("Move failed: source flight was not found.");
            return false;
        }

        if (toFlight == null) {
            System.out.println("Move failed: destination flight was not found.");
            return false;
        }

        if (fromFlight == toFlight) {
            System.out.println("Move failed: source and destination are the same.");
            return false;
        }

        Passenger passenger = fromFlight.findPassenger(passportId);
        if (passenger == null) {
            System.out.println("Move failed: passenger was not found.");
            return false;
        }

        if (toFlight.isFull()) {
            System.out.println("Move failed: destination flight is full.");
            return false;
        }

        if (toFlight.findPassenger(passportId) != null) {
            System.out.println("Move failed: passenger already exists on destination flight.");
            return false;
        }

        fromFlight.removePassenger(passportId);
        toFlight.addPassenger(passenger);
        return true;
    }

    public Passenger findPassenger(String passportId) {
        for (int i = 0; i < flightCount; i++) {
            Passenger passenger = flights[i].findPassenger(passportId);

            if (passenger != null) {
                return passenger;
            }
        }

        return null;
    }

    public Flight getFlightForPassenger(String passportId) {
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].findPassenger(passportId) != null) {
                return flights[i];
            }
        }

        return null;
    }

    public void printAllFlights() {
        if (flightCount == 0) {
            System.out.println("No flights yet.");
        } else {
            for (int i = 0; i < flightCount; i++) {
                System.out.println((i + 1) + ". " + flights[i]);
            }
        }
    }
}

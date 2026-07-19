// Name: Gilad | Date: 2026-07-09 | Manages one flight and its passengers.
public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int maxCapacity;
    private Passenger[] passengers;
    private int passengerCount;

    public Flight(String flightNumber, String origin, String destination, int maxCapacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;

        if (maxCapacity <= 0) {
            System.out.println("Invalid capacity. Default capacity 1 was used.");
            this.maxCapacity = 1;
        } else {
            this.maxCapacity = maxCapacity;
        }

        passengers = new Passenger[this.maxCapacity];
        passengerCount = 0;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean addPassenger(Passenger passenger) {
        if (passenger == null) {
            return false;
        }

        if (isFull()) {
            return false;
        }

        if (findPassenger(passenger.getPassportId()) != null) {
            return false;
        }

        passengers[passengerCount] = passenger;
        passengerCount++;
        return true;
    }

    public boolean isFull() {
        return passengerCount == maxCapacity;
    }

    public double getOccupancyPercent() {
        return (double) passengerCount / maxCapacity * 100;
    }

    public Passenger findPassenger(String passportId) {
        for (int i = 0; i < passengerCount; i++) {
            if (passengers[i].getPassportId().equals(passportId)) {
                return passengers[i];
            }
        }

        return null;
    }

    public boolean removePassenger(String passportId) {
        int indexToRemove = -1;

        for (int i = 0; i < passengerCount; i++) {
            if (passengers[i].getPassportId().equals(passportId)) {
                indexToRemove = i;
            }
        }

        if (indexToRemove == -1) {
            return false;
        }

        for (int i = indexToRemove; i < passengerCount - 1; i++) {
            passengers[i] = passengers[i + 1];
        }

        passengers[passengerCount - 1] = null;
        passengerCount--;
        return true;
    }

    public double getTotalLuggageWeight() {
        double total = 0;

        for (int i = 0; i < passengerCount; i++) {
            total = total + passengers[i].getLuggageWeight();
        }

        return total;
    }

    public void printPassengers() {
        if (passengerCount == 0) {
            System.out.println("No passengers on this flight.");
        } else {
            for (int i = 0; i < passengerCount; i++) {
                System.out.println((i + 1) + ". " + passengers[i]);
            }
        }
    }

    public void printSeatMap() {
        System.out.println("Seat map for flight " + flightNumber + ":");

        for (int i = 0; i < maxCapacity; i++) {
            int row = i / 4 + 1;
            char letter = (char) ('A' + i % 4);

            if (i < passengerCount) {
                System.out.print(row + "" + letter + "[X] ");
            } else {
                System.out.print(row + "" + letter + "[ ] ");
            }

            if (i % 4 == 3) {
                System.out.println();
            }
        }

        System.out.println();
    }

    public String toString() {
        return "Flight " + flightNumber
                + ": " + origin + " -> " + destination
                + ", passengers: " + passengerCount + "/" + maxCapacity
                + ", occupancy: " + (int) getOccupancyPercent() + "%"
                + ", luggage: " + getTotalLuggageWeight() + "kg";
    }
}

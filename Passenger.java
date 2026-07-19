public class Passenger {
    private String fullName;
    private String passportId;
    private int age;
    private double luggageWeight;

    public Passenger(String fullName, String passportId, int age, double luggageWeight) {
        this.fullName = fullName;
        this.passportId = passportId;
        setAge(age);
        setLuggageWeight(luggageWeight);
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassportId() {
        return passportId;
    }

    public int getAge() {
        return age;
    }

    public double getLuggageWeight() {
        return luggageWeight;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        if (age < 0 || age > 120) {
            System.out.println("Invalid age. Default age 0 was used.");
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public void setLuggageWeight(double luggageWeight) {
        if (luggageWeight < 0 || luggageWeight > 32) {
            System.out.println("Invalid luggage weight. Default luggage weight 0 was used.");
            this.luggageWeight = 0;
        } else {
            this.luggageWeight = luggageWeight;
        }
    }

    public boolean isOverweight() {
        return luggageWeight > 23;
    }

    public String toString() {
        return "Name: " + fullName
                + ", Passport: " + passportId
                + ", Age: " + age
                + ", Luggage: " + luggageWeight + "kg"
                + ", Overweight: " + isOverweight();
    }
}

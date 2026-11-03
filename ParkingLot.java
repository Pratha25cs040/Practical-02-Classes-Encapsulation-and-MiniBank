public class ParkingLot {
    private int twoWheels;
    private int fourWheels;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = Math.max(0, twoCap);
        this.fourCap = Math.max(0, fourCap);
        this.twoWheels = 0;
        this.fourWheels = 0;
    }

    public boolean park(String type) {
        if (type == null) {
            System.out.println("Invalid vehicle type.");
            return false;
        }

        switch (type.trim().toLowerCase()) {
            case "two" -> {
                if (twoWheels < twoCap) {
                    twoWheels++;
                    revenue += 20;
                    System.out.println("Two-wheeler parked.");
                    return true;
                }
            }
            case "four" -> {
                if (fourWheels < fourCap) {
                    fourWheels++;
                    revenue += 40;
                    System.out.println("Four-wheeler parked.");
                    return true;
                }
            }
            default -> {
                System.out.println("Invalid vehicle type. Use two or four.");
                return false;
            }
        }

        System.out.println("Full");
        return false;
    }

    public void leave(String type) {
        if (type == null) {
            return;
        }

        switch (type.trim().toLowerCase()) {
            case "two" -> {
                if (twoWheels > 0) {
                    twoWheels--;
                    System.out.println("Two-wheeler left.");
                } else {
                    System.out.println("No two-wheeler to remove.");
                }
            }
            case "four" -> {
                if (fourWheels > 0) {
                    fourWheels--;
                    System.out.println("Four-wheeler left.");
                } else {
                    System.out.println("No four-wheeler to remove.");
                }
            }
            default -> System.out.println("Invalid vehicle type. Use two or four.");
        }
    }

    public static long getRevenue() {
        return revenue;
    }

    public int getTwoWheels() {
        return twoWheels;
    }

    public int getFourWheels() {
        return fourWheels;
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 2);

        System.out.println("Parking simulation:");
        lot.park("two");
        lot.park("two");
        lot.park("two"); // Rejected because the two-wheeler section is full.
        lot.park("four");
        lot.park("four");
        lot.park("four"); // Rejected because the four-wheeler section is full.

        lot.leave("two");
        lot.park("two");

        lot.leave("four");
        lot.leave("four");
        lot.leave("four"); // Count cannot become negative.

        System.out.println("\\nFinal two-wheelers: " + lot.getTwoWheels());
        System.out.println("Final four-wheelers: " + lot.getFourWheels());
        System.out.println("Total revenue: " + ParkingLot.getRevenue());
    }
}

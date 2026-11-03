public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;

    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = Math.max(0, capacity);
        this.seatsAvailable = this.capacity;
    }

    public CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= 0) {
            System.out.println("Booking quantity must be positive.");
            return false;
        }

        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }

        return false;
    }

    public void cancel(int n) {
        if (n <= 0) {
            System.out.println("Cancellation quantity must be positive.");
            return;
        }

        seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("The Java Adventure", 50);

        System.out.println("Show: " + show.title);
        System.out.println("Initial seats: " + show.getSeatsAvailable());

        boolean first = show.book(20);
        System.out.println("Book 20: " + first + " | Seats available: " + show.getSeatsAvailable());

        boolean second = show.book(40);
        System.out.println("Book 40: " + second + " | Seats available: " + show.getSeatsAvailable());

        boolean third = show.book(15);
        System.out.println("Book 15: " + third + " | Seats available: " + show.getSeatsAvailable());

        show.cancel(10);
        System.out.println("Cancel 10 | Seats available: " + show.getSeatsAvailable());

        boolean fourth = show.book(10);
        System.out.println("Book 10: " + fourth + " | Seats available: " + show.getSeatsAvailable());

        show.cancel(100);
        System.out.println("Cancel 100 (capped at capacity) | Seats available: " + show.getSeatsAvailable());

        System.out.println("Total successfully booked: " + CinemaShow.getTotalBooked());
    }
}

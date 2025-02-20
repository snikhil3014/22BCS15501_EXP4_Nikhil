import java.util.concurrent.*;

class TicketBookingSystem {
    private final boolean[] seats;
    private final Object lock = new Object();

    public TicketBookingSystem(int seatCount) {
        seats = new boolean[seatCount];
    }

    public boolean bookSeat(int seatNumber, String customer) {
        synchronized (lock) {
            if (seatNumber < 0 || seatNumber >= seats.length || seats[seatNumber]) {
                return false;
            }
            seats[seatNumber] = true;
            System.out.println(customer + " successfully booked seat " + seatNumber);
            return true;
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customer;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customer, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customer = customer;
        setPriority(priority);
    }

    public void run() {
        if (!system.bookSeat(seatNumber, customer)) {
            System.out.println(customer + " failed to book seat " + seatNumber + ". Already taken.");
        }
    }
}

public class TicketBookingDemo {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.execute(new BookingThread(system, 2, "VIP Customer 1", Thread.MAX_PRIORITY));
        executor.execute(new BookingThread(system, 2, "Regular Customer 1", Thread.MIN_PRIORITY));
        executor.execute(new BookingThread(system, 5, "VIP Customer 2", Thread.MAX_PRIORITY));
        executor.execute(new BookingThread(system, 5, "Regular Customer 2", Thread.MIN_PRIORITY));
        executor.execute(new BookingThread(system, 7, "Regular Customer 3", Thread.NORM_PRIORITY));

        executor.shutdown();
    }
}
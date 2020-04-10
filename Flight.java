import java.util.*;

/**
 * flight is the base class for all the types of flights
 */
public class Flight {

    // Data Members

    // unique id for each flight
    private String id;

    public static String stops[] = { "AUS", "IND", "ITA", "USA" };
    // total number of seats
    private int eSize;
    private int bSize;
    private int fSize;
    int row, bRow, fRow;
    int col, bcol, fcol;

    // starting point of flight
    private String from;

    // end point of flight
    private String to;

    private ArrayList<String> pnrList;

    public HashMap<String, Passenger> eReservations;
    public HashMap<String, Passenger> bReservations;
    public HashMap<String, Passenger> fReservations;

    public HashMap<String, Integer> count;

    // constructor
    Flight(String id, String from, String to, int eSize, int bSize, int fSize) {
        this.id = id;
        this.from = from;
        this.to = to;

        this.eSize = eSize;

        if (bSize >= 20 && bSize <= 40) {
            this.bSize = bSize;
        } else {
            this.bSize = 20;
        }

        this.fSize = fSize;

        this.eReservations = new HashMap<String, Passenger>();
        this.bReservations = new HashMap<String, Passenger>();
        this.fReservations = new HashMap<String, Passenger>();

        this.count = new HashMap<String, Integer>();
        this.count.put("eco", 0);
        this.count.put("bus", 0);
        this.count.put("fir", 0);
        this.row = 1;
        this.bRow = 0;
        this.fRow = 0;
        col = bcol = fcol = 0;
    }

    // GETTERS

    public void getDetails() {
        System.out.println("Flight " + id + ":");
        System.out.println(from + "-->" + to);
        System.out.println("Seats available:");
        System.out.println("Economy :" + (eSize - count.get("eco")));
        System.out.println("Business :" + (eSize - count.get("bus")));
        System.out.println("First-Class :" + (eSize - count.get("fir")));
    }

    public String getId() {
        return id;
    }

    public int getESize() {
        return eSize;
    }

    public int getBSize() {
        return bSize;
    }

    public int getFSize() {
        return fSize;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    // SETTERS
    public void setId(String i) {
        id = i;
    }

    public void setESize(int s) {
        eSize = s;
    }

    public void setBSize(int s) {
        bSize = s;
    }

    public void setFSize(int s) {
        fSize = s;
    }

    public void setFrom(String f) {
        from = f;
    }

    public void setTo(String t) {
        to = t;
    }
}

/**
 * Passenger
 */
public class Passenger {
    String firstName;
    String lastName;
    String sex;
    int age;
    Flight flight;
    String seatNum;
    String seatType;
    int baggageLimit;
    String pnr;

    Passenger(String f, String l, String s, int a) {
        this.firstName = f;
        this.lastName = l;
        this.sex = s;
        this.age = a;
    }

    public void setSeatNumber(String seat) {
        this.seatNum = seat;
    }

    public void setBaggeLimit(int baggageLimit) {
        this.baggageLimit = baggageLimit;
    }

    public void setSeatType(String type) {
        if (type.equals("eco"))
            this.seatType = "economy".toUpperCase();
        else if (type.equals("bus"))
            this.seatType = "business-class".toUpperCase();
        else if (type.equals("fir"))
            this.seatType = "first-class".toUpperCase();
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public void getSummary() {
        System.out.print(ConsoleColors.GREEN_BOLD);
        System.out.println("+---------------------------------------------+");
        System.out.println("               Flight:" + flight.getId());
        System.out.println("+---------------------------------------------+");
        System.out.println("               " + flight.getFrom() + "-->" + flight.getTo());
        System.out.println(" PNR:" + pnr + "\t\tType:" + seatType);
        System.out.println(" Name" + firstName + " " + lastName + "\tAge:" + age);
        System.out.println(" Sex:" + sex + "\t\t\tSeat:" + seatNum);
        System.out.println("+---------------------------------------------+");
        System.out.println(ConsoleColors.RESET);
    }
}
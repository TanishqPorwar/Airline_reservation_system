import java.util.*;
import java.io.*;

/**
 * sys, the actual airline reservation system
 */

public class sys {
    public static void main(String[] args) throws IOException, InterruptedException {
        // method to clear the console
        clearScreen();
        Scanner sc = new Scanner(System.in);

        // create a List flight objects.
        ArrayList<Flight> flights = createFlights();
        int choice;
        System.out.print("\n" + ConsoleColors.RED + "WELCOME TO " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "AIR ASIA!" + ConsoleColors.RESET + "\n");

        do {
            choice = showMainMenu(sc);
            switch (choice) {
                case 1:
                    bookTicket(sc, flights);
                    break;
                case 2:
                    cancelTicket(sc);
                    break;
                case 3:
                    buyBaggage(sc);
                    break;
                case 4:
                    getBaggage(sc);
                    break;
                case 5:
                    getDetails(sc);
                    break;
                case 6:
                    System.out.print(ConsoleColors.CYAN);
                    System.out.println("Thank you for choosing AIR ASIA, hope to see you again!!");
                    System.out.print(ConsoleColors.RESET);
                    return;
                default:
                    break;
            }
        } while (choice != 6);

        sc.close();
    }

    // method to get passanger trip details
    private static void getDetails(Scanner sc) throws IOException, InterruptedException {
        clearScreen();
        System.out.print(ConsoleColors.YELLOW + "Enter PNR: " + ConsoleColors.RESET);
        String pnr = sc.next();
        if (Flight.reservations.get(pnr.toUpperCase()) == null) {
            System.out.println(ConsoleColors.YELLOW + "Invalid PNR! Can't get baggage limit" + ConsoleColors.RESET);
            return;
        } else {
            Passenger p = Flight.reservations.get(pnr.toUpperCase());
            p.getSummary();
        }
    }

    // method to get baggage limit
    private static void getBaggage(Scanner sc) throws IOException, InterruptedException {
        clearScreen();
        System.out.print(ConsoleColors.YELLOW + "Enter PNR: " + ConsoleColors.RESET);
        String pnr = sc.next();
        getBaggageLimit(pnr);
    }

    // method to get baggage limit by pnr
    private static void getBaggageLimit(String pnr) {
        if (Flight.reservations.get(pnr.toUpperCase()) == null) {
            System.out.println(ConsoleColors.YELLOW + "Invalid PNR! Can't get baggage limit" + ConsoleColors.RESET);
            return;
        } else {
            Passenger p = Flight.reservations.get(pnr.toUpperCase());
            System.out.println("Welcome " + p.firstName);
            System.out.println("Current Baggage Limit:" + p.baggageLimit);
        }
    }

    // method to buy excess baggage
    private static void buyBaggage(Scanner sc) throws IOException, InterruptedException {
        clearScreen();
        System.out.print(ConsoleColors.YELLOW + "Enter PNR: " + ConsoleColors.RESET);
        String pnr = sc.next();
        buyExcessBaggage(pnr, sc);
    }

    // the method looks for passanger with pnr and buys Baggage by
    // calling polymorphic method buyExcessBaggage
    private static void buyExcessBaggage(String pnr, Scanner sc) {
        if (Flight.reservations.get(pnr.toUpperCase()) == null) {
            System.out.println(ConsoleColors.YELLOW + "Invalid PNR! Can't buy baggage" + ConsoleColors.RESET);
            return;
        } else {
            Passenger p = Flight.reservations.get(pnr.toUpperCase());
            System.out.println("Welcome " + p.firstName);
            System.out.println("Current Baggage Limit:" + p.baggageLimit);
            int r = 0, i = 0;
            if (p.seatType.equalsIgnoreCase("ECONOMY")) {
                System.out.println("You can buy upto 20Kgs, by paying Rs.2000/Kg");
                System.out.print(ConsoleColors.YELLOW + "Enter number of additional Kgs: " + ConsoleColors.RESET);
                r = sc.nextInt();
                i = 1;
            } else if (p.seatType.equalsIgnoreCase("BUSINESS-CLASS")) {
                System.out.println("You can buy upto 35Kgs, by paying Rs.3000/Kg");
                System.out.print(ConsoleColors.YELLOW + "Enter number of additional Kgs: " + ConsoleColors.RESET);
                r = sc.nextInt();
                i = 2;
            } else if (p.seatType.equalsIgnoreCase("FIRST-CLASS")) {
                System.out.println("You can buy upto 40Kgs, by paying Rs.4000/Kg");
                System.out.print(ConsoleColors.YELLOW + "Enter number of additional Kgs: " + ConsoleColors.RESET);
                r = sc.nextInt();
                i = 3;
            }
            if (buyExcessBaggage(r, i, p, sc))
                System.out.println("Current Baggage Limit:" + p.baggageLimit);
            return;
        }
    }

    // polymorphic method buyyExcessBaggage which updates
    // the baggage limit of Passenger p
    private static boolean buyExcessBaggage(int r, int i, Passenger p, Scanner sc) {
        if (i == 1) {
            if (r <= (20 - p.baggageLimit)) {
                if (makePayment(r, 1, sc)) {
                    p.baggageLimit += r;
                    return true;
                } else {
                    System.out.println("Transaction falied");
                }
            } else
                System.out.println("You can only buy " + (20 - p.baggageLimit) + "Kgs");
        } else if (i == 2) {
            if (r <= (35 - p.baggageLimit)) {
                if (makePayment(r, 2, sc)) {
                    p.baggageLimit += r;
                    return true;
                } else {
                    System.out.println("Transaction falied");
                }
            } else
                System.out.println("You can only buy " + (35 - p.baggageLimit) + "Kgs");
        } else if (i == 3) {
            if (r <= (40 - p.baggageLimit)) {
                if (makePayment(r, 3, sc)) {
                    p.baggageLimit += r;
                    return true;
                } else {
                    System.out.println("Transaction falied");
                }
            } else
                System.out.println("You can only buy " + (40 - p.baggageLimit) + "Kgs");
        }
        return false;
    }

    // method to get the payment amount based on the weight to be bought
    private static boolean makePayment(int r, int i, Scanner sc) {
        int baggagePrice;
        String temp;
        if (i == 1) {
            baggagePrice = r * 2000;
            System.out.println("Bill Amount Rs." + baggagePrice + " .Enter Y to Proceed or N to cancel:");
            temp = sc.next();
            if (temp.equalsIgnoreCase("Y"))
                return true;
        } else if (i == 2) {
            baggagePrice = r * 3000;
            System.out.println("Bill Amount Rs." + baggagePrice + " .Enter Y to Proceed or N to cancel:");
            temp = sc.next();
            if (temp.equalsIgnoreCase("Y"))
                return true;
        } else if (i == 3) {
            baggagePrice = r * 4000;
            System.out.println("Bill Amount Rs." + baggagePrice + " .Enter Y to Proceed or N to cancel:");
            temp = sc.next();
            if (temp.equalsIgnoreCase("Y"))
                return true;
        }
        return false;
    }

    // method to cancel tickets
    private static void cancelTicket(Scanner sc) throws IOException, InterruptedException {
        clearScreen();
        System.out.print(ConsoleColors.YELLOW + "Enter PNR: " + ConsoleColors.RESET);
        String pnr = sc.next();
        if (Flight.reservations.get(pnr.toUpperCase()) == null) {
            System.out.println(ConsoleColors.YELLOW + "Invalid PNR! No tickets found" + ConsoleColors.RESET);
            return;
        } else {
            Passenger remP = Flight.reservations.remove(pnr.toUpperCase());
            if (remP.seatType == "ECONOMY") {
                remP.flight.count.put("eco", remP.flight.count.get("eco") - 1);
            } else if (remP.seatType == "BUSINESS-CLASS") {
                remP.flight.count.put("fir", remP.flight.count.get("fir") - 1);
            } else if (remP.seatType == "FIRST-CLASS") {
                remP.flight.count.put("fir", remP.flight.count.get("fir") - 1);
            }
            System.out.println(remP.firstName + "'s tickect was cancelled");
            return;
        }

    }

    // method to book ticket
    private static void bookTicket(Scanner sc, ArrayList<Flight> flights) throws IOException, InterruptedException {
        clearScreen();
        int flag = 0;
        System.out.print("We connect :+\n[ ");
        for (String s : Flight.stops) {
            System.out.print(s + " ");
        }
        System.out.print("]\n");
        String from, to, id;
        System.out.println("Please fill the details");
        System.out.print(ConsoleColors.YELLOW + "Boarding from: " + ConsoleColors.RESET);
        from = sc.next();
        System.out.print(ConsoleColors.YELLOW + "Going to: " + ConsoleColors.RESET);
        to = sc.next();
        for (Flight flight : flights) {
            if (flight.getFrom().equalsIgnoreCase(from)) {
                if (flight.getTo().equalsIgnoreCase(to)) {
                    flag += 1;
                    System.out.print(ConsoleColors.PURPLE);
                    flight.getDetails();
                    System.out.print(ConsoleColors.RESET);
                }
                System.out.println();
            }
        }
        if (flag == 0) {
            System.out.println("Sorry!! no flights available");
            return;
        }
        Flight flight;
        do {
            System.out.print(ConsoleColors.YELLOW + "Enter flight id: " + ConsoleColors.RESET);
            id = sc.next();
            flight = getFlightByID(flights, id);
        } while (flight == null);
        System.out.println("You have selected: ");
        flight.getDetails();
        int ch;
        flag = 0;
        String seat = "";
        String type = "";
        int baggageLimit = 0;
        do {
            ch = showSeatMenu(sc);
            switch (ch) {
                case 1:
                    if ((flight.getESize() - flight.count.get("eco")) <= 0) {
                        System.out.println("Sorry no seats available");
                        flag = 0;
                    } else {
                        flight.count.put("eco", flight.count.get("eco") + 1);
                        seat = flight.row + getSeatFromcol(flight, "eco");
                        type = "eco";
                        baggageLimit = 15;
                        flag = 1;
                    }
                    ch = 780;
                    break;

                case 2:
                    if ((flight.getBSize() - flight.count.get("bus")) <= 0) {
                        System.out.println("Sorry no seats available");
                        flag = 0;
                    } else {
                        flight.count.put("bus", flight.count.get("bus") + 1);
                        seat = flight.bRow + getSeatFromcol(flight, "bus");
                        type = "bus";
                        baggageLimit = 25;
                        flag = 1;
                    }
                    ch = 780;
                    break;
                case 3:
                    if ((flight.getFSize() - flight.count.get("fir")) <= 0) {
                        System.out.println("Sorry no seats available");
                        flag = 0;
                    } else {
                        flight.count.put("fir", flight.count.get("fir") + 1);
                        seat = flight.fRow + getSeatFromcol(flight, "fir");
                        type = "fir";
                        baggageLimit = 30;
                        flag = 1;
                    }
                    ch = 780;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (ch != 780);
        if (flag == 0)
            return;
        System.out.println(ConsoleColors.YELLOW + "Thank you for choosing AIR ASIA. Please Enter your details"
                + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "Enter First Name: " + ConsoleColors.RESET);
        String fname = sc.next();
        System.out.print(ConsoleColors.YELLOW + "Enter Last Name: " + ConsoleColors.RESET);
        String lname = sc.next();
        System.out.print(ConsoleColors.YELLOW + "Enter Sex(M/F): " + ConsoleColors.RESET);
        String sex = sc.next();
        System.out.print(ConsoleColors.YELLOW + "Enter Age: " + ConsoleColors.RESET);
        int age = sc.nextInt();
        Passenger p = new Passenger(fname, lname, sex, age);
        p.setSeatNumber(seat);
        p.setSeatType(type);
        p.setBaggeLimit(baggageLimit);
        p.setFlight(flight);
        String pnr = flight.getPNR();
        p.setPnr(pnr);
        p.getSummary();
        Flight.reservations.put(pnr, p);
    }

    // assigns a seatnuber to from the current booking
    private static String getSeatFromcol(Flight flight, String st) {
        String s = "";
        if (st == "eco") {
            flight.col = (flight.col + 1) % 6;
            if (flight.col == 1) {
                s = "A";
            } else if (flight.col == 2) {
                s = "B";
            } else if (flight.col == 3) {
                s = "C";
            } else if (flight.col == 4) {
                s = "D";
            } else if (flight.col == 5) {
                s = "E";
            } else if (flight.col == 0) {
                s = "f";
                flight.row += 1;
            }
        } else if (st == "bus") {
            flight.bcol = (flight.bcol + 1) % 4;
            if (flight.bcol == 1) {
                s = "A";
            } else if (flight.bcol == 2) {
                s = "B";
            } else if (flight.bcol == 3) {
                s = "C";
            } else if (flight.bcol == 0) {
                s = "D";
                flight.bRow += 1;
            }
        } else if (st == "fir") {
            flight.fcol = (flight.fcol + 1) % 2;
            if (flight.fcol == 1) {
                s = "A";
            } else if (flight.fcol == 0) {
                s = "B";
                flight.fRow += 1;
            }
        }
        return s;
    }

    // a menu to choose the type of seat and returns the user choice
    private static int showSeatMenu(Scanner sc) {
        System.out.print(ConsoleColors.GREEN_BOLD);
        System.out.println("+------------------------+");
        System.out.println("|           Menu         |");
        System.out.println("+------------------------+");
        System.out.println("| 1. Economic            |");
        System.out.println("| 2. Business            |");
        System.out.println("| 3. First-Class         |");
        System.out.println("+------------------------+");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
        int x = sc.nextInt();
        return x;
    }

    // a method that searches the flights by id
    private static Flight getFlightByID(ArrayList<Flight> flights, String id) {
        for (Flight flight : flights) {
            if (flight.getId().equalsIgnoreCase(id)) {
                return flight;
            }
        }
        return null;
    }

    // display the main menu and returns the user choice
    private static int showMainMenu(Scanner sc) {
        System.out.print(ConsoleColors.GREEN_BOLD);
        System.out.println("+------------------------+");
        System.out.println("|           Menu         |");
        System.out.println("+------------------------+");
        System.out.println("| 1. Book Ticket         |");
        System.out.println("| 2. Cancel Tickets      |");
        System.out.println("| 3. Buy Excess Baggage  |");
        System.out.println("| 4. Get Baggage Limit   |");
        System.out.println("| 5. Get Booking Details |");
        System.out.println("| 6. Exit                |");
        System.out.println("+------------------------+");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
        int x = sc.nextInt();
        return x;
    }

    // create a list of flight objects
    private static ArrayList<Flight> createFlights() {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        flights.add(new Type1("T1-A100", "IND", "AUS", 40));
        flights.add(new Type1("T1-A200", "IND", "USA", 40));
        flights.add(new Type2("T2-B100", "AUS", "ITA", 30, 30));
        flights.add(new Type2("T2-B200", "IND", "AUS", 30, 36));
        flights.add(new Type3("T3-C100", "USA", "AUS", 40, 20));
        flights.add(new Type3("T3-C100", "ITA", "AUS", 20, 40));
        return flights;
    }

    // method to clear the output console screen
    private static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
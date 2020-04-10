
/**
 * Class for type1 flight, it inhertits form Flight class
 */
public class Type3 extends Flight {

    Type3(String id, String from, String to, int eSize, int bSize) {
        super(id, from, to, eSize, bSize, 20);
        this.fRow = 1;
        this.bRow = 11;
        this.row = 21;
    }

}

/**
 * Class for type1 flight, it inhertits form Flight class
 */
public class Type2 extends Flight {
    Type2(String id, String from, String to, int eSize, int bSize) {
        super(id, from, to, eSize, bSize, 0);
        this.bRow = 1;
        this.row = 11;
    }

    @Override
    public void setFSize(int s) {
        super.setFSize(0);
    }

}
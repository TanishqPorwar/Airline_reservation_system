
/**
 * Class for type1 flight, it inhertits form Flight class
 */
public class Type1 extends Flight {

    Type1(String id, String from, String to, int eSize) {
        super(id, from, to, eSize, 0, 0);
    }

    @Override
    public void setBSize(int s)  {
        super.setBSize(0);
    }

    @Override
    public void setFSize(int s)  {
        super.setFSize(0);
    }

}
import java.util.HashMap;

/**
 * test
 */
public class test {

    public static void main(String[] args) {
        HashMap<Integer, String> a = new HashMap<Integer, String>();
        a.put(1, "abc");
        a.put(2, "def");
        a.put(3, "ghi");
        a.put(4, "jkl");
        System.out.println(a.get(6));
    }
}

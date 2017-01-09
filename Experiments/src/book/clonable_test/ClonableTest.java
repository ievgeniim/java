package book.clonable_test;

/**
 * Created by lagger on 09.01.2017.
 */
public class ClonableTest {
    public static void main (String args[]) {

        Apartment flat1 = new Apartment(1,3,"Home");
        Apartment flat2 = new Apartment(1,3,"Home");

        if (flat1.equals(flat2)) {
            System.out.print("Equals");
        } else  {
            System.out.print("Not equals");
        }

        if (flat1.equals(new String("flat3"))) {

        }
    }
}

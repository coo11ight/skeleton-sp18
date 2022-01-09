import java.math.*;

public class TestPlanet{

    public TestPlanet(){
    }

    public static void main(String[] args){
        CheckPlanet();
    }

/**
 *  Checks whether or not two Doubles are equal and prints the result.
 *
 *  @param  expected    Expected double
 *  @param  actual      Double received
 *  @param  label       Label for the 'test' case
 */
    private static void checkEquals(double expected, double actual, String label) {
        if (expected == actual) {
        System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
        System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }
/**
*  Rounds a double value to a number of decimal places.
*
*  @param  value   Double to be rounded.
*  @param  places  Integer number of places to round VALUE to.
*/
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void CheckPlanet() {
        System.out.println("Checking Planet class...");

        Planet p1 = new Planet(4, -3, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(0.0, 0.0, 3.0, 4.0, 1e12D, "jupiter.gif");

        Planet p3 = new Planet(-4, 3, 3.0, 4.0, 15.0, "jupiter.gif");

        Planet[] planets = {p1, p2, p3};

        double xNetForce = p2.calcNetForceExertedByX(planets);
        double yNetForce = p2.calcNetForceExertedByY(planets);

        checkEquals(-21.344, round(xNetForce, 3), "calcNetForceExertedByX()");
        checkEquals(16.008, round(yNetForce, 3), "calcNetForceExertedByY()");

        System.out.println("Running test again, but with array that contains the target planet.");




    }




}
/**
 * 1.
 * Create java class
 * declare the int global variable
 * access the global variable in the main func by creating object
 * if (value > 10) { // output as num is greater 10 }
 * else { // output as num is lesser 10 }
 */

package wk4.sep29;

public class GlobalInt {

    // create variable
    int myInt = 1;

    public static void main(String[] args) {

        GlobalInt myObj = new GlobalInt();

        // access variable
        System.out.println(myObj.myInt);

        // if.. else
        int value = myObj.myInt;
        if (value > 10){
            System.out.println("Value is more than 10");
        } else {
            System.out.println("Value is less than 10");
        }
    }
}

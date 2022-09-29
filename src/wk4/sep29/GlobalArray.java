/**
 * 2.
 * Create java class
 * declare the string global array and long array
 * access the global variable in the main func by creating object
 * iterate the global arrays and show the output
 */

package wk4.sep29;

import java.util.Arrays;

public class GlobalArray {

    // create the variables
    String[] myStringArray = {"apple", "orange", "pear", "banana"};
    long[] myLongArray = {1234, 4567, 7890, 2345};

    public static void main(String[] args) {

        GlobalArray myObj = new GlobalArray();

        // access the variables
        System.out.println(Arrays.toString(myObj.myStringArray));
        System.out.println(Arrays.toString(myObj.myLongArray));

        // iterate myStringArray
        for (String str: myObj.myStringArray){
            System.out.println(str);
        }

        // iterate myLongArray
        for (long lng: myObj.myLongArray){
            System.out.println(lng);
        }

    }
}

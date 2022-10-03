/**
 * Exception Management:
 * create array in the function.
 * pass an index to a function to check whether the array index is exist or not.
 * if index is not exist throw the error with custom Exception.
 */

package wk5.oct3;

public class ExceptionMgmt {
    public static boolean doesIndexExist(int[] myArray, int index) {
        try {
            int i = myArray[index];
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws CustomException {

        int[] myArray = {1, 2, 3, 4, 5};
        int index = 10;
        boolean validIndex = doesIndexExist(myArray, index);

        if (validIndex != true) {
            throw new CustomException("index does not exist!");
        } else {
             System.out.println("index exists!");
        }

    }
}

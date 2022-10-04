package wk5.oct4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductList {
    public static void main(String[] args) {

        ArrayList<Products> productsList = new ArrayList<Products>();

        Products product1 = new Products();
        product1.setName("apple");
        product1.setPrice(2.95);

        Products product2 = new Products();
        product2.setName("honeydew");
        product2.setPrice(7.40);

        Products product3 = new Products();
        product3.setName("mango");
        product3.setPrice(3.85);

        Products product4 = new Products();
        product4.setName("orange");
        product4.setPrice(3.45);

        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);

        // before sorting
        System.out.println("-- Before Sorting --");
        for (Products item:productsList){
            System.out.println(item.getName() + " $" + item.getPrice());
        }

        // after sorting
        System.out.println("-- After Sorting --");
        Collections.sort(productsList, new PriceComparator());
        for (Products item:productsList){
            System.out.println(item.getName() + " $" + item.getPrice());
        }
    }
}

class PriceComparator implements Comparator<Products>{

    public int compare (Products p1, Products p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }

}

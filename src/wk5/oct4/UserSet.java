package wk5.oct4;

import java.util.TreeSet;

public class UserSet {
    public static void main(String[] args) {
        TreeSet<Users> userSet = new TreeSet<Users>();

        Users user1 = new Users();
        user1.setName("Anne");
        user1.setAge(28);

        Users user2 = new Users();
        user2.setName("Eunice");
        user2.setAge(25);

        Users user3 = new Users();
        user3.setName("Charlie");
        user3.setAge(46);

        Users user4 = new Users();
        user4.setName("Beth");
        user4.setAge(35);

        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        userSet.add(user4);

        // Sort by name
        for(Users user:userSet){
            System.out.println(user.getName() + " - " + user.getAge());
        }

    }
}

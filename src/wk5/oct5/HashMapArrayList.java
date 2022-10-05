package wk5.oct5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapArrayList {

    public static void main(String[] args) {
        HashMap<String, ArrayList<User>> userList = new HashMap<String,ArrayList<User>>();

        ArrayList<User> female = new ArrayList<User>();

        User female1 = new User();
        female1.setName("Anne");
        female1.setAge(15);
        female1.setAddress("Kent Ridge");

        User female2 = new User();
        female2.setName("Kelly");
        female2.setAge(32);
        female2.setAddress("Dover");

        User female3 = new User();
        female3.setName("Beth");
        female3.setAge(28);
        female3.setAddress("Mountbatten");

        User female4 = new User();
        female4.setName("Ariel");
        female4.setAge(18);
        female4.setAddress("Eunos");

        female.add(female1);
        female.add(female2);
        female.add(female3);
        female.add(female4);

        ArrayList<User> male = new ArrayList<User>();

        User male1 = new User();
        male1.setName("Tom");
        male1.setAge(19);
        male1.setAddress("Lakeside");

        User male2 = new User();
        male2.setName("John");
        male2.setAge(36);
        male2.setAddress("Hillview");

        User male3 = new User();
        male3.setName("Damien");
        male3.setAge(17);
        male3.setAddress("Bedok");

        User male4 = new User();
        male4.setName("Alvin");
        male4.setAge(25);
        male4.setAddress("Woodlands");

        male.add(male1);
        male.add(male2);
        male.add(male3);
        male.add(male4);

        userList.put("female", female);
        userList.put("male", male);

        // iterate
        System.out.println("***** All users *****");

        for(String key:userList.keySet()){
            ArrayList<User> user = userList.get(key);
            System.out.println("-- "+key+" --");
            for (User person:user){
                System.out.println(person.getName()+" "+person.getAge()+" "+person.getAddress());
            }
        }

        // filter user whose age is below 20
        System.out.println("\n***** Users below 20 *****");

        for(String key:userList.keySet()){
            ArrayList<User> user = userList.get(key);
            List<User> below20 = user.stream().filter((p)->p.getAge()<20).collect(Collectors.toList());
            System.out.println("-- "+key+" --");
            for (User person:below20){
                System.out.println(person.getName()+" "+person.getAge()+" "+person.getAddress());
            }
        }

        // filter user whose name starts with 'A' and  return the first user
        System.out.println("\n***** First name that starts with A *****");

        for(String key:userList.keySet()){
            ArrayList<User> user = userList.get(key);
            List<User> startWithA = user.stream().filter((p)->p.getName().startsWith("A")).collect(Collectors.toList());
            User person = startWithA.stream().findFirst().get();
            System.out.println("-- "+key+" --");
            System.out.println(person.getName()+" "+person.getAge()+" "+person.getAddress());
        }

    }

}

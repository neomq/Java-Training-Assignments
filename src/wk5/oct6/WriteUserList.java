package wk5.oct6;

import java.util.ArrayList;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteUserList {

    public static void main(String[] args) {

        ArrayList<User> userList = new ArrayList<User>();

        User user1 = new User();
        user1.setName("Hayley");
        user1.setAddress("24 River Valley Close");

        User user2 = new User();
        user2.setName("Beth");
        user2.setAddress("1 Woodlands Square");

        User user3 = new User();
        user3.setName("Eugene");
        user3.setAddress("6001 Beach Road");

        User user4 = new User();
        user4.setName("Janice");
        user4.setAddress("Crawford Lane");

        User user5 = new User();
        user5.setName("Allen");
        user5.setAddress("111 North Bridge Road ");

        User user6 = new User();
        user6.setName("Samuel");
        user6.setAddress("5 Defu Lane");

        User user7 = new User();
        user7.setName("Clara");
        user7.setAddress("10 Admiralty Street");

        User user8 = new User();
        user8.setName("Kathleen");
        user8.setAddress("76 Westwood Avenue");

        User user9 = new User();
        user9.setName("Francis");
        user9.setAddress("81B Neil Road");

        User user10 = new User();
        user10.setName("Sophia");
        user10.setAddress("10 Anson Road");

        for (User user:new User[] {
                user1, user2, user3, user4, user5,
                user6, user7, user8, user9, user10
        }){
            userList.add(user);
        }

        // datetime
        LocalDateTime dateTime = LocalDateTime.now();
        String format ="mm-dd-yyyy HH:mm:ss";
        DateTimeFormatter DateTime = DateTimeFormatter.ofPattern(format);
        String formattedDt = dateTime.format(DateTime);

        // write data into text file
        try {
            String path = "/Users/minqi/Documents/IdeaProjects/Java-Training-Assignments/src/wk5/oct6/userdata.txt";
            FileOutputStream dataOut = new FileOutputStream(path, true);
            // iterate
            for (User user:userList){
                String data = user.getName() + " " + user.getAddress() + " " + formattedDt + "\n";
                dataOut.write(data.getBytes());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

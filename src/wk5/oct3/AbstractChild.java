package wk5.oct3;

public class AbstractChild extends Abstract implements PersonOne, PersonTwo {

    public void getName() {
        System.out.println("Name is John");
    }

    public void getAge() {
        int age = 40;
    }

    public static void main(String[] args) {
        // ... code execution
    }
}

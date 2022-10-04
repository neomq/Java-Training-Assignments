package wk5.oct4;

public class Users implements Comparable<Users> {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Users o) {
        return this.getName().compareTo(o.getName());
    }
}

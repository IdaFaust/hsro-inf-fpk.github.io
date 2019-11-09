package json;

import com.google.gson.Gson;

import java.util.Objects;

/**
 * Class to represent a person.
 */
public class Person implements JSONSerialiable {

    private String firstName;
    private String lastName;
    private int age;

    /**
     * Creates an instance of a person.
     * @param firstName
     * @param lastName
     * @param age
     */
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    /**
     * Lets test is.
     */
    public static void main(String[] args) throws Exception {
        Person p = new Person("Max", "Mustermann", 33);
        String s = p.toJson();
        System.out.println(s);

        Gson gson = new Gson();
        Person p2 = gson.fromJson(s, Person.class);
        System.out.println(p.equals(p2));
    }
}

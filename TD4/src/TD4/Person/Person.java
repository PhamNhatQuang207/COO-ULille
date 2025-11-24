package TD4.Person;
public class Person {
    private int age;
    private int height;
    private boolean prime;
    public Person(int age, int height, boolean prime) {
        this.age = age;
        this.height = height;
        this.prime = prime;
    }
    public int getAge() {
        return age;
    }
    public int getHeight() {
        return height;
    }
    public boolean isPrime() {
        return prime;
    }
}
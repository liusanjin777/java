package 修饰符;

public class Dog extends AbstractAnimal {
    @Override
    public void sound() {
        System.out.println("汪汪");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound();
        dog.eat();
    }
}

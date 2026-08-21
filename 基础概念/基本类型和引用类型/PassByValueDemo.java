package 基本类型和引用类型;

// 方法传参都是按值传递（传的是值的副本）
public class PassByValueDemo {
    public static void main(String[] args) {
        int num = 5;
        changePrimitive(num);
        System.out.println("方法调用后 num=" + num);

        Person person = new Person("小林");
        changeReference(person);
        System.out.println("方法调用后 person.name=" + person.name);
    }

    static void changePrimitive(int value) {
        value = 100;
    }

    static void changeReference(Person p) {
        // 副本和外部引用指向同一个对象，所以外部能看到修改
        p.name = "小明";
    }
}

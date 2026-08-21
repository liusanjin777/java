package 基本类型和引用类型;

// 数组、String、自定义类都是引用类型
public class ReferenceDemo {
    public static void main(String[] args) {
        // 引用类型变量未赋值时为 null
        String name = null;
        System.out.println("未赋值的引用=" + name);

        // 两个引用指向同一个数组对象，改 b 会反映到 a
        int[] a = {1, 2, 3};
        int[] b = a;
        b[0] = 100;
        System.out.println("a[0]=" + a[0] + "，b[0]=" + b[0]);

        // 引用类型变量存的是对象的引用，赋值只是复制引用
        Person p1 = new Person("小林");
        Person p2 = p1;
        System.out.println("p1 == p2 ? " + (p1 == p2));

        // == 比较引用是否指向同一个对象，equals 比较内容
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println("s1 == s2 ? " + (s1 == s2));
        System.out.println("s1.equals(s2) ? " + s1.equals(s2));
    }
}

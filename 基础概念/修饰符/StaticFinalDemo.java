package 修饰符;

public class StaticFinalDemo {
    public static int count = 0;
    public final String name;
    public static final double PI = 3.14;

    public StaticFinalDemo(String name) {
        this.name = name;
        count++;
    }

    public static void showCount() {
        System.out.println("共创建 " + count + " 个对象");
    }

    public static void main(String[] args) {
        StaticFinalDemo a = new StaticFinalDemo("A");
        StaticFinalDemo b = new StaticFinalDemo("B");

        // static 成员通过类名访问，不依赖对象
        StaticFinalDemo.showCount();
        System.out.println(StaticFinalDemo.PI);

        // final 字段只能赋值一次
        System.out.println(a.name + ", " + b.name);
    }
}

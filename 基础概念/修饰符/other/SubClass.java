package 修饰符.other;

import 修饰符.AccessDemo;

public class SubClass extends AccessDemo {
    public void useProtected() {
        // 不同包中的子类可以访问 protected 成员
        System.out.println(protectedField);
    }

    public static void main(String[] args) {
        new SubClass().useProtected();
    }
}

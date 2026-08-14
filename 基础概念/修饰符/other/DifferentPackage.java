package 修饰符.other;

import 修饰符.AccessDemo;

public class DifferentPackage {
    public static void main(String[] args) {
        AccessDemo demo = new AccessDemo();

        // 其他包、非子类：只有 public 可以直接访问
        System.out.println(demo.publicField);

        // 下面三行如果取消注释会编译报错
        // System.out.println(demo.protectedField);
        // System.out.println(demo.packageField);
        // System.out.println(demo.privateField);
    }
}

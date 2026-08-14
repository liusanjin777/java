package 修饰符;

public class SamePackage {
    public static void main(String[] args) {
        AccessDemo demo = new AccessDemo();

        // 同包内：public / protected / 默认都能直接访问
        System.out.println(demo.publicField);
        System.out.println(demo.protectedField);
        System.out.println(demo.packageField);

        // private 只有类内部能访问，这里通过公开方法读取
        System.out.println(demo.getPrivateField());
    }
}

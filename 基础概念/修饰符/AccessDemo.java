package 修饰符;

public class AccessDemo {
    public int publicField = 1;
    protected int protectedField = 2;
    int packageField = 3;
    private int privateField = 4;

    public void showAll() {
        System.out.println("public=" + publicField);
        System.out.println("protected=" + protectedField);
        System.out.println("package=" + packageField);
        System.out.println("private=" + privateField);
    }

    public int getPrivateField() {
        return privateField;
    }
}

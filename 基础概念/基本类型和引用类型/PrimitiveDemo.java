package 基本类型和引用类型;

// Java 的 8 种基本类型
public class PrimitiveDemo {
    public static void main(String[] args) {
        // 整数类型
        byte byteValue = 127;
        short shortValue = 32000;
        int intValue = 2000000000;
        long longValue = 9000000000L;

        // 浮点类型
        float floatValue = 3.14f;
        double doubleValue = 3.141592653589793;

        // 字符和布尔
        char charValue = 'A';
        boolean boolValue = true;

        System.out.println("byte=" + byteValue);
        System.out.println("short=" + shortValue);
        System.out.println("int=" + intValue);
        System.out.println("long=" + longValue);
        System.out.println("float=" + floatValue);
        System.out.println("double=" + doubleValue);
        System.out.println("char=" + charValue);
        System.out.println("boolean=" + boolValue);

        showSize();
    }

    static void showSize() {
        System.out.println("byte 大小=" + Byte.BYTES + " 字节，范围 " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
        System.out.println("short 大小=" + Short.BYTES + " 字节");
        System.out.println("int 大小=" + Integer.BYTES + " 字节");
        System.out.println("long 大小=" + Long.BYTES + " 字节");
        System.out.println("float 大小=" + Float.BYTES + " 字节");
        System.out.println("double 大小=" + Double.BYTES + " 字节");
        System.out.println("char 大小=" + Character.BYTES + " 字节");
    }
}

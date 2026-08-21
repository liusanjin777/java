package JavaBaseUse.Stream;
import java.io.FileInputStream;

public class InputStream {
    public static void main(String[] args) {
      
       try {
       new StreamDemoA().main(args);

        System.out.println("-----------------");

       new StreamDemoB().main(args);

        System.out.println("-----------------");

       new StreamDemoC().main(args);
       } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
       }
    }
}


class StreamDemoA {
    public static void main(String[] args) {
         try {
        // 相对路径以“当前工作目录”为基准，这里假设从项目根目录运行
        FileInputStream file = new FileInputStream("assets/demo.txt");

        int data;
        while ((data = file.read()) != -1) {
          System.out.println("data=" + data);
        }

        file.close();
       } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
       }
    }
}



class StreamDemoB {
    public static void main(String[] args) {
         try {
        // 相对路径以“当前工作目录”为基准，这里假设从项目根目录运行
        FileInputStream file = new FileInputStream("assets/demo.txt");

        byte[] data = new byte[2];
        file.read(data);
        System.out.println("data=" + file.read(data));



        file.close();
       } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
       }
    }
}


class StreamDemoC {
    public static void main(String[] args) {
         try {
        // 相对路径以“当前工作目录”为基准，这里假设从项目根目录运行
        FileInputStream file = new FileInputStream("assets/demo.txt");

        byte[] data = new byte[2];
        System.out.println("data=" + file.read(data, 0, 1));



        file.close();
       } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
       }
    }
}

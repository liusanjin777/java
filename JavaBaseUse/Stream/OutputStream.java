package JavaBaseUse.Stream;
import java.io.FileOutputStream;

public class OutputStream {

  public static void main(String[] args) {
    try {
      FileOutputStream file = new FileOutputStream("assets/demo_output.txt");
      file.write("Hello World".getBytes());
      file.close();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
  
}

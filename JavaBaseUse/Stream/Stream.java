package JavaBaseUse.Stream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Stream {
  public static void main(String[] args) {
    try (
      FileInputStream fInput = new FileInputStream("assets/demo.txt");
      FileOutputStream fOutput = new FileOutputStream("assets/demo_output.txt");
    ) {
      int data;
      while ((data = fInput.read()) != -1) {
        fOutput.write(data);
      }
      fOutput.close();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
  
}

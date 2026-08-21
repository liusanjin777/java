package JavaBaseUse.Http;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class App {
    public static void main(String[] args) {
        try {
          // 创建 HTTP 服务器地址
          InetSocketAddress address = new InetSocketAddress("localhost", 8080);
          // 创建 HTTP 服务器
          HttpServer server = HttpServer.create(address, 0);
          // 注册 API 处理程序
          server.createContext("/api", new ApiHandle());
          // 注册用户 处理程序
          server.createContext("/user", new UserHandle());
          // 启动 HTTP 服务器
          server.start();
          System.out.println("Server is running on port 8080");
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
        }
    }
}

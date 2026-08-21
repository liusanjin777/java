package JavaBaseUse.Http;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ApiHandle implements HttpHandler {
  public void handle(HttpExchange exchange) throws IOException {

    // 获取请求方法
    String method = exchange.getRequestMethod();
    System.out.println("Request method: " + method);
    // 获取请求路径
    String uri = exchange.getRequestURI().toString();
    System.out.println("Request URI: " + uri);

    String response =method+uri;

    // 发送响应头
    exchange.sendResponseHeaders(200, response.getBytes().length);

    exchange.getResponseBody().write(response.getBytes());

    // 关闭响应体
    exchange.close();

    // TODO: handle request
    System.out.println("Request received");
  }
}

# HTTP 服务器（HttpServer）

- 日期：2026-08-21
- 阶段：Java 基础
- 状态：新学

## 学习目标

- 认识 JDK 自带的 `com.sun.net.httpserver.HttpServer`
- 掌握创建 HTTP 服务器、注册路由、启动监听的流程
- 理解 `HttpHandler` 和 `HttpExchange` 的作用
- 能从请求里读取方法和路径，并返回一段 HTTP 响应

## 核心概念

### HttpServer 是什么

`com.sun.net.httpserver.HttpServer` 是 JDK 内置的一个轻量级 HTTP 服务器类，不需要引入第三方框架（如 Spring）就能启动一个能处理 `http://localhost:8080/...` 请求的服务。它位于 `jdk.httpserver` 模块中，OpenJDK 和 Temurin 都自带。

一个 HTTP 服务的基本流程：

1. 用 `InetSocketAddress` 指定监听的主机和端口
2. 用 `HttpServer.create(address, 0)` 创建服务器，第二个参数是 backlog，`0` 表示使用默认值
3. 用 `createContext(path, handler)` 注册路由：访问该路径时交给对应 handler 处理
4. 调用 `server.start()` 启动，让服务器开始接收请求

### HttpHandler 接口

`HttpHandler` 是需要实现处理逻辑的接口，只有一个方法 `handle(HttpExchange exchange)`。每一个注册到某个路径上的 handler，负责处理发到该路径的所有请求。

### HttpExchange

`HttpExchange` 封装了一次请求和响应的全部信息：

读取请求：

- `getRequestMethod()`：请求方法，例如 `GET`、`POST`
- `getRequestURI()`：请求地址，`toString()` 出来可能带查询参数

写响应：

- `sendResponseHeaders(200, length)`：先发送响应头，第二个参数是响应体的字节长度
- `getResponseBody().write(bytes)`：把响应内容写入响应体
- `close()`：结束这次交换，释放连接

注意调用顺序：先 `sendResponseHeaders`，再写响应体，最后 `close()`。响应体长度要写成字节数，而不是字符数。

## 代码示例

示例代码在 `JavaBaseUse/Http/` 下，包含两个类：`App` 负责启动服务器，`ApiHandle` 负责处理请求。

### App：创建并启动服务器

`App.java` 在 `localhost:8080` 启动服务器，并把 `/api` 路径交给 `ApiHandle` 处理：

```java
package JavaBaseUse.Http;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class App {
    public static void main(String[] args) {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost", 8080);
            HttpServer server = HttpServer.create(address, 0);
            server.createContext("/api", new ApiHandle());
            server.start();
            System.out.println("Server is running on port 8080");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### ApiHandle：处理请求并返回响应

`ApiHandle.java` 实现 `HttpHandler`，读取请求方法和路径，拼成一个字符串作为响应体返回：

```java
package JavaBaseUse.Http;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ApiHandle implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String uri = exchange.getRequestURI().toString();

        String response = method + uri;   // 例如 GET/api?name=xx

        exchange.sendResponseHeaders(200, response.getBytes().length);
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
    }
}
```

编译并启动：

```bash
javac -d out JavaBaseUse/Http/*.java
java -cp out JavaBaseUse.Http.App
```

启动后访问 `http://localhost:8080/api` 或 `http://localhost:8080/api?name=java`，服务端会打印请求方法、URI，并返回类似 `GET/api?name=java` 的响应正文。

## 易错点

- 必须先 `sendResponseHeaders(...)` 再写响应体，顺序反了会报错
- 响应头里的长度要写 `response.getBytes().length`（字节数），不能直接写 `response.length()`（字符数），否则中文可能截断或出错
- 每次请求处理完必须调用 `exchange.close()`，否则连接可能挂住
- 这个功能属于 JDK 内置模块 `jdk.httpserver`，路径带 `com.sun`，和 `java.io` / `java.net` 不同，不要和第三方框架混淆
- 端口被占用（比如 8080 已被其他程序使用）时会抛异常，启动失败
- `getRequestURI().toString()` 会把查询参数一起带出来，例如 `/api?name=xx`；需要单独取值要自己解析
- 服务器启动后会一直运行占用终端，测试用浏览器或 `curl` 访问即可

## 练习与思考

1. 试访问 `http://localhost:8080/api?name=java`，思考 URI 字符串和请求方法分别在什么时候会变化
2. 修改 handler，让 `/api` 响应带一个换行或固定前缀，例如返回 `Hello: GET/api`
3. 思考：为什么响应体长度要用字节数而不是字符数？
4. 再把 8080 换成别的端口，观察端口被占用时的报错
5. 注册第二个路径，比如 `/hello`，用不同的 handler 返回不同的内容

## 小结

- `HttpServer` 是 JDK 内置的轻量级 HTTP 服务器，适合快速搭一个本地接口
- `createContext` 用来路由，`HttpHandler` 负责具体处理逻辑
- `HttpExchange` 同时提供请求信息和响应出口
- 处理顺序是发响应头、写响应体、关闭，长度按字节计算

## 下一步

- 学习处理 `POST` 请求和读取请求体（`getRequestBody`）
- 学习解析 `GET` 查询参数 `?a=1&b=2`
- 了解 JSON 序列化，让接口返回标准的 JSON 数据
- 对比 Spring Boot 里的 `@RestController` 实现方式

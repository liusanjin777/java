package JavaBaseUse.Http;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserHandle implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            // 1. 读取 assets/user.json 的全部内容
            String json = readFile("assets/user.json");

            // 2. 从查询参数里取 id，例如 /user?id=2
            String id = getQueryValue(exchange.getRequestURI().getQuery(), "id");

            // 3. 有 id 就返回对应用户，否则返回全部用户
            String response = (id == null) ? json : findUser(json, id);

            // 4. 先发响应头，再写响应体，最后关闭
            byte[] body = response.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            byte[] body = ("{\"error\":\"" + e.getMessage() + "\"}").getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(500, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        }
    }

    private static String readFile(String path) throws IOException {
        try (FileInputStream in = new FileInputStream(path)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static String getQueryValue(String query, String key) {
        if (query == null) {
            return null;
        }
        for (String pair : query.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2 && kv[0].equals(key)) {
                return kv[1];
            }
        }
        return null;
    }

    // 在扁平的用户数组里，逐个 {} 找 "id" 匹配的对象
    private static String findUser(String json, String id) {
        int depth = 0;
        int start = -1;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                if (depth == 0) {
                    start = i;
                }
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0 && start != -1) {
                    String block = json.substring(start, i + 1);
                    if (extractId(block).equals(id)) {
                        return block;
                    }
                    start = -1;
                }
            }
        }
        return "{\"error\":\"user not found: " + id + "\"}";
    }

    // 从 {"id": 2, ...} 里取出 id 的值
    private static String extractId(String block) {
        int p = block.indexOf("\"id\"");
        if (p < 0) {
            return "";
        }
        int colon = block.indexOf(':', p);
        int value = colon + 1;
        while (value < block.length()
                && (block.charAt(value) == ' ' || block.charAt(value) == '\t')) {
            value++;
        }
        int end = value;
        while (end < block.length() && Character.isDigit(block.charAt(end))) {
            end++;
        }
        return block.substring(value, end);
    }
}

import com.fastcgi.FCGIInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        var fcgiInterface = new FCGIInterface();
        System.out.println("Fast CGI ready");

        while (fcgiInterface.FCGIaccept() >= 0) {

            String jsonRequest;
            try {
                jsonRequest = readRequestBody();
            } catch (IOException e) {
                String errorResponse = """
                        HTTP/1.1 403 Forbidden Request

                        %s
                        """.formatted(e.getMessage());
                System.out.println(errorResponse);
                continue;
            }

            Gson gson = new Gson();

            Request request = gson.fromJson(jsonRequest, Request.class);

            Response response = Response.handleRequest(request);

            String jsonResponse = gson.toJson(response, Response.class);

            String httpResponse = """
                    HTTP/1.1 200 OK
                    Content-Type: application/json

                    %s
                    """.formatted(jsonResponse);

            System.out.println(httpResponse);
        }

    }

    private static String readRequestBody() throws IOException {    
        FCGIInterface.request.inStream.fill();
        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes =
                FCGIInterface.request.inStream.read(buffer.array(), 0,
                        contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }
}
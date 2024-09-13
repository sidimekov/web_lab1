import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Response {
    public double x;
    public double y;
    public int r;
    public boolean in;
    public String currentTime;
    public float execTime;

    public Response(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public static Response handleRequest(Request request) {
        double x = request.x;
        double y = request.y;
        int r = request.r;
        Response response = new Response(x, y, r);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.currentTime = formatter.format(LocalDateTime.now());
        Instant execStart = Instant.now();
        // валидация
        // curl
        response.in = Calculator.calculatePoint(x, y, r);
        Instant execEnd = Instant.now();
        response.execTime = (float) (Duration.between(execStart, execEnd).toNanos() / Math.pow(10, 9));
        return response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", in=" + in +
                ", currentTime=" + currentTime +
                ", execTime=" + execTime +
                '}';
    }
}

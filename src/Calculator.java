public class Calculator {
    public static boolean calculatePoint(double x, double y, int r) {
        if (x > 0) {
            if (y > 0) {
                return y <= -1 * Math.sqrt(3) * x + r;
            } else if (y < 0) {
                return y >= (double) r / 2 && x <= r;
            }
        } else if (x < 0) {
            if (y < 0) {
                return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2) / 4;
            }
        }
        return false;
    }
}

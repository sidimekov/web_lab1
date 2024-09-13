public class Calculator {
    public static boolean calculatePoint(double x, double y, int r) {
        if (x >= 0) {
            if (y >= 0) {
                if (y <= -1 * Math.sqrt(3) * x + r) {
                    return true;
                }
            }
            if (y <= 0) {
                if (y >= -1 * (double) r / 2 && x <= r) {
                    return true;
                }
            }
        }
        if (x <= 0) {
            if (y <= 0) {
                if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2) / 4) {
                    return true;
                }
            }
        }
        return false;
    }
}

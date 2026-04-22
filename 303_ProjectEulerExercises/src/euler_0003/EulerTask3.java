package euler_0003;

public class EulerTask3 {
    private static final long num = 600851475143L;

    public static long largestPrimeFactor() {
        long n = num;
        long factor = 2;
        long lastFactor = 1;

        while (factor * factor <= n) {
            if (n % factor == 0) {
                lastFactor = factor;
                n /= factor;
            } else {
                factor++;
            }
        }

        return Math.max(lastFactor, n);
    }
}

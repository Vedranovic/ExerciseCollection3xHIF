package euler_0004;

public class EulerTask4 {
    public static int largestPalindromeFrom3DigitProduct() {
        int max = 0;

        for (int i = 999; i >= 100; i--) {
            for (int j = i; j >= 100; j--) {
                int product = i * j;

                if (product <= max) break;

                if (isPalindrome(product)) {
                    max = product;
                }
            }
        }

        return max;
    }

    private static boolean isPalindrome(int n) {
        int original = n;
        int reversed = 0;

        while (n > 0) {
            reversed = reversed * 10 + (n % 10);
            n /= 10;
        }

        return original == reversed;
    }
}

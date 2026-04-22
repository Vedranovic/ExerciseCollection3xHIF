public class PrimeTask implements Runnable {
    private long start;
    private long end;
    private long count = 0;

    public PrimeTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (long i = start; i <= end; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        System.out.format("%s finished range [%,d..%,d], primes = %,d\n",
                Thread.currentThread().getName(),
                start, end, count);
    }

    private boolean isPrime(long num) {
        if (num < 2) {
            return false;
        }

        if (num % 2 == 0) {
            return num == 2;
        }

        for (int i = 3; (long) i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public long getCount() {
        return count;
    }
}

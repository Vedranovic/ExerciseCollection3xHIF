public class Main {
    private long max;
    private int numThreads;
    private int chunkSize;

    public Main(int max, int numThreads) {
        this.max = max;
        this.numThreads = numThreads;
        chunkSize = max / numThreads;
    }

    public void calculate() throws InterruptedException {
        PrimeTask[] tasks = new PrimeTask[numThreads];
        Thread[] worker = new Thread[numThreads];
        long start = 1;
        long count = 0;

        for (int i = 0; i < numThreads; i++) {
            long end = (i == numThreads - 1) ? max : (start + chunkSize - 1);

            tasks[i] = new PrimeTask(start, end);
            worker[i] = new Thread(tasks[i], "Prime worker-" + (i + 1));

            start = end + 1;
        }

        long t0 = System.currentTimeMillis();

        for (Thread thread : worker) {
            thread.start();
        }

        for (Thread thread : worker) {
            thread.join();
        }

        long t1 = System.currentTimeMillis();

        for (PrimeTask task : tasks) {
            count += task.getCount();
        }

        System.out.println("==========================");
        System.out.format("Total primes between 1 and %,d: %,d", max, count);
        System.out.println();
        System.out.format("Time with %d threads: %,d ms", numThreads, t1 - t0);
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main(100_000, 4);
        main.calculate();
    }
}

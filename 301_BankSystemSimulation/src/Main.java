public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(10_000);

        System.out.println("=== UNSAFE / SAFE Withdraw");
        System.out.println("Expected balance: 0");

        Thread t1 = new Thread(new WithdrawTask(account, 5000));
        Thread t2 = new Thread(new WithdrawTask(account, 5000));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Actual Balance: " + account.getBalance());
    }
}

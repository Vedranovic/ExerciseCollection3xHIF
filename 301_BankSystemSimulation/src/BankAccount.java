public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public /* synchronized */ void withdraw(int amount) {
        synchronized (this) {
            balance -= amount;
        }
    }

    public int getBalance() {
        return balance;
    }
}

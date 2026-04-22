public class WithdrawTask implements Runnable {
    private BankAccount bankAccount;
    private int times;

    public WithdrawTask(BankAccount bankAccount, int times) {
        this.bankAccount = bankAccount;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            /* synchronized (bankAccount) { */
                bankAccount.withdraw(1);
            /* } */
        }
    }
}

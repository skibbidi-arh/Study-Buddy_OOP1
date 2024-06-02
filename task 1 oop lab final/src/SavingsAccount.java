import java.io.IOException;

class SavingsAccount extends Account {
     public SavingsAccount(String accountNumber, double balance, double interestRate, double minimumBalance, double transactionLimit)
     {
         super(accountNumber, balance, interestRate, minimumBalance, transactionLimit);
     }
    public void withdraw(double amount) throws IOException {
        if (amount > transactionLimit) {
            System.out.println("Transaction limit exceeded");
        } else if (balance - amount < minimumBalance) {
            System.out.println("Insufficient Funds");
        } else {
            balance -= amount;
            System.out.println("Withdraw "+ amount);
            log("Withdraw", amount);
        }
    }







}

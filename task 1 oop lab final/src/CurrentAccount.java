import java.io.IOException;

public class CurrentAccount extends Account {
    public CurrentAccount(String accountNumber, double balance)  {
        super(accountNumber, balance, 5.0,500,2000);

    }
    public void withdraw(double amount) throws IOException {
        if (amount > transactionLimit) {
            System.out.println("Transaction limit exceeded");
        } else if (balance - amount < minimumBalance) {
            System.out.println("Insufficient Funds");
        } else {
            balance -= amount;
            log("Withdraw", amount);
            System.out.println("Withdraw " +amount);
        }
    }

}

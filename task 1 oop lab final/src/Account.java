import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public  class Account {
    String accountNumber;
    double balance;
     double interestRate;
    double minimumBalance;
     double transactionLimit;

    public Account(String accountNumber, double balance, double interestRate, double minimumBalance, double transactionLimit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.transactionLimit = transactionLimit;
    }
    public void deposit(double amount) throws IOException
    {
        this.balance = this.balance+amount;
        System.out.println("Deposited "+amount);
        log("Deposit",amount);

    }
//    public  void withdraw(double amount)
//    {
//
//    }
    public double calculateInterest() {
        return balance * interestRate / 100;
    }
     void log(String type, double amount) throws IOException {
         try
         {
             BufferedWriter writer = new BufferedWriter( new FileWriter("Transactions.txt"));
             writer.write(type+" "+amount );
             writer.close();
         }
         catch (IOException e)
         {
             System.out.println("Error occured");
         }



     }








}

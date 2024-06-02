import java.io.IOException;
import  java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        SavingsAccount bobsAccount= new SavingsAccount("Bob",5000,10,1000,5000);
        bobsAccount.deposit(2000);
        bobsAccount.withdraw(500);
        bobsAccount.deposit(1000);
        bobsAccount.withdraw(3000);
        bobsAccount.withdraw(2000);

        System.out.println("Interest is "+bobsAccount.calculateInterest());


        CurrentAccount aliceAccount = new CurrentAccount("Alice",5000);
        aliceAccount.deposit(5000);
        aliceAccount.withdraw(600);
        aliceAccount.deposit(1500);
        aliceAccount.withdraw(2500);
        System.out.println("1- access bobs account\n2-Access alice account");
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        switch (a)
        {
            case  1:

        }



    }
}
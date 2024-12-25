package main.project.service;

import main.project.model.Account;
import main.project.model.Ewallet;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("Welcome Sir");
            System.out.println("Please Enter your operation");
            System.out.println("a.login \nb.signup \nc.exit");

            System.out.print("Your choice is : ");
            char choose = scanner.next().charAt(0);
            switch (choose) {
                case 'a':
                    login();
                    break;
                case 'b':
                    signup();
                    break;
                case 'c':
                    System.out.println("you are welcome.");
                    System.out.println("Thanks for using EraaSoft wallet");
                    break;
                default:
                    System.out.println("Invalid Choose");
            }
            if(choose == 'c'){
                break;
            }
        }
    }

    private void signup() {

        System.out.println("Please Enter User name");
        String name = scanner.next();

        System.out.println("Please Enter password");
        String password = scanner.next();

        ValidationService validationService = new ValidationServiceImpl();

        // TODO Validation on UserName and Password
        boolean validation = true;
            if (!validationService.validateUserName(name)) {
                System.out.println("Invalid UserName");
                validation=false;
            }

            if (!validationService.validatePassword(password)) {
                System.out.println("Invalid Password");
                validation=false;
            }



        // TODO SERVICE OF ACCOUNT TO CREATE ACCOUNT
        if(validation) {
            AccountService accountService = new AccountServiceImpl();
            Account account = new Account(name, password);
            if (accountService.createAccount(account)) {
                System.out.println("Account Created");
                System.out.print("Enter your money : ");
                float money = scanner.nextFloat();
                account.setBalance(money);
            } else {
                System.out.println("Account not Created Because this account existed : " + name);
            }
        }

    }

    private void login() {
        System.out.println("Please Enter User name");
        String name = scanner.next();

        System.out.println("Please Enter password");
        String password = scanner.next();

        // TODO SERVICE OF ACCOUNT TO CREATE LOGIN
        AccountService accountService = new AccountServiceImpl();
        Account account =new Account(name,password);
        if (accountService.loginAccount(account)) {
            System.out.println("Login  Success");
            account = Ewallet.getInstance().findUser(name);
            if (account != null) {
                account.setActive("activated");
                services(account);
                }
        }
        else {
            System.out.println("Account not Exist or worng password. Please try again.");
        }

    }

    private void services(Account account) {
        while (true) {
            System.out.println("Choose an option: ");
            System.out.println("1. Deposit\n2. Withdraw\n3. Show Details\n4. Transfer\n5. Show Balance\n6. Exit");

            System.out.print("Your choice is : ");
            int num = scanner.nextInt();

            switch (num) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    float depositAmount = scanner.nextFloat();
                    deposit(account, depositAmount);
                    break;

                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    float withdrawAmount = scanner.nextFloat();
                    withdraw(account, withdrawAmount);
                    break;

                case 3:
                    showDetails(account);
                    break;

                case 4:
                    System.out.print("Enter the username of the account to transfer to: ");
                    String name=scanner.next();
                    Account transferUsername = new Account(name);
                    System.out.print("Enter the amount to transfer: ");
                    float transferAmount = scanner.nextFloat();
                    transferMoney(account,transferUsername,transferAmount);
                    break;

                case 5:
                    showBalance(account);
                    break;

                case 6:
                    System.out.println("Exiting services.");
                    return;

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
    }


    void deposit(Account a, float money){
        float balance = a.getBalance();
        balance +=money;
        System.out.println("Deposited : "+ money);
        a.setBalance(balance);
        System.out.println("Your new balance is : "+balance);
    }

    void withdraw(Account a , float money){
        float balance= a.getBalance();
        if(balance < money){
            System.out.println("not Accepted ,you don't have enough money");
        }
        else{
            balance -= money;
            System.out.println("Accepted operation : ");
            a.setBalance(balance);
            System.out.println("Your new balance is : "+balance);
        }
    }
    void showDetails(Account a){
        System.out.println(a.toString());
    }
    void transferMoney(Account ur_account, Account tran_account,float money){
        float ur_accountBalance = ur_account.getBalance();
        tran_account = Ewallet.getInstance().findUser(tran_account.getUserName());
        if (tran_account != null) {
            if(ur_accountBalance >= money){
                ur_account.setBalance(ur_accountBalance - money);
                tran_account.setBalance(tran_account.getBalance() + money);
                System.out.println("Accepted operation");
                System.out.println("Amount transferred: " + money);
            }
            else
                System.out.println("not Accepted ,you don't have enough money");
        }
        else
            System.out.println("This account does not exist. ");
    }
    void showBalance(Account a){
        System.out.println("Your Balance is : "+a.getBalance());
    }

}

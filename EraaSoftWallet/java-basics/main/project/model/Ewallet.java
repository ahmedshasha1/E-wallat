package main.project.model;

import java.util.ArrayList;
import java.util.List;
public class Ewallet {
    private String name = "EraaSoft Cash";
    private  List<Account> accounts ;
    private static Ewallet ewallet ;

    private Ewallet(){
        accounts = new ArrayList<>();
    }
    public static Ewallet getInstance(){
        if(ewallet == null){
            ewallet = new Ewallet();
        }
        return ewallet;
    }

    public void addAccounts(Account new_account) {
        accounts.add(new_account);

    }

    public  Account findUser(String username){
        for (Account account: accounts) {
            if (account.getUserName().equals(username)){
                return account;
            }
        }
        return null;
    }


}

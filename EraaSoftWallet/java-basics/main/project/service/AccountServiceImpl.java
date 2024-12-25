package main.project.service;

import main.project.model.Account;
import main.project.model.Ewallet;


public class AccountServiceImpl implements AccountService {

        Ewallet ewallet = Ewallet.getInstance();

    @Override
    public boolean createAccount(Account account) {
        if(ewallet.findUser(account.getUserName()) == null ){
            ewallet.addAccounts(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean loginAccount(Account account) {
            Account existingAccount = ewallet.findUser(account.getUserName());
            if (existingAccount != null && existingAccount.getPassword().equals(account.getPassword())) {
                return true;
            }
            return false;
    }

}

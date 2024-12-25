package main.project.model;

public class Account {

    private String userName;
    private String password;
    private float balance;
    private String active;

    public Account(String userName) {
        this.userName=userName;
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName= " + userName + '\'' +
                ", password= " + password + '\'' +
                ", balance= " + balance +
                ", active= " + active + '\'' +
                '}';
    }
}

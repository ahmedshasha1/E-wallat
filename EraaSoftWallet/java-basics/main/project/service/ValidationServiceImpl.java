package main.project.service;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validateUserName(String userName) {
        String patt="^[A-Z][a-zA-Z0-9]{5,}$";
        return userName.matches(patt);
    }

    @Override
    public boolean validatePassword(String password) {
        String patt="(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*=+])(?=\\S+$).{9,}";
        return password.matches(patt);
    }
}

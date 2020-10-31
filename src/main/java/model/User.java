package model;

import model.Transaction;

public class User {

    private PersonalUserAccount userAccount;
    private String userName;
    private Long userId;

    public User(Long userId, String userName, PersonalUserAccount userAccount) {
        this.userAccount = userAccount;
        this.userName = userName;
        this.userId = userId;
    }

    synchronized private boolean modifySumOfAccount(Float sum) {

        return false;
    }

    public PersonalUserAccount getUserAccount() {
        return userAccount;
    }
}

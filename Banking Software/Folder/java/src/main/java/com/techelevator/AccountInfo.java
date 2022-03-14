package com.techelevator;

public class AccountInfo {
    private long userId;
    private String username;
    private String password;
    private int balanceAmount;
    private int depositAmount;
    private int withdrawAmount;

    public AccountInfo(long userId, String username, String password, int balanceAmount, int depositAmount, int withdrawAmount){
        this.balanceAmount = balanceAmount;
        this.password = password;
        this.depositAmount = depositAmount;
        this.withdrawAmount = withdrawAmount;
        this.userId = userId;
        this.username = username;
    }

    public AccountInfo() {

    }



    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public int getDepositAmount() {
        return depositAmount;
    }
    public int getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

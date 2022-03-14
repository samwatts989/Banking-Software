package com.techelevator;

public class AuthorizedUsers {
    //Instance Variables
    private int authorizedNumber;
    private int accountBalance;

    //Getters
    public int getAuthorizedNumber() {
        return authorizedNumber;
    }
    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAuthorizedNumber(int pinNumber){
        authorizedNumber = 1234;
    }

    //Constructor
    public AuthorizedUsers(int authorizedNumber, int accountBalance){
        this.accountBalance = 1234;
        this.authorizedNumber = authorizedNumber;
    }
    public void verifiedUser(int pinNumber){
       setAuthorizedNumber(pinNumber);
    }

}


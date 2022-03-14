package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class BankingSoftware {
    /**
     * Acts similar to VendingMachine
     * Performs Withdraw and Deposit
     */

    private int balance = 10000;
    private int withdrawMoney;
    private int depositMoney;
    private LocalDateTime dateTime = LocalDateTime.now();

    public BankingSoftware() {}

    /**
     * Updates time transaction was started
     * @return
     */
    public String getDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/DD/yyy HH:mm:ss a");
        return dateTime.format(formatter);
    }
    /**
     * giveWithdrawMoneyInDescendingBills method uses total withdraw amount
     * to calculate how many and what type of bills to give to user
     * @return
     */
    public String giveWithdrawnMoneyInDescendingBills(){
        int countHundreds = 0;
        int countTwenties = 0;
        int countTens = 0;
        int countFives = 0;
        int countOnes = 0;

        while(withdrawMoney >= 100){
            countHundreds++;
            balance -= 100;
        }
        while(withdrawMoney >= 20){
            countTwenties++;
            balance -= 20;
        }
        while(withdrawMoney >= 10){
            countTens++;
            balance -= 10;
        }
        while(withdrawMoney >= 5){
            countFives++;
            balance -= 5;
        }
        while(withdrawMoney >= 1){
            countOnes++;
            balance -= 1;
        }

        return "Here's your withdrawal! Hundreds: " + countHundreds + " Twenties: " + countTwenties + " Tens: "+ countTens + " Fives: "+ countFives + " Ones: "+countOnes;
    }

    public int depositMoney(int amount){
    balance += amount;
    return balance;
    }


    public void setWithdrawMoney(int withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public void setDepositMoney(int depositMoney) {this.depositMoney = depositMoney;}

    public int getBalance() {
        return balance;
    }

    public void PrintBalance(){
        boolean alreadyPrinted = false;
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("Transaction History", alreadyPrinted))){
            alreadyPrinted = true;
            //todo track transaction history and add to this file
        } catch (FileNotFoundException e) {
            System.out.println("Oops, something went wrong with creating the Transaction History File");
        }
    }

}

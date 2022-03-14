package com.techelevator;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BankingInterface {
    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        BankingInterface cli = new BankingInterface(menu);
        cli.run();
    }

    /**
     * Acts similar to VendingMachine CLI
     * Choose 1 of 2 options
     * Choosing 1 will prompt Account number to login
     * Check if account number matches valid number
     * Prompt user for Withdraw or Deposit
     * Prompt user for amount
     * Perform action
     * Prompt user if they want to do any more transactions
     * If yes, loop back to Withdraw or Deposit
     */

    private static final String MAIN_MENU_DISPLAY_LOGIN = "Login";
    private static final String MAIN_MENU_DISPLAY_CREATE_ACCOUNT = "Create Account";
    private static final String MAIN_MENU_DISPLAY_QUIT = "Done";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_DISPLAY_LOGIN, MAIN_MENU_DISPLAY_CREATE_ACCOUNT, MAIN_MENU_DISPLAY_QUIT};


    private static final String WITHDRAW = "Withdraw";
    private static final String DEPOSIT = "Deposit";
    private static final String[] MONEY_OPTIONS = {WITHDRAW, DEPOSIT};



    private Menu menu;
    private BankingSoftware bankingSoftware = new BankingSoftware();
    private AccountInfo accountInfo;
    private AccountInfoDao accountInfoDao;

    Scanner scanner = new Scanner(System.in);

    /**
     * Constructors for menu and AuthorizedUsers
     *
     * @param menu
     */
    public BankingInterface(Menu menu) {
        this.menu = menu;
    }


    private void run() {
        boolean running = true;
        while (running) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_DISPLAY_LOGIN)) {
                userLogin();
            }
            if (choice.equals(MAIN_MENU_DISPLAY_CREATE_ACCOUNT)) {
                createAccount();
            }
            if (choice.equals(MAIN_MENU_DISPLAY_QUIT)) {
                quitMenu();
                running = false;
            } else {
                System.out.println("Invalid Option Selected");
            }
        }
    }


    private void userLogin() {
        System.out.println("What is your username?");
        String userInput = scanner.nextLine();
        System.out.println("What is your password?");
        String passwordInput = scanner.nextLine();
       if(userInput.equals(accountInfoDao.getUsername()) && passwordInput.equals(accountInfoDao.getPassword())){
           ManageAccountBalance();
       }else{
           System.out.println("Invalid username and/or password.");
       }

    }

    private void createAccount() {
        AccountInfo accountInfo = PromptForNewAccountInfo();
        accountInfo = accountInfoDao.createAccount(accountInfo);
        System.out.println("Thank you for creating an account with us!");
    }

    private void quitMenu() {
        System.out.println("Thank you have a great day!");
    }

    /**
     * user Id is hard coded in
     * what goes in parameters
     */
    private void ManageAccountBalance() {
        AccountInfo manageAccount = accountInfoDao.getUserId(1234);
        String choice = (String) menu.getChoiceFromOptions(MONEY_OPTIONS);
        if (choice.equals(WITHDRAW)) {
            WithDrawMoney(manageAccount);
        }
        if (choice.equals(DEPOSIT)) {
            DepositMoney(manageAccount);
        }
    }
    /**
     * Balance is set to withdraw amount instead of adding to balance
     * @param withDrawMoney
     */
    private void WithDrawMoney(AccountInfo withDrawMoney) {
        System.out.println("How much would you like to withdraw?");
        int withdrawAmount = Integer.parseInt(scanner.nextLine());
        withDrawMoney.setBalanceAmount(withdrawAmount);
        accountInfoDao.updateWithdraw(withDrawMoney);

    }
    /**
     * Balance is set to deposit amount instead of adding to balance
     * @param depositMoney
     */
    private void DepositMoney(AccountInfo depositMoney) {
        System.out.println("How much would you like to deposit?");
        int depositAmount = Integer.parseInt(scanner.nextLine());
        depositMoney.setBalanceAmount(depositAmount);
        accountInfoDao.updateDeposit(depositMoney);
    }
    private AccountInfo PromptForNewAccountInfo(){
        AccountInfo accountInfo = new AccountInfo();
        int max = 100000;
        int min = 1111;

        boolean uniqueUserId = false;
       while (uniqueUserId == false) {
           long userId = (int) Math.floor(Math.random()*(max-min+1)+min);
           if (!accountInfoDao.userIdList().contains(userId)) {
               accountInfo.setUserId(userId);
               uniqueUserId = true;
           }
       }

        System.out.println("What do you want your username to be?");
        String userInput = scanner.nextLine();
        accountInfo.setUsername(userInput);
        System.out.println("What do you want your password to be?");
        String passwordInput = scanner.nextLine();
        accountInfo.setPassword(passwordInput);
        System.out.println("What would you like to deposit?");
        int balanceInput = Integer.parseInt(scanner.nextLine());
        accountInfo.setBalanceAmount(balanceInput);
        return accountInfo;
    }

}
   /*
                 // todo

                //todo wishlist
                //todo add front end for Banking Software
                //todo PUT to update database with API
                //todo POST to update database with API
                //todo add mortgage rates using api
                //todo networking event
                //todo create transaction history as a database
                //todo loan amortization schedule

    }*/



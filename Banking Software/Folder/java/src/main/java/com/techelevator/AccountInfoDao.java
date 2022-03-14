package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public interface AccountInfoDao {
/**
 *This interface gives a clean view on what JdbcAccountInfoDao can do
 */


//void subtractWithdrawFromAccount(int amount, AccountInfo userId);
AccountInfo getUserId(long id);
AccountInfo getUsername();
AccountInfo getPassword();
AccountInfo createAccount(AccountInfo accountInfo);
AccountInfo getUser(long id);
List<AccountInfo> userIdList();
void updateDeposit(AccountInfo accountInfo);
void updateWithdraw(AccountInfo accountInfo);
}


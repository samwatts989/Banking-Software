package com.techelevator;



import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcAccountInfoDao implements AccountInfoDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountInfoDao(BasicDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public AccountInfo createAccount(AccountInfo accountInfo) {
        String sql = "INSERT INTO users (user_id, username, password) VALUES (?,?,?)/n" +
                "RETURNING city_id;";
        Long newId = jdbcTemplate.queryForObject(sql, long.class, accountInfo.getUserId(), accountInfo.getUsername(), accountInfo.getPassword());
        return getUser(newId);
    }

    @Override
    public AccountInfo getUser(long id) {
        return null;
    }

    @Override
    public List<AccountInfo> userIdList() {
       List<AccountInfo> accountInfo = new ArrayList<>();
        String sql = "SELECT username, password\n" +
                "FROM users\n" +
                "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()){
            accountInfo.add(mapRowToAccount(results));
        }
        return accountInfo;
    }

    @Override
    public void updateDeposit(AccountInfo accountInfo) {
         String sql = "UPDATE balance SET balance_amount = balance_amount + ? WHERE user_id = ?;";
         jdbcTemplate.update(sql,accountInfo.getDepositAmount(), accountInfo.getUserId());
    }

    @Override
    public void updateWithdraw(AccountInfo accountInfo) {
        String sql = "UPDATE balance SET balance_amount = balance_amount + ? WHERE user_id = ?;";
        jdbcTemplate.update(sql,accountInfo.getWithdrawAmount(), accountInfo.getUserId());
    }

    @Override
    public AccountInfo getUserId(long id) {
        List<AccountInfo> accountInfo = new ArrayList<>();
        String sql = "SELECT user_id FROM users WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()){
            accountInfo.add(mapRowToAccount(results));
        }
        return (AccountInfo) accountInfo;
    }


    @Override
    public AccountInfo getUsername() {
        return null;
    }

    @Override
    public AccountInfo getPassword() {
        return null;
    }

    /*
            @Override
            public AccountInfo createAccount(AccountInfo newAccount) {
                String sql = "INSERT INTO users(user_id, username, password) " +
                        "VALUES (?,?,?) RETURNING user_id;";
                Long user_id = jdbcTemplate.queryForObject(sql, Long.class, newAccount.getUserId(), newAccount.getUsername(),
                        newAccount.getPassword());
                return getCreatedAccount(user_id);
            }
        */
    //@Override
    public void addDepositToAccount(AccountInfo depositMoney, AccountInfo userId) {
        String sql = "UPDATE balance\n" +
                "SET balance_amount = balance_amount + ? \n" +
                "WHERE user_id = ?; ";
        jdbcTemplate.update(sql, depositMoney.getDepositAmount(), userId.getUserId());
    }


   // @Override
    public void subtractWithdrawFromAccount(AccountInfo withdrawMoney, AccountInfo userId) {
        String sql = "UPDATE balance\n" +
                "SET balance_amount = balance_amount - ? \n" +
                "WHERE user_id = ?; ";
        jdbcTemplate.update(sql, withdrawMoney.getWithdrawAmount(), userId.getUserId());
    }
    private AccountInfo mapRowToAccount(SqlRowSet rowSet) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUserId(rowSet.getLong("user_id"));
        accountInfo.setUsername(rowSet.getString("username"));
        accountInfo.setPassword(rowSet.getNString("password"));

        return accountInfo;
    }

}











package dev.educery.storage;

import java.sql.*;
import dev.educery.db.Reporting;
import dev.educery.models.Account;

/**
 * Stores and fetches accounts to and from a connected SQL backing store (provided).
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @see "Copyright 2001,2020 Nikolas S. Boyd"
 */
public class AccountStorage implements Reporting {

    private final Connection c;
    protected AccountStorage(Connection c) { this.c = c; }
    public static AccountStorage with(Connection c) { return new AccountStorage(c); }

    static final String INSERT = "insert into accounts (id,name,note,balance) values (?,?,?,?)";
    private PreparedStatement insertion() throws SQLException { return c.prepareStatement(INSERT); }
    public Account insert(Account account) throws SQLException {
        try (PreparedStatement s = insertion()) {
            account.fillValues(s);
            int count = s.executeUpdate();
            return account;
        }
    }

    static final String SELECT = "select id,name,note,balance from accounts where id = ?";
    private PreparedStatement selection() throws SQLException { return c.prepareStatement(SELECT); }
    public Account fetchAccount(int accountID) throws SQLException {
        Account result = new Account();
        try (PreparedStatement s = selection()) {
            s.setInt(1, accountID);
            try (ResultSet results = s.executeQuery()) {
                if (results.next()) {
                    result.fillFrom(results);
                    return result;
                }
            }
            return null; // signal that fetch failed
        }
    }

} // AccountStorage

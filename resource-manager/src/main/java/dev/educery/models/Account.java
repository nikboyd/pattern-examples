package dev.educery.models;

import java.sql.*;

/**
 * A simple account for demo.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @see "Copyright 2001,2020 Nikolas S. Boyd"
 */
public class Account {

    public int id = 0;
    public String name = "";
    public String note = "";
    public double balance = 0.0;

    public static Account sampleAccount() {
        Account result = new Account();
        result.fillInitially();
        return result;
    }

    public void fillInitially() {
        this.id = 5;
        this.name = "John Doe";
        this.note = "Opening Balance";
        this.balance = 500.0;
    }

    public void fillFrom(ResultSet results) throws SQLException {
        int column = 1;
        this.id = results.getInt(column++);
        this.name = results.getString(column++);
        this.note = results.getString(column++);
        this.balance = results.getDouble(column++);
    }

    public void fillValues(PreparedStatement s) throws SQLException {
        int column = 1;
        s.setInt(column++, this.id);
        s.setString(column++, this.name);
        s.setString(column++, this.note);
        s.setDouble(column++, this.balance);
    }

} // Account

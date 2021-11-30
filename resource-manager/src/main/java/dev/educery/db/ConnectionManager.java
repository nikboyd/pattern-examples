package dev.educery.db;

import java.sql.*;
import javax.sql.*;

/**
 * A connection life-cycle manager.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @see "Copyright 2001,2020 Nikolas S. Boyd"
 */
public class ConnectionManager implements Reporting {

    public static interface Handled<T, R> { R use(T item) throws SQLException; }

    private final DataSource dataSource;
    public DataSource dataSource() { return this.dataSource; }
    public ConnectionManager(DataSource ds) { this.dataSource = ds; }

    public <R> R connectUsing(Handled<Connection,R> usage) {
        try (Connection c = dataSource().getConnection()) {
            try {
                return usage.use(c);
            }
            catch (SQLException ex) {
                // roll back any uncommitted changes
                if (!c.isClosed() && !c.getAutoCommit()) c.rollback();
                error(ex); // report problem
                return null; // signal that connection usage failed
            }
        }
        catch(SQLException ex) {
            error(ex); // report problem
            return null; // signal that something failed
        }
    }

} // ConnectionManager

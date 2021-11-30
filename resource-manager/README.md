### Resource Manager

The code herein provides an example application of the [Resource Manager][design-pattern] design pattern.

**Intent:** Factor out resource life-cycle management into a separate class, focus client code on resource usage.

### Connection Life-cycle Demo

The [ExampleMain][example-main] class frames the managed life-cycle demo as follows.

```java
public void demoLifecycles() { Account sample = insertSample(); fetchSample(sample); }

public Account insertSample() { // c = Connection
    Account sample = demo().connectUsing((c) -> AccountStorage.with(c).insert(Account.sampleAccount()));
    assert sample != null && sample.id != 0; // confirm account was initialized and saved
    report(sample, "inserted");
    return sample; }

public Account fetchSample(Account sample) { // c = Connection
    Account test   = demo().connectUsing((c) -> AccountStorage.with(c).fetchAccount(sample.id));
    assert test != null && test.id == sample.id; // confirm saved account was fetched
    report(sample, "fetched");
    return test; }
```

In the frame of this demo, an opened connection passes to [AccountStorage][account-storage], 
both to insert an Account and then fetch the inserted Account, using two separate interactions through **connectUsing**, 
and so with two distinct connection life-cycles.

In turn, [connectUsing][connect-using] in the ConnectionManager allocates and opens a Connection, passes that into 
a connection **usage**, and then closes the Connection using **try-with-resources** (available in Java 8) as control 
passes back out through **connectUsing**. ConnectionManager also handles any SQLExceptions that arise during the 
usage of the connection, so that any uncommitted changes can be rolled back.

```java
public <R> R connectUsing(Handled<Connection,R> usage) {
    try (Connection c = dataSource().getConnection()) {
        try {
            return usage.apply(c);
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
```

Notice there's a division of responsibilities across the layers of code:

| Layer | Responsibilities |
| ----- | ---------------- |
| ExampleMain | provides life-cycle demo frame (nominal client) |
| ConnectionManager | manages connections and handles exceptions that arise during their usage |
| AccountStorage | stores and fetches accounts using provided connections |

This separation of concerns ensures the proper usage and handling of the Connections, and helps keep the 
storage layer code simpler. Here's a portion from [AccountStorage][account-storage].

```java
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

// ... more ...
```

[example-main]: src/main/java/dev/educery/demo/ExampleMain.java#L26
[connect-using]: src/main/java/dev/educery/db/ConnectionManager.java#L19
[account-storage]: src/main/java/dev/educery/storage/AccountStorage.java#L17
[design-pattern]: https://educery.dev/patterns/resource-manager/

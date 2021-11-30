package dev.educery.demo;

import javax.sql.*;
import static java.lang.String.*;
import org.springframework.context.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jmx.export.annotation.*;
import org.springframework.jmx.support.*;
import org.springframework.boot.*;

import dev.educery.db.*;
import dev.educery.models.*;
import dev.educery.storage.*;

/**
 * Demonstrates usage of a database connection manager.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @see "Copyright 2001,2020 Nikolas S. Boyd"
 */
@SpringBootApplication @ComponentScan
public class ExampleMain implements CommandLineRunner, Reporting {

    public static void main(String... args) { SpringApplication.run(ExampleMain.class, args); }
    @Override public void run(String... strings) throws Exception { demoLifecycles(); }
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

    static final String AccountReport = "'%s' account was %s";
    private void report(Account account, String message) {
        report(format(AccountReport, account.name, message)); }

    // prevent autowiring failures on DataSource
    @Bean AnnotationMBeanExporter annotationMBeanExporter() {
        AnnotationMBeanExporter result = new AnnotationMBeanExporter();
        result.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
        return result; }

    @Autowired DataSource ds;
    private ConnectionManager demo;
    public ConnectionManager demo() { // lazy creation with ds
        if (this.demo == null) this.demo = new ConnectionManager(ds);
        return this.demo; }

} // ExampleMain

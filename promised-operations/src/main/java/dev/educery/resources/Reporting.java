package dev.educery.resources;

/**
 * A simple reporting mechanism.
 * @author Nik Boyd <nik.boyd@educery.dev>
 * @see "Copyright 2001,2020 Nikolas S. Boyd"
 */
public interface Reporting {

    public static final String Empty = "";

    default void report(String message) { System.out.println(message); }
    default void error(String message) { System.err.println(message); }
    default void error(Throwable problem) { System.err.println(problem.toString()); }

} // Reporting

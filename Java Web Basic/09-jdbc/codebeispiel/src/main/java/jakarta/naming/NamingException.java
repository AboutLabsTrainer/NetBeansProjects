package jakarta.naming;

/**
 * Minimal stub for compilation when jakarta.jakartaee-api does not include this package.
 * At runtime Payara provides the real implementation.
 */
public class NamingException extends Exception {
    public NamingException() { super(); }
    public NamingException(String message) { super(message); }
    public NamingException(String message, Throwable cause) { super(message, cause); }
    public NamingException(Throwable cause) { super(cause); }
}

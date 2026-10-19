package jakarta.naming;

/**
 * Minimal stub for compilation when jakarta.jakartaee-api does not include this package.
 * At runtime Payara provides the real implementation.
 */
public class InitialContext {
    public Object lookup(String name) throws NamingException {
        throw new UnsupportedOperationException("Stub only – use server implementation at runtime");
    }
}

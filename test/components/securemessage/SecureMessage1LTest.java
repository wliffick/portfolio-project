package components.securemessage;

import components.securemessage.SecureMessage;
import components.securemessage.SecureMessage1L;

/**
 * JUnit test fixture for SecureMessage1L.
 */
public class SecureMessage1LTest extends SecureMessageTest {

    @Override
    protected final SecureMessage constructorTest() {
        return new SecureMessage1L();
    }
}

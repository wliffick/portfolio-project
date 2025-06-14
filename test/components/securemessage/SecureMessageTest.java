package components.securemessage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for SecureMessage1L's kernel and secondary methods.
 */
public class SecureMessageTest extends SecureMessage1LTest {

    /**
     * Abstract constructor for the concrete SecureMessage implementation.
     */
    @Override
    protected SecureMessage constructorTest() {
        return new SecureMessage1L();
    }

    /**
     * Creates and returns a new SecureMessage object.
     *
     * @return new SecureMessage object
     */
    private SecureMessage createSecureMessage() {
        return this.constructorTest();
    }

    /**
     * Test encrypt with simple message and key.
     */
    @Test
    public void testEncrypt1() {
        SecureMessage sm = this.createSecureMessage();
        String encrypted = sm.encrypt("hello", "key");
        assertEquals("Valid encrypted length", 5, encrypted.length());
    }

    /**
     * Test encrypt stores message-key pair.
     */
    @Test
    public void testEncrypt2() {
        SecureMessage sm = this.createSecureMessage();
        sm.encrypt("data", "abc");
        boolean stored = sm.isEncrypted("data", "abc");
        assertEquals("Message-key pair should be stored", true, stored);
    }

    /**
     * Test decrypt reverses Caesar cipher logic.
     */
    @Test
    public void testDecrypt1() {
        SecureMessage sm = this.createSecureMessage();
        String encrypted = sm.encrypt("world", "xyz");
        String decrypted = sm.decrypt(encrypted, "xyz");
        assertEquals("Decryption should return original", "world", decrypted);
    }

    /**
     * Test isEncrypted returns false when not encrypted.
     */
    @Test
    public void testIsEncrypted1() {
        SecureMessage sm = this.createSecureMessage();
        boolean found = sm.isEncrypted("not-stored", "abc");
        assertEquals("Should return false for non-stored entry", false, found);
    }

    /**
     * Test getEncrypted returns expected encrypted string.
     */
    @Test
    public void testGetEncrypted1() {
        SecureMessage sm = this.createSecureMessage();
        String result = sm.encrypt("example", "xyz");
        String retrieved = sm.getEncrypted("example", "xyz");
        assertEquals("Encrypted message should match stored", result,
                retrieved);
    }

    /**
     * Test clear empties all entries.
     */
    @Test
    public void testClear1() {
        SecureMessage sm = this.createSecureMessage();
        sm.encrypt("clearme", "abc");
        sm.clear();
        boolean found = sm.isEncrypted("clearme", "abc");
        assertEquals("After clear, message should not be found", false, found);
    }

    /**
     * Test transferFrom moves all values and clears source.
     */
    @Test
    public void testTransferFrom1() {
        SecureMessage source = this.createSecureMessage();
        source.encrypt("moved", "k");

        SecureMessage target = this.createSecureMessage();
        target.transferFrom(source);

        boolean moved = target.isEncrypted("moved", "k");
        boolean sourceEmpty = !source.isEncrypted("moved", "k");

        assertEquals("Message should be in target", true, moved);
        assertEquals("Message should not be in source", true, sourceEmpty);
    }

    // Secondary Method Tests

    /**
     * Test sendEncryptedMessage prints to console (manual observation).
     */
    @Test
    public void testSendEncryptedMessage1() {
        SecureMessage1L sm = new SecureMessage1L();
        sm.sendEncryptedMessage("hi", "key", "Alice");

    }

    /**
     * Test verifyHash with correct hash.
     */
    @Test
    public void testVerifyHash1() {
        SecureMessage1L sm = new SecureMessage1L();
        String msg = "abc";
        int hash = 'a' + 'b' + 'c';
        boolean valid = sm.verifyHash(msg, String.valueOf(hash));
        assertEquals("Correct hash should validate", true, valid);
    }

    /**
     * Test verifyHash with incorrect hash.
     */
    @Test
    public void testVerifyHash2() {
        SecureMessage1L sm = new SecureMessage1L();
        boolean valid = sm.verifyHash("abc", "9999");
        assertEquals("Incorrect hash should fail", false, valid);
    }

    /**
     * Test hashAndEncrypt returns correct encrypted hash.
     */
    @Test
    public void testHashAndEncrypt1() {
        SecureMessage1L sm = new SecureMessage1L();
        String encryptedHash = sm.hashAndEncrypt("abc", "key");
        String decrypted = sm.decrypt(encryptedHash, "key");

        int expectedHash = 'a' + 'b' + 'c';
        assertEquals("Should decrypt to correct hash",
                String.valueOf(expectedHash), decrypted);
    }

    /**
     * Test decryptVerifyHash returns original message on match.
     */
    @Test
    public void testDecryptVerifyHash1() {
        SecureMessage1L sm = new SecureMessage1L();
        String encrypted = sm.encrypt("check", "123");
        int hash = 'c' + 'h' + 'e' + 'c' + 'k';
        String result = sm.decryptVerifyHash(encrypted, "123",
                String.valueOf(hash));
        assertEquals("Hash match should return original", "check", result);
    }

    /**
     * Test decryptVerifyHash returns failure on mismatch.
     */
    @Test
    public void testDecryptVerifyHash2() {
        SecureMessage1L sm = new SecureMessage1L();
        String encrypted = sm.encrypt("data", "key");
        String result = sm.decryptVerifyHash(encrypted, "key", "9999");
        assertEquals("Hash mismatch should return failure message",
                "Hash verification failed", result);
    }
}

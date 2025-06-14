import components.securemessage.SecureMessage;
import components.securemessage.SecureMessage1L;

/**
 * Use case demonstrating secure message transmission and validation.
 */
public final class UseCase1 {

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SecureMessage sm = new SecureMessage1L();

        String message = "Confidential Report";
        String key = "secureKey";

        // Encrypt and send
        String encrypted = sm.encrypt(message, key);
        System.out.println("Encrypted Message: " + encrypted);

        // Simulate receiver decrypting
        String decrypted = sm.decrypt(encrypted, key);
        System.out.println("Decrypted Message: " + decrypted);

        // Validate that decryption works
        if (message.equals(decrypted)) {
            System.out.println("Success: Message integrity verified.");
        } else {
            System.out.println("Error: Message corrupted.");
        }
    }
}

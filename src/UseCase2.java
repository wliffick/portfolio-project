import components.securemessage.SecureMessage1L;

/**
 * Use case demonstrating hashing and verification of encrypted messages.
 */
public final class UseCase2 {

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SecureMessage1L sm = new SecureMessage1L();

        String message = "Budget Approval";
        String key = "financeKey";

        // Generate a hash of the message
        int hash = 0;
        for (int i = 0; i < message.length(); i++) {
            hash += message.charAt(i);
        }

        // Encrypt the message
        String encrypted = sm.encrypt(message, key);

        // Attempt to verify using decryptVerifyHash
        String result = sm.decryptVerifyHash(encrypted, key,
                String.valueOf(hash));
        if (result.equals(message)) {
            System.out.println("Secure transfer successful. Message verified.");
        } else {
            System.out.println("Warning: Hash mismatch. Potential tampering.");
        }
    }
}

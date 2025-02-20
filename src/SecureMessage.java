import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This class provides methods for secure communication by encrypting and
 * decrypting messages. It uses a simple Caesar cipher technique to ensure that
 * the characters are mapped to printable characters.
 */
public class SecureMessage {

    private Map<String, String> messageMap = new Map1L<>();
    private String key;
    public static final int ASCII_VALUE = 128;

    /**
     * Constructs a SecureMessage instance with the given key.
     *
     * @param key
     *            the encryption key
     */
    public SecureMessage(String key) {
        this.key = key;
    }

    /**
     * Encrypts the given message using the provided key.
     *
     * @param message
     *            the message to be encrypted
     * @param key
     *            the encryption key
     * @return the encrypted message
     */
    public String encrypt(String message, String key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = (char) ((message.charAt(i) + key.charAt(i % key.length()))
                    % ASCII_VALUE);
            encryptedMessage.append(c);
        }
        this.messageMap.add(encryptedMessage.toString(), key);
        return encryptedMessage.toString();
    }

    /**
     * Decrypts the given message using the provided key.
     *
     * @param encryptedMessage
     *            the encrypted message to be decrypted
     * @param key
     *            the encryption key
     * @return the decrypted message
     */
    public String decrypt(String encryptedMessage, String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = (char) ((encryptedMessage.charAt(i)
                    - key.charAt(i % key.length()) + ASCII_VALUE)
                    % ASCII_VALUE);
            decryptedMessage.append(c);
        }
        return decryptedMessage.toString();
    }

    /**
     * Sets the message to be encrypted or decrypted.
     *
     * @param message
     *            the message to be set
     */
    public void setMessage(String message) {
        this.messageMap.add(message, this.key);
    }

    /**
     * Sets the encryption key.
     *
     * @param key
     *            the encryption key to be set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Retrieves the stored message corresponding to the given encrypted
     * message.
     *
     * @param encryptedMessage
     *            the encrypted message
     * @return the corresponding stored message
     */
    public String getMessage(String encryptedMessage) {
        return this.messageMap.value(encryptedMessage);
    }

    /**
     * Retrieves the stored encryption key.
     *
     * @return the stored encryption key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Enter a message: ");
        String message = in.nextLine();

        out.println("Enter a key: ");
        String key = in.nextLine();

        SecureMessage secureMessage = new SecureMessage(key);

        String encryptedMessage = secureMessage.encrypt(message, key);
        out.println("Encrypted Message: " + encryptedMessage);

        String decryptedMessage = secureMessage.decrypt(encryptedMessage, key);
        out.println("Decrypted Message: " + decryptedMessage);

        in.close();
        out.close();
    }
}

package components.securemessage;

import components.map.Map;
import components.map.Map1L;

/**
 * Kernel Implementation of the SecureMessage component.
 */
public class SecureMessage1L extends SecureMessageSecondary {

    /**
     * Helper class representing a (message, key) pair.
     */
    private static class MessageKey {
        public final String message;
        public final String key;

        public MessageKey(String message, String key) {
            this.message = message;
            this.key = key;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj instanceof MessageKey) {
                MessageKey other = (MessageKey) obj;
                if (this.message.equals(other.message)
                        && this.key.equals(other.key)) {
                    result = true;
                }
            }
            return result;
        }

        @Override
        public int hashCode() {
            int result = 31 * this.message.hashCode() + this.key.hashCode();
            return result;
        }
    }

    /**
     * Map storing (message, key) → encryptedMessage.
     */
    private final Map<MessageKey, String> encryptedMap;

    /**
     * Default constructor.
     */
    public SecureMessage1L() {
        this.encryptedMap = new Map1L<>();
    }

    /**
     * Caesar cipher encryption. Encrypts and stores into a map.
     */
    @Override
    public String encrypt(String message, String key) {
        assert message != null && key != null
                && !key.equals("") : "Invalid input";

        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = (char) ((message.charAt(i) + key.charAt(i % key.length()))
                    % 128);
            encrypted.append(c);
        }

        String encryptedMessage = encrypted.toString();
        MessageKey mk = new MessageKey(message, key);

        if (!this.encryptedMap.hasKey(mk)) {
            this.encryptedMap.add(mk, encryptedMessage);
        }

        return encryptedMessage;
    }

    /**
     * Decrypts the message using Caesar cipher and key.
     */
    @Override
    public String decrypt(String encryptedMessage, String key) {
        assert encryptedMessage != null && key != null && !key.equals("");

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = (char) ((encryptedMessage.charAt(i)
                    - key.charAt(i % key.length()) + 128) % 128);
            decrypted.append(c);
        }

        return decrypted.toString();
    }

    /**
     * Checks if the (message, key) combination was stored.
     */
    @Override
    public boolean isEncrypted(String message, String key) {
        assert message != null && key != null;
        return this.encryptedMap.hasKey(new MessageKey(message, key));
    }

    /**
     * Retrieves the encrypted version of the message for the given key.
     */
    @Override
    public String getEncrypted(String message, String key) {
        assert message != null && key != null;
        return this.encryptedMap.value(new MessageKey(message, key));
    }

    @Override
    public void clear() {
        this.encryptedMap.clear();
    }

    @Override
    public SecureMessage newInstance() {
        return new SecureMessage1L();
    }

    @Override
    public void transferFrom(SecureMessage source) {
        assert source != null : "source must not be null";
        assert source instanceof SecureMessage1L : "source must be of type SecureMessage1L";

        SecureMessage1L local = (SecureMessage1L) source;
        this.encryptedMap.transferFrom(local.encryptedMap);
    }

}

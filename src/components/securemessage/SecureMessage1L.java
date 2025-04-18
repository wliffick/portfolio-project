package components.securemessage;

import components.map.Map;
import components.map.Map1L;

/**
 * Kernel Implementation of the SecureMessage component.
 *
 * @convention $this.encryptedMap has non-null keys and values, and each
 *             MessageKey has non-empty message and key strings.
 *
 * @correspondence this = mapping from each (message, key) pair in
 *                 $this.encryptedMap to its associated encrypted message
 */
public final class SecureMessage1L extends SecureMessageSecondary {

    /**
     * Record representing a (message, key) pair.
     *
     * @param message
     *            the original message text
     * @param key
     *            the encryption key used for the message
     */
    private static record MessageKey(String message, String key) {
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

    @Override
    public boolean isEncrypted(String message, String key) {
        assert message != null && key != null;
        return this.encryptedMap.hasKey(new MessageKey(message, key));
    }

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

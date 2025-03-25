package components.securemessage;

import components.standard.Standard;

/**
 * Kernel interface for secure communication with primary methods.
 */
public interface SecureMessage extends Standard<SecureMessage> {

    /**
     * Encrypts the given message using the provided key.
     *
     * @param message
     *            the message to be encrypted
     * @param key
     *            the encryption key
     * @return the encrypted message
     */
    String encrypt(String message, String key);

    /**
     * Decrypts the given encrypted message using the provided key.
     *
     * @param encryptedMessage
     *            the encrypted message to be decrypted
     * @param key
     *            the encryption key
     * @return the decrypted message
     */
    String decrypt(String encryptedMessage, String key);

    /**
     * Checks if the given message is encrypted.
     *
     * @param message
     *            the message to be checked
     * @return true if the message is encrypted, false otherwise
     */
    boolean isEncrypted(String message);

    /**
     * Sets the message to be encrypted or decrypted.
     *
     * @param message
     *            the message to be set
     */
    void setMessage(String message);

    /**
     * Sets the encryption key.
     *
     * @param key
     *            the encryption key to be set
     */
    void setKey(String key);

    /**
     * Retrieves the stored message corresponding to the given encrypted
     * message.
     *
     * @param encryptedMessage
     *            the encrypted message
     * @return the corresponding stored decrypted message
     */
    String getMessage(String encryptedMessage);

    /**
     * Retrieves the currently stored encryption key.
     *
     * @return the stored encryption key
     */
    String getKey();
}

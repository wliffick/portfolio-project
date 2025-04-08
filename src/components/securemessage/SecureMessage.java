package components.securemessage;

import components.standard.Standard;

/**
 * Kernel interface for secure communication with primary methods.
 * <p>
 * This component securely encrypts and decrypts messages using Caesar
 * cipher-style logic. Each unique (message, key) pair is associated with one
 * encrypted result.
 * </p>
 */
public interface SecureMessage extends Standard<SecureMessage> {

    /**
     * Encrypts the given message using the provided key and stores the result.
     * If the same message is encrypted with a different key, both are stored
     * independently.
     *
     * @param message
     *            the original message to encrypt
     * @param key
     *            the encryption key
     * @return the encrypted version of the message
     */
    String encrypt(String message, String key);

    /**
     * Decrypts the given encrypted message using the provided key.
     *
     * @param encryptedMessage
     *            the encrypted message to decrypt
     * @param key
     *            the encryption key used to encrypt the original message
     * @return the decrypted original message
     */
    String decrypt(String encryptedMessage, String key);

    /**
     * Checks whether the given (message, key) pair has been encrypted and
     * stored.
     *
     * @param message
     *            the original message
     * @param key
     *            the encryption key
     * @return true if the pair is in storage, false otherwise
     */
    boolean isEncrypted(String message, String key);

    /**
     * Retrieves the encrypted version of the given message using the provided
     * key.
     *
     * @param message
     *            the original message
     * @param key
     *            the key that was used to encrypt it
     * @return the corresponding encrypted message
     */
    String getEncrypted(String message, String key);
}

package components.securemessage;

import components.standard.Standard;

/**
 * Secure message kernel component with primary methods.
 *
 * This component securely encrypts and decrypts messages using Caesar
 * cipher-style logic. Each unique (message, key) pair is associated with one
 * encrypted result.
 *
 */
public interface SecureMessageKernel extends Standard<SecureMessage> {

    /**
     * Encrypts the given message using the provided key and stores the result.
     *
     * @param message
     *            the original message to encrypt
     * @param key
     *            the encryption key
     * @return the encrypted version of the message
     * @updates this
     * @requires message and key are non-null and key is non-empty
     * @ensures stores the (message, key) → result mapping if not already
     *          stored, and returns the encrypted version
     */
    String encrypt(String message, String key);

    /**
     * Decrypts the given encrypted message using the provided key.
     *
     * @param encryptedMessage
     *            the encrypted message
     * @param key
     *            the encryption key
     * @return the decrypted version of the message
     * @requires encryptedMessage and key are non-null and key is non-empty
     * @ensures returns the result of Caesar cipher decryption
     */
    String decrypt(String encryptedMessage, String key);

    /**
     * Checks whether the given (message, key) pair has already been encrypted
     * and stored.
     *
     * @param message
     *            the message to check
     * @param key
     *            the encryption key
     * @return true if the (message, key) pair exists in the internal mapping
     */
    boolean isEncrypted(String message, String key);

    /**
     * Retrieves the encrypted version of the given message and key pair.
     *
     * @param message
     *            the original message
     * @param key
     *            the encryption key
     * @return the encrypted message associated with this (message, key)
     */
    String getEncrypted(String message, String key);
}

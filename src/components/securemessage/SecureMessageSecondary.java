package components.securemessage;

import components.standard.Standard;

/**
 * Enhanced interface for secure communication with additional methods.
 */
public interface SecureMessageSecondary extends Standard<SecureMessage> {

    /**
     * Encrypts and prints the message to the console.
     *
     * @param message
     *            the message to be encrypted
     * @param key
     *            the encryption key
     * @param recipient
     *            the recipient of the encrypted message
     */
    void sendEncryptedMessage(String message, String key, String recipient);

    /**
     * Verifies the integrity of the message by comparing their hashes.
     *
     * @param message
     *            the message to be verified
     * @param hash
     *            the hash to be compared
     * @return true if the hashes match, false otherwise
     */
    boolean verifyHash(String message, String hash);

    /**
     * Hashes the message and encrypts the message using the provided key.
     *
     * @param message
     *            the message to be hashed and encrypted
     * @param key
     *            the key to be used for encryption
     * @return the hashed and encrypted message
     */
    String hashAndEncrypt(String message, String key);

    /**
     * Decrypts the message using the provided key and verifies its integrity by
     * comparing the hashes.
     *
     * @param encryptedMessage
     *            the message to be decrypted
     * @param key
     *            the encryption key
     * @param hash
     *            the hash to be compared
     * @return the decrypted message if hashes match, null otherwise
     */
    String decryptVerifyHash(String encryptedMessage, String key, String hash);
}

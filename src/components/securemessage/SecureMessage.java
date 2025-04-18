package components.securemessage;

/**
 * Enhanced interface for secure communication that includes the secondary
 * methods.
 *
 */
public interface SecureMessage extends SecureMessageKernel {

    /**
     * Encrypts a message and prints it to the console for a given recipient.
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
     * Verifies the integrity of the message by comparing its computed hash with
     * the provided hash.
     *
     * @param message
     *            the message to verify
     * @param hash
     *            the expected hash value
     * @return true if the computed hash matches the provided hash, false
     *         otherwise
     */
    boolean verifyHash(String message, String hash);

    /**
     * Hashes a message and then encrypts the hash using the given key.
     *
     * @param message
     *            the message to be hashed and encrypted
     * @param key
     *            the key used to encrypt the hash
     * @return the encrypted hash of the message
     */
    String hashAndEncrypt(String message, String key);

    /**
     * Decrypts an encrypted message using the given key and verifies its
     * integrity against the provided hash.
     *
     * @param encryptedMessage
     *            the encrypted message to decrypt
     * @param key
     *            the key used to decrypt the message
     * @param hash
     *            the expected hash of the original message
     * @return the original message if the hash matches, or a failure message
     *         otherwise
     */
    String decryptVerifyHash(String encryptedMessage, String key, String hash);
}

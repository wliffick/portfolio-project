package components.securemessage;

/**
 * Abstract class implementing secondary methods for secure communication.
 * <p>
 * This class uses kernel methods to define higher-level operations such as
 * hashing, verification, and printing encrypted messages.
 * </p>
 */
public abstract class SecureMessageSecondary implements SecureMessage {

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
    public void sendEncryptedMessage(String message, String key,
            String recipient) {
        String encryptedMessage = this.encrypt(message, key);
        System.out.println("To " + recipient + ": " + encryptedMessage);
    }

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
    public boolean verifyHash(String message, String hash) {
        int computedHash = 0;
        for (int i = 0; i < message.length(); i++) {
            computedHash += message.charAt(i);
        }
        boolean result = String.valueOf(computedHash).equals(hash);
        return result;
    }

    /**
     * Hashes a message and then encrypts the hash using the given key.
     *
     * @param message
     *            the message to be hashed and encrypted
     * @param key
     *            the key used to encrypt the hash
     * @return the encrypted hash of the message
     */
    public String hashAndEncrypt(String message, String key) {
        int hash = 0;
        for (int i = 0; i < message.length(); i++) {
            hash += message.charAt(i);
        }
        String hashedMessage = String.valueOf(hash);
        String result = this.encrypt(hashedMessage, key);
        return result;
    }

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
    public String decryptVerifyHash(String encryptedMessage, String key,
            String hash) {
        String decryptedMessage = this.decrypt(encryptedMessage, key);
        String result = "Hash verification failed";
        if (this.verifyHash(decryptedMessage, hash)) {
            result = decryptedMessage;
        }
        return result;
    }

    /**
     * Compares this component with another object for equality.
     * <p>
     * Since internal data is not publicly accessible, this implementation
     * defaults to reference (identity) equality.
     * </p>
     *
     * @param obj
     *            the object to compare with
     * @return true if both references point to the same object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof SecureMessage) {
            result = this == obj;
        }
        return result;
    }
}

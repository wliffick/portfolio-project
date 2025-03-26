package components.securemessage;

/**
 *
 * Abstract class implementing secondary methods for secure communication.
 */
public abstract class SecureMessageSecondary implements SecureMessage {

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
    public void sendEncryptedMessage(String message, String key,
            String recipient) {
        String encryptedMessage = this.encrypt(message, key);
        System.out.println("To " + recipient + ": " + encryptedMessage);
    }

    /**
     * Verifies the integrity of the message by comparing their hashes.
     *
     * @param message
     *            the message to be verified
     * @param hash
     *            the hash to be compared
     * @return true if the hashes match, false otherwise
     */
    public boolean verifyHash(String message, String hash) {
        int computedHash = 0;
        for (int i = 0; i < message.length(); i++) {
            computedHash += message.charAt(i);
        }
        return String.valueOf(computedHash).equals(hash);
    }

    /**
     * Hashes the message and encrypts the message using the provided key.
     *
     * @param message
     *            the message to be hashed and encrypted
     * @param key
     *            the key to be used for encryption
     * @return the hashed and encrypted message
     */
    public String hashAndEncrypt(String message, String key) {
        int hash = 0;
        for (int i = 0; i < message.length(); i++) {
            hash += message.charAt(i);
        }
        String hashedMessage = String.valueOf(hash);
        return this.encrypt(hashedMessage, key);
    }

    /**
     * Decrypts the message using the provided key and verifies its integrity by
     * comparing the hashes.
     *
     * @param encryptedMessage
     *            the encrypted message to be decrypted
     * @param key
     *            the encryption key
     * @param hash
     *            the hash to be compared
     * @return the decrypted message if hashes match, otherwise null
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
     * Returns a string representation of the SecureMessage object.
     *
     * @return a string representation of this SecureMessage object in the
     *         format "SecureMessage: [Key: <key>, Message: <message>]"
     */
    @Override
    public String toString() {
        return "SecureMessage: [Key: " + this.getKey() + ", Message: "
                + this.getMessage("") + "]";
    }

    /**
     * Checks if this SecureMessage is equal to another object based on key and
     * message.
     *
     * @param obj
     *            the object to compare
     * @return true if keys and messages are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null) {
            SecureMessage other = (SecureMessage) obj;
            boolean keysAreEqual = this.getKey().equals(other.getKey());
            boolean messagesAreEqual = this.getMessage("")
                    .equals(other.getMessage(""));
            isEqual = keysAreEqual && messagesAreEqual;
        }
        return isEqual;
    }
}

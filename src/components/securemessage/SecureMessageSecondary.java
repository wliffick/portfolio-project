package components.securemessage;

/**
 * Abstract class for secure communication with secondary methods.
 */
public abstract class SecureMessageSecondary extends SecureMessage {

    @Override
    public void sendEncryptedMessage(String message, String key,
            String recipient) {
        String encryptedMessage = this.encrypt(message, key);
        System.out.println("To " + recipient + ": " + encryptedMessage);
    }

    @Override
    public boolean verifyHash(String message, String hash) {
        int computedHash = 0;
        for (int i = 0; i < message.length(); i++) {
            computedHash += message.charAt(i);
        }
        return String.valueOf(computedHash).equals(hash);
    }

    @Override
    public String hashAndEncrypt(String message, String key) {
        int hash = 0;
        for (int i = 0; i < message.length(); i++) {
            hash += message.charAt(i);
        }
        String hashedMessage = String.valueOf(hash);
        return this.encrypt(hashedMessage, key);
    }

    @Override
    public String decryptVerifyHash(String encryptedMessage, String key, String hash) {
        String decryptedMessage = this.decrypt(encryptedMessage, key);
        return this.verifyHash(decryptedMessage, hash) ? decryptedMessage : "Hash verification failed";
    }
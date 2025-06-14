package components.securemessage;

/**
 *
 * Abstract class implementing secondary methods for secure communication.
 *
 */
public abstract class SecureMessageSecondary implements SecureMessage {

    @Override
    public final void sendEncryptedMessage(String message, String key,
            String recipient) {
        String encryptedMessage = this.encrypt(message, key);
        System.out.println("To " + recipient + ": " + encryptedMessage);
    }

    @Override
    public final boolean verifyHash(String message, String hash) {
        int computedHash = 0;
        for (int i = 0; i < message.length(); i++) {
            computedHash += message.charAt(i);
        }
        boolean result = String.valueOf(computedHash).equals(hash);
        return result;
    }

    @Override
    public final String hashAndEncrypt(String message, String key) {
        int hash = 0;
        for (int i = 0; i < message.length(); i++) {
            hash += message.charAt(i);
        }
        String hashedMessage = String.valueOf(hash);
        String result = this.encrypt(hashedMessage, key);
        return result;
    }

    @Override
    public final String decryptVerifyHash(String encryptedMessage, String key,
            String hash) {
        String decryptedMessage = this.decrypt(encryptedMessage, key);
        String result = "Hash verification failed";
        if (this.verifyHash(decryptedMessage, hash)) {
            result = decryptedMessage;
        }
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof SecureMessage) {
            result = this == obj;
        }
        return result;
    }
}

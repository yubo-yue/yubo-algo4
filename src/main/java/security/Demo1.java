package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Demo1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
    }
}

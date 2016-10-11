package security;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class GenSig {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: GenSig nameOfFileToSign");
        } else {
            try {
                final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
                final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");

                keyPairGenerator.initialize(1024, secureRandom);

                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                PrivateKey privateKey = keyPair.getPrivate();
                PublicKey publicKey = keyPair.getPublic();

                Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
                signature.initSign(privateKey);

                InputStream fis = new BufferedInputStream(Files.newInputStream(Paths.get(args[0])));
                byte[] buffer = new byte[1024];
                int len;

                while((len = fis.read(buffer)) > 0) {
                    signature.update(buffer, 0, len);
                }

                fis.close();

                byte[] realSig = signature.sign();

                OutputStream sigfos = Files.newOutputStream(Paths.get("/Users/yubo/sig"));
                sigfos.write(realSig);
                sigfos.close();

                OutputStream keyfos = Files.newOutputStream(Paths.get("/Users/yubo/suepk"));
                byte[] key = publicKey.getEncoded();
                keyfos.write(key);
                keyfos.close();

            } catch (Exception e) {
                System.err.println("Caught exception" + e.toString());
            }
        }
    }
}

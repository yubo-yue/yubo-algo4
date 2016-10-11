package security;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class VerSig {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: VerSig " + " publickeyfile signarefile datafile");
        } else {
            try {
                InputStream keyfis = Files.newInputStream(Paths.get(args[0]));
                byte[] encKey = new byte[keyfis.available()];
                int ignore = keyfis.read(encKey);
                keyfis.close();

                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
                PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);

                InputStream sigfis = Files.newInputStream(Paths.get(args[1]));
                byte[] sigToVerify = new byte[sigfis.available()];
                ignore = sigfis.read(sigToVerify);
                sigfis.close();

                Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
                sig.initVerify(publicKey);

                InputStream datafis = new BufferedInputStream(Files.newInputStream(Paths.get(args[2])));
                byte[] buffer = new byte[1024];
                int len;
                while (datafis.available() != 0) {
                    len = datafis.read(buffer);
                    sig.update(buffer, 0, len);
                }
                datafis.close();

                boolean verifies = sig.verify(sigToVerify);
                System.out.println("signature verifies: " + verifies);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

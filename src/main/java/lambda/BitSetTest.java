package lambda;

import java.util.BitSet;

/**
 * Created by yubo on 12/16/15.
 */
public class BitSetTest {

    public static void main(String[] args) {
        byte[] bytes = {(byte) 0b10101100, (byte) 0b00101000};
        BitSet primes = BitSet.valueOf(bytes);

        long[] longs = {0x100010116L, 0x1L, 0x1L, 0L, 0x1L};

        BitSet powersOfTwo = BitSet.valueOf(longs);

        if (primes.get(3))
            System.out.println("3 is prime");
        if (!primes.get(4))
            System.out.println("4 is not prime");

        if (powersOfTwo.get(1)) {
            System.out.println("1 is power of 2");
            System.out.println(powersOfTwo.toString());
        }
    }
}

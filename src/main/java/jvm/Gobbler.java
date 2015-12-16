package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubo on 12/8/15.
 */
public class Gobbler {
    public static void main(String[] args) {

        List<byte[]> holder = new ArrayList<byte[]>();
        while (true) {
            int size = (int) (65536 * Math.random());
            holder.add(new byte[size]);
//            Thread.sleep(100L);
        }
    }
}

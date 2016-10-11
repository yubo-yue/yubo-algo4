/**
 * Created by yubo on 3/25/16.
 */
public class BestSquare {
    public static void main(String[] args) {
        int n = 17;
        int x =  (int)Math.sqrt(n);
        if (x * x != n) {
            x++;
        }

        int y = x;
        while (x * y > n + 2) {
            x --;
            y = (int) Math.ceil((double) n / x);
        }
        System.out.println(x + "," + y);
    }
}

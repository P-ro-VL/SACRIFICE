package neu.cs.sacrifice.scene;

import java.math.BigInteger;

public class App {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            BigInteger a = BigInteger.valueOf(4).pow(i);
            BigInteger b = BigInteger.valueOf(3).pow(i);
            BigInteger tuSo = a.add(b);

            BigInteger c = BigInteger.valueOf(7);
            BigInteger d = BigInteger.valueOf(4).pow(i+1);
            BigInteger mauSo = c.subtract(d);

            BigInteger result = tuSo.divide(mauSo);
            System.out.println(result.toString());
        }
    }

}

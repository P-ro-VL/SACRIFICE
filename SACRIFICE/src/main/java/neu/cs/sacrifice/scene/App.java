package neu.cs.sacrifice.scene;

import neu.cs.sacrifice.api.utils.DoubleRange;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        DoubleRange range = DoubleRange.of(3.0, 5.5);
        System.out.println(range.toList(0.5));
    }

}

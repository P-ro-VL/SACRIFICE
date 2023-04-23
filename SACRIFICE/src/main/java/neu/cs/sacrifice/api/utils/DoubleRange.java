package neu.cs.sacrifice.api.utils;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A type of {@link Range} that represents for Doubles.
 */
public class DoubleRange extends Range<Double> implements Iterable<Double> {

    /**
     * Create a new instance of {@link IntRange} with the given {@code min} and
     * {@code max} value.
     */
    public static DoubleRange of(double min, double max) {
        return new DoubleRange(min, max);
    }

    public DoubleRange(double min, double max) {
        super(min, max);
    }

    @Override
    public Double getRandom() {
        Random rand = ThreadLocalRandom.current();
        return rand.nextInt((int) (getMax() - getMin())) + getMin() + rand.nextDouble();
    }

    @Override
    public boolean isInRange(Double value) {
        return value <= getMax() && value >= getMin();
    }

    @Override
    public Iterator<Double> iterator() {
        throw new UnsupportedOperationException("Cannot loop through a double range ! Use iterator(double) instead !");
    }

    public Iterator<Double> iterator(double step) {
        if (!DoubleMath.isMathematicalInteger((getMax() - getMin()) / step))
            System.out.println("[WARNING] The loop step of DoubleRange is not fully fit the given range! Be careful! It will" +
                    " stop at the max number smaller than getMax()");

        List<Double> nums = new ArrayList<>();
        for(double d = getMin(); d <= getMax(); d+= step)
            nums.add(d);
        final Double[] array = nums.toArray(new Double[0]);
        return new Iterator<Double>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < array.length;
            }

            @Override
            public Double next() {
                return array[currentIndex++];
            }
        };
    }

    /**
     * Convert the range into a {@link List}.
     */
    public List<Double> toList() {
        throw new UnsupportedOperationException("Use toList(double) instead!");
    }

    public List<Double> toList(double step){
        List<Double> list = new ArrayList<>();
        for (Iterator<Double> it = iterator(step); it.hasNext(); ) {
            double d = it.next();
            list.add(d);
        }
        return list;
    }

    /**
     * Convert the range into a primitive int array.
     */
    public double[] toArray(double step) {
        return Doubles.toArray(toList(step));
    }

}

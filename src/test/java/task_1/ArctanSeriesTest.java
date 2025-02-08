package task_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class ArctanSeriesTest {

    private static final int N = 1000;
    private static final double EPS = 0.001d;

    @ParameterizedTest
    @ValueSource(
            doubles = {-0.5d, 0d, 0.5d}
    )
    public void checkStandardDots(double x) {
        double computedResult = ArctanSeries.compute(x, N);
        double mostAccurateResult = Math.atan(x);

        assertEquals(mostAccurateResult, computedResult, EPS);
    }

    @ParameterizedTest
    @ValueSource(
            doubles = {-1d, 1d}
    )
    public void checkLimitDots(double x) {
        double computedResult = ArctanSeries.compute(x, N);
        double mostAccurateResult = Math.atan(x);

        assertEquals(mostAccurateResult, computedResult, EPS);
    }

    @ParameterizedTest
    @ValueSource(
            doubles = {0.1d, 0.3d, 0.5d, 1d}
    )
    public void checkOdd(double x) {
        double computedLeftValue = ArctanSeries.compute(-x, N);
        double computedRightValue = ArctanSeries.compute(x, N);

        assertEquals(-computedLeftValue, computedRightValue);
    }

    @Test
    public void checkIncreasing() {
        double[] values = {-1d, -0.7d, -0.5d, -0.3d, -0.1d, 0d, 0.1d, 0.3d, 0.5d, 0.7d, 1d};

        for (int i = 1; i < values.length; i++) {
            double computedPreviousValue = ArctanSeries.compute(values[i - 1], N);
            double computedCurrentValue = ArctanSeries.compute(values[i], N);

           assertTrue(computedPreviousValue < computedCurrentValue);
        }
    }

    @Test
    public void checkNanX() {
        double x = Double.NaN;

        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.compute(x, N));
    }

    @ParameterizedTest
    @ValueSource(
            doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY}
    )
    public void checkInfX(double infX) {
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.compute(infX, N));
    }

    @Test
    public void checkNegativeN() {
        double x = 0;
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.compute(x, -N));
    }


}

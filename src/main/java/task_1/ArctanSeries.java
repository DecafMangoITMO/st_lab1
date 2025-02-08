package task_1;

/**
 * Calculates arctan(x) using a power series:
 *
 *   arctan(x) = x - x³/3 + x⁵/5 - x⁷/7 + ...
 *             = ∑ₙ₌₀^∞ (-1)ⁿ * (x²ⁿ⁺¹) / (2n+1)
 *
 * The series converges for |x| ≤ 1.
 *
 * @param x the argument of the function (must be in the range [-1, 1])
 * @param n the number of terms in the series to compute
 * @return an approximate value of arctan(x)
 */
public class ArctanSeries {

    public static double compute(double x, int n) {
        if (Double.isNaN(x) || Double.isInfinite(x))
            throw new IllegalArgumentException("Parameter x must be NaN or Infinite");

        if (Math.abs(x) > 1d)
            throw new IllegalArgumentException("Parameter x must be: |x| ≤ 1");

        if (n < 0)
            throw new IllegalArgumentException("Parameter n must be greater than or equal to zero");

        double result = 0d;
        for (int i = 0; i < n; i++) {
            double tmp = Math.pow(-1, i) * Math.pow(x, 2 * i + 1) / (2 * i + 1);
            result += tmp;
        }

        return result;
    }

}

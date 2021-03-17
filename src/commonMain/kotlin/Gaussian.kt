import kotlin.math.*
import kotlin.random.Random.Default.nextDouble

object Gaussian {
    /**
     * Based on the JavaDoc from Random.nextGaussian(), OpenJDK 11.
     *
     * This uses the polar method of G. E. P. Box, M. E. Muller, and G. Marsaglia,
     * as described by Donald E. Knuth in The Art of Computer Programming,
     * Volume 2: Seminumerical Algorithms, section 3.4.1, subsection C, algorithm P.
     * Note that it generates two independent values at the cost of only one call to ln and one call to sqrt.
     */
    operator fun invoke(mean: Double = 0.0, standardDeviation: Double = 1.0, N: Int) = List(N) {
        nextGaussian(mean, standardDeviation)
    }

    /**
     * For performance only, use generated [v2] in next iteration
     */
    private var nextGaussian: Double? = null

    private fun nextGaussian(mean: Double, standardDeviation: Double): Double {
        if (nextGaussian != null) {
            val gaussian = nextGaussian!!
            nextGaussian = null
            return gaussian * standardDeviation + mean
        } else {
            var v1: Double
            var v2: Double
            var s: Double
            do {
                v1 = 2 * nextDouble() - 1
                v2 = 2 * nextDouble() - 1
                s = v1 * v1 + v2 * v2
            } while (s >= 1.0 || s == 0.0)
            val multiplier = sqrt(-2 * ln(s) / s)
            nextGaussian = v2 * multiplier
            return mean + standardDeviation * v1 * multiplier
        }
    }
}

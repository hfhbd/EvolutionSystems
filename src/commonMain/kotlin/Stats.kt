
import kotlin.math.*

data class Stats(
    val count: Int,
    val min: Double,
    val max: Double,
    val median: Double,
    val average: Double,

    val std: Double,
    val variance: Double,

    val lowerQuantile: Double,
    val upperQuantile: Double,

    val whisker: Lazy<Whisker>
) {

    data class Whisker(
        val iqr: Double,
        val whiskerLength: Double,
        val lowerWhisker: Double,
        val upperWhisker: Double,
        val lowerExtrema: List<Double>,
        val upperExtrema: List<Double>
    )

    companion object {
        operator fun <T> invoke(
            values: Iterable<T>,
            whiskerLength: Double = 1.5,
            selector: (T) -> Double
        ) = invoke(sortedValues = values.map(selector).sorted(), whiskerLength = whiskerLength)

        operator fun invoke(sortedValues: List<Double>, whiskerLength: Double = 1.5): Stats {
            val count = sortedValues.size
            val min: Double = sortedValues.minOrNull()!!
            val max: Double = sortedValues.maxOrNull()!!
            val median: Double = sortedValues[sortedValues.size / 2]
            val average: Double = sortedValues.average()


            val variance = sortedValues.sumOf { (it - average).pow(2) } / count
            val std = sqrt(variance)

            val lowerQuantile: Double = sortedValues[(sortedValues.size * 0.25).toInt()]
            val upperQuantile: Double = sortedValues[(sortedValues.size * 0.75).toInt()]

            val whisker = lazy {
                val iqr = upperQuantile - lowerQuantile

                val lowerWhisker = (lowerQuantile - whiskerLength * iqr).let {
                    if (min >= it) {
                        max(min, it)
                    } else {
                        sortedValues.takeLastWhile { v -> v > it }.first()
                    }
                }
                val upperWhisker = (upperQuantile + whiskerLength * iqr).let {
                    if (max <= it) {
                        min(max, it)
                    } else {
                        sortedValues.takeWhile { v -> v <= it }.last()
                    }
                }

                val lowerExtrema: List<Double> = sortedValues.takeWhile { it < lowerWhisker }
                val upperExtrema: List<Double> =
                    sortedValues.takeLastWhile { it > upperWhisker }

                Whisker(
                    iqr = iqr,
                    whiskerLength = whiskerLength,
                    lowerWhisker = lowerWhisker,
                    upperWhisker = upperWhisker,
                    lowerExtrema = lowerExtrema,
                    upperExtrema = upperExtrema
                )
            }


            return Stats(
                count = count,
                min = min, max = max,
                median = median,
                average = average,
                variance = variance,
                std = std,
                lowerQuantile = lowerQuantile,
                upperQuantile = upperQuantile,
                whisker = whisker
            )
        }
    }
}

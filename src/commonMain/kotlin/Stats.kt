import kotlin.math.*

interface Stats {
    val min: Double
    val max: Double
    val median: Double
    val average: Double

    val lowerQuantile: Double
    val upperQuantile: Double
    val iqr: Double

    val whiskerLength: Double
    val lowerWhisker: Double
    val upperWhisker: Double
    val lowerExtrema: List<Double>
    val upperExtrema: List<Double>

    companion object {
        operator fun <T : Comparable<T>> invoke(
            values: List<T>,
            whiskerLength: Double = 1.5,
            selector: (T) -> Double
        ): Stats {
            require(values.isNotEmpty())
            val sortedValues = values.map(selector).sorted()
            return object : Stats {
                override val min: Double = sortedValues.minOrNull()!!
                override val max: Double = sortedValues.maxOrNull()!!
                override val median: Double = sortedValues[sortedValues.size / 2]
                override val average: Double = sortedValues.average()

                override val lowerQuantile: Double = sortedValues[(sortedValues.size * 0.25).toInt()]
                override val upperQuantile: Double = sortedValues[(sortedValues.size * 0.75).toInt()]
                override val iqr = upperQuantile - lowerQuantile

                override val whiskerLength = whiskerLength
                override val lowerWhisker = (lowerQuantile - whiskerLength * iqr).let {
                    if (min >= it) {
                        max(min, it)
                    } else {
                        sortedValues.takeLastWhile { v -> v > it }.first()
                    }
                }
                override val upperWhisker = (upperQuantile + whiskerLength * iqr).let {
                    if (max <= it) {
                        min(max, it)
                    } else {
                        sortedValues.takeWhile { v -> v <= it }.last()
                    }
                }

                override val lowerExtrema: List<Double> = sortedValues.takeWhile { it < lowerWhisker }
                override val upperExtrema: List<Double> =
                    sortedValues.takeLastWhile { it > upperWhisker }

            }
        }
    }
}

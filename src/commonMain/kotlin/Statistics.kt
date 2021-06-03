interface Statistics {
    val count: Int
    val min: Double
    val max: Double
    val median: Double
    val average: Double

    val std: Double
    val variance: Double

    val lowerQuantile: Double
    val upperQuantile: Double

    val whisker: Lazy<Whisker>

    data class Whisker(
        val iqr: Double,
        val whiskerLength: Double,
        val lowerWhisker: Double,
        val upperWhisker: Double,
        val lowerExtrema: List<Double>,
        val upperExtrema: List<Double>
    )
}
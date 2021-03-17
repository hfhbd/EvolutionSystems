sealed class Fitness {
    abstract operator fun invoke(values: List<Double>): Double

    object Sphere : Fitness() {
        override fun invoke(values: List<Double>) = values.sumOf { it * it }
    }
}

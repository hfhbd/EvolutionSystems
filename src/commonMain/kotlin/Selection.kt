sealed class Selection {
    abstract operator fun invoke(
        population: List<Solution>, newSolutions: List<Solution>,
        mu: Int,
        iteration: Int, lifetime: Int? = null
    ): List<Solution>

    object Plus : Selection() {
        override fun invoke(
            population: List<Solution>, newSolutions: List<Solution>,
            mu: Int,
            iteration: Int, lifetime: Int?
        ) =
            (population + newSolutions)
                .filter { lifetime?.let { time -> time < (iteration - it.born!!) } ?: true }
                .sorted()
                .take(mu)
    }

    object Comma : Selection() {
        override fun invoke(
            population: List<Solution>, newSolutions: List<Solution>,
            mu: Int,
            iteration: Int, lifetime: Int?
        ) =
            newSolutions
                .filter { lifetime?.let { time -> time < (iteration - it.born!!) } ?: true }
                .sorted()
                .take(mu)
    }
}

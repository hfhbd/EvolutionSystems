sealed class Crossover {
    abstract operator fun invoke(population: List<Solution>, rho: Int, N: Int): List<Double>

    object ArithmeticMean : Crossover() {
        override fun invoke(population: List<Solution>, rho: Int, N: Int) =
            population.shuffled().take(rho).let { parents ->
                List(N) { n ->
                    parents.map { it.values[n] }.average()
                }
            }
    }

    object DominantCrossover : Crossover() {
        override fun invoke(population: List<Solution>, rho: Int, N: Int) =
            population.shuffled().take(rho).let { parents ->
                List(N) { n ->
                    parents.random().values[n]
                }
            }
    }
}

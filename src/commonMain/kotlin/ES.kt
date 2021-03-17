interface ES {
    val mu: Int
    val lambda: Int
    val rho: Int
    val N: Int

    val sigma: Double

    val fitness: Fitness
    val crossover: Crossover
    val selection: Selection

    fun mutate(init: List<Double>): List<Double>

    fun init() = List(mu) {
        val init = Gaussian(N = N)
        Solution(init, fitness(init), born = 0)
    }

    fun List<Double>.componentPlus(other: List<Double>, action: (Double) -> Double) =
        mapIndexed { index, value ->
            value + action(other[index])
        }

    fun run(iterations: Int): Results {
        var population = init()
        val history = mutableListOf(Population(population))
        repeat(iterations) { iteration ->
            val createPopulation = List(lambda) {
                val init = crossover(population, rho, N)
                val values = mutate(init)
                Solution(values, fitness(values), born = iteration)
            }
            population = selection(population, createPopulation, mu, iteration)
            history.add(Population(population))
        }
        return Results(history)
    }
}

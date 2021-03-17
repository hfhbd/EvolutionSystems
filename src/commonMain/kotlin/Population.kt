data class Population(val solutions: List<Solution>) : Stats by Stats(solutions, selector = { it.fitness }) {
    override fun toString(): String = "MinFitness: $min, averageFitness: $average, maxFitness: $max"
}

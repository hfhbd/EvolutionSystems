data class Population(val solutions: List<Solution>) : Statistics by Stats(solutions, selector = { it.fitness })

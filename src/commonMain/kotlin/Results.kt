data class Results(val history: List<Population>) {
    val initPopulation = history.first()
    val finalPopulation = history.last()

    override fun toString() = "Init: $initPopulation, final: $finalPopulation"
}

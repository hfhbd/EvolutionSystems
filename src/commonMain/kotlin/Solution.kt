data class Solution(val values: List<Double>, val fitness: Double, val born: Int? = null, val sigma: Double? = null) :
    Comparable<Solution> {
    override fun compareTo(other: Solution): Int = fitness.compareTo(other.fitness)
}

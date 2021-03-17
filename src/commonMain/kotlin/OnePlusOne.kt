class OnePlusOne(override val N: Int, override val sigma: Double) : ES {
    override val mu: Int = 1
    override val lambda: Int = 1
    override val rho: Int = 1

    override val fitness = Fitness.Sphere
    override val crossover = Crossover.ArithmeticMean
    override val selection = Selection.Plus

    override fun mutate(init: List<Double>): List<Double> {
        val gaussian = Gaussian(N = N)
        return init.componentPlus(gaussian) {
            it * sigma
        }
    }
}

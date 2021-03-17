import kotlin.test.*

class OnePlusOneTest {

    @Test
    fun execute() {
        val es = OnePlusOne(N = 5, sigma = 1.0)
        val results = es.run(iterations = 100_000)
        assertTrue(results.finalPopulation.min < 0.1)
        println(results.toString())
    }
}

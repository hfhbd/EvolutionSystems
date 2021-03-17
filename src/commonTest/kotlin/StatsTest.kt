import kotlin.test.*

class StatsTest {
    @Test
    fun withoutOutliers() {
        val values =
            listOf(57, 57, 57, 58, 63, 66, 66, 67, 67, 68, 69, 70, 70, 70, 70, 72, 73, 75, 75, 76, 76, 78, 79, 81)
        val stats = Stats(values) { it.toDouble() }
        assertEquals(57.0, stats.min)
        assertEquals(81.0, stats.max)
        assertEquals(70.0, stats.median)
        assertEquals(69.16666666666667, stats.average)

        assertEquals(66.0, stats.lowerQuantile)
        assertEquals(75.0, stats.upperQuantile)

        assertEquals(81.0, stats.upperWhisker)
        assertEquals(57.0, stats.lowerWhisker)

        assertTrue(stats.lowerExtrema.isEmpty())
        assertTrue(stats.upperExtrema.isEmpty())
    }

    @Test
    fun withOutliers() {
        val values =
            listOf(52, 57, 57, 58, 63, 66, 66, 67, 67, 68, 69, 70, 70, 70, 70, 72, 73, 75, 75, 76, 76, 78, 79, 89)
        val stats = Stats(values) { it.toDouble() }
        assertEquals(52.0, stats.min)
        assertEquals(89.0, stats.max)
        assertEquals(70.0, stats.median)
        assertEquals(69.29166666666667, stats.average)

        assertEquals(66.0, stats.lowerQuantile)
        assertEquals(75.0, stats.upperQuantile)

        assertEquals(79.0, stats.upperWhisker)
        assertEquals(57.0, stats.lowerWhisker)

        assertEquals(listOf(52.0), stats.lowerExtrema)
        assertEquals(listOf(89.0), stats.upperExtrema)
    }
}

package elementsPack

/**
 * Class storing two numbers reflecting coordinates - x and y
 *
 *
 * x cannot be null
 *
 *
 * y cannot be null and should not be greater than 274
 */
class Coordinates(
    private var x: Float,
    private var y: Double
) {
    override fun toString(): String {
        return "x=" + x +
                ", y=" + y
    }

    fun getX(): Float {
        return x
    }

    fun getY(): Double {
        return y
    }
}
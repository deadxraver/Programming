package elementsPack

enum class Color(
    private val n: Int
) : Comparator<Color> {
    GREEN(0),
    ORANGE(1),
    WHITE(2);
    override fun compare(other: Color?, p1: Color?): Int {
        return this.n - other!!.n
    }
}